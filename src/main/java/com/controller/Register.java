package com.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.bson.Document;
import org.modelmapper.ModelMapper;

import com.connect.mongo.Connect;
import com.dao.ReisterDao;

import com.dto.RegisterDto;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;


@Path("/register")
public class Register {
	
	
	@POST
	@Path("/insert")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insert(RegisterDto RegisterDto) {
//		importmongo
		Connect mongo = new Connect();
		MongoCollection<Document> collection = mongo.db.getCollection("userData");
		
//		import json , modelmapper
		JsonObject message = new JsonObject();
		Gson gson = new Gson();
		ModelMapper Mapper = new ModelMapper();
		
		
		ReisterDao ReisterDao = Mapper.map(RegisterDto, ReisterDao.class);
		
		
		String json = gson.toJson(ReisterDao);
		Document document = Document.parse(json);
		
//		insert document
		try {
			collection.insertOne(document);
			message.addProperty("message", true);
			
		} catch (Exception e) {
			message.addProperty("message", false);
		}
		
		
		return Response.ok(gson.toJson(message), MediaType.APPLICATION_JSON).build();
		
		
	}
	
	
	@POST
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(RegisterDto RegisterDto) {
		Connect mongo = new Connect();
		JsonObject message = new JsonObject();
		Gson gson = new Gson();
		MongoCollection<Document> collection = mongo.db.getCollection("userData");
		
		ReisterDao ReisterDao = new ReisterDao();
		ReisterDao.setId(RegisterDto.getId());
		ReisterDao.setFirstName(RegisterDto.getFirstName());
		ReisterDao.setLastName(RegisterDto.getLastName());
		ReisterDao.setDOB(RegisterDto.getDob());
		ReisterDao.setGender(RegisterDto.getGender());
		ReisterDao.setAllergy(RegisterDto.getAllergy());
		ReisterDao.setBloodgroup(RegisterDto.getBloodgroup());
		ReisterDao.setDisease(RegisterDto.getDisease());
		
		
		String json = gson.toJson(ReisterDao);
		Document document = Document.parse(json);
		
		BasicDBObject setQuery = new BasicDBObject();
        setQuery.put("$set", document);
		
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("_id", RegisterDto.getId());
		
		try {
			collection.updateOne(searchQuery, setQuery);
			message.addProperty("message", true);
		}catch (Exception e) {
			message.addProperty("message", false);
		}
		
		return Response.ok(gson.toJson(message), MediaType.APPLICATION_JSON).build();
	}
	
	@POST
	@Path("/findOne")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response findOne(RegisterDto RegisterDto) {
		Connect mongo = new Connect();
		JsonObject message = new JsonObject();
		Gson gson = new Gson();
		MongoCollection<Document> collection = mongo.db.getCollection("userData");
		ModelMapper Mapper = new ModelMapper();
		
		
//	{}  สร้าง object in robo db หาค่าที่ input เข้าไป	
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("_id", RegisterDto.getId());
		
		RegisterDto value = new RegisterDto();
		
		try {
			FindIterable<Document> data = collection.find(searchQuery);
			value = Mapper.map(data.first(), RegisterDto.class);
			message.addProperty("message", true);
		}catch (Exception e) {
			message.addProperty("message", false);
		}finally {
			message.add("data", gson.toJsonTree(value));
		}
		
		return Response.ok(gson.toJson(message), MediaType.APPLICATION_JSON).build();
	}
	

}
