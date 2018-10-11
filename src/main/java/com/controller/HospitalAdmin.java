package com.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.bson.Document;
import org.modelmapper.ModelMapper;

import com.connect.mongo.Connect;
import com.dao.HospitalAdminDao;
import com.dao.JaiyaDao;
import com.dto.HospitalAdminDto;
import com.dto.JaiyaDto;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

@Path("/hospitaladmin")
public class HospitalAdmin {
	
	
	@POST
	@Path("/insert")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insert(HospitalAdminDto HospitalAdminDto) {
//		importmongo
		Connect mongo = new Connect();
		MongoCollection<Document> collection = mongo.db.getCollection("hospital");
		
//		import json , modelmapper
		JsonObject message = new JsonObject();
		Gson gson = new Gson();
		ModelMapper Mapper = new ModelMapper();
		
		
		HospitalAdminDao HospitalAdminDao = Mapper.map(HospitalAdminDto, HospitalAdminDao.class);
		
		
		String json = gson.toJson(HospitalAdminDao);
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
	public Response update(HospitalAdminDto hospitalAdminDto) {
		
		//importmongo
		Connect mongo = new Connect();
		MongoCollection<Document> collection = mongo.db.getCollection("hospital");
		
		//import json , modelmapper
		JsonObject message = new JsonObject();
		Gson gson = new Gson();
		
		HospitalAdminDao hospitalAdminDao = new HospitalAdminDao();
		
		hospitalAdminDao.setLatijude(hospitalAdminDto.getLatijude());
		hospitalAdminDao.setLongjijude(hospitalAdminDto.getLongjijude());
		hospitalAdminDao.setNameofhospital(hospitalAdminDto.getNameofhospital());
		hospitalAdminDao.setProvinceId(hospitalAdminDto.getProvinceId());
		hospitalAdminDao.setDistrictId(hospitalAdminDto.getDistrictId());
		hospitalAdminDao.setSubdistrictId(hospitalAdminDto.getSubdistrictId());
		hospitalAdminDao.setTell(hospitalAdminDto.getTell());
		
		String json = gson.toJson(hospitalAdminDao);
		Document document = Document.parse(json);
		
		BasicDBObject setQuery = new BasicDBObject();
        setQuery.put("$set", document);
		
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("_id", hospitalAdminDto.getId());
		
		try {
			collection.updateOne(searchQuery, setQuery);
			message.addProperty("message", true);
		}catch (Exception e) {
			message.addProperty("message", false);
		}
		
		return Response.ok(gson.toJson(message), MediaType.APPLICATION_JSON).build();
	}
	
	
	@DELETE
	@Path("/delete")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response delete(HospitalAdminDto hospitalAdminDto) {
		
		//importmongo
		Connect mongo = new Connect();
		MongoCollection<Document> collection = mongo.db.getCollection("hospital");
		
		//import json , modelmapper
		JsonObject message = new JsonObject();
		Gson gson = new Gson();
		
		try {
			collection.deleteOne(Filters.eq("_id", hospitalAdminDto.getId())); 
			message.addProperty("message", true);
		}catch (Exception e) {
			message.addProperty("message", false);
		}
		
		return Response.ok(gson.toJson(message), MediaType.APPLICATION_JSON).build();
	}
	
	

	
}
