package com.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.bson.Document;
import org.modelmapper.ModelMapper;

import com.connect.mongo.Connect;
import com.dao.JaiyaDao;
import com.dao.ReisterDao;
import com.dao.UserDataAdminDao;
import com.dto.JaiyaDto;
import com.dto.ProvinceDto;
import com.dto.RegisterDto;
import com.dto.UserDataAdminDto;
import com.google.common.collect.Iterables;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

@Path("/registernew")
public class RegisterNew {
	
	@POST
	@Path("/insert")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insert(JaiyaDto JaiyaDto) {
//		importmongo
		Connect mongo = new Connect();
		MongoCollection<Document> collection = mongo.db.getCollection("user");
		
//		import json , modelmapper
		JsonObject message = new JsonObject();
		Gson gson = new Gson();
		ModelMapper Mapper = new ModelMapper();
		
		
		JaiyaDao JaiyaDao = Mapper.map(JaiyaDto, JaiyaDao.class);
		JaiyaDao.setStatus("1");
		
		
		String json = gson.toJson(JaiyaDao);
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
	@Path("/findAll")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response findAll() {
		Connect mongo = new Connect();
		JsonObject message = new JsonObject();
		Gson gson = new Gson();
		MongoCollection<Document> collection = mongo.db.getCollection("user");
		ModelMapper Mapper = new ModelMapper();
		
		JaiyaDto[] value = null;
//		 ห้ามใช้     Dao
		try {
			FindIterable<Document> data = collection.find();
			int size = Iterables.size(data);
			value = new JaiyaDto[size];
			int key = 0;
			for (Document document : data) {
				value[key++] = Mapper.map(document, JaiyaDto.class);
			}
			message.addProperty("message", true);
		}catch (Exception e) {
			message.addProperty("message", false);
		}finally {
			message.add("data", gson.toJsonTree(value));
		}
		
		return Response.ok(gson.toJson(message), MediaType.APPLICATION_JSON).build();
	}
	
	@POST
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(UserDataAdminDto JaiyaDto) {
		Connect mongo = new Connect();
		JsonObject message = new JsonObject();
		Gson gson = new Gson();
		MongoCollection<Document> collection = mongo.db.getCollection("user");
		
		JaiyaDao JaiyaDao = new JaiyaDao();

		JaiyaDao.setUsername(JaiyaDao.getUsername());
		JaiyaDao.setEmail(JaiyaDao.getEmail());
		JaiyaDao.setTel(JaiyaDao.getTel());
		JaiyaDao.setPassword(JaiyaDao.getPassword());

		
		String json = gson.toJson(JaiyaDao);
		Document document = Document.parse(json);
		
		BasicDBObject setQuery = new BasicDBObject();
        setQuery.put("$set", document);
		
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("_id", JaiyaDto.getId());
		
		try {
			collection.updateOne(searchQuery, setQuery);
			message.addProperty("message", true);
		}catch (Exception e) {
			message.addProperty("message", false);
		}
		
		return Response.ok(gson.toJson(message), MediaType.APPLICATION_JSON).build();
	}
	
}
