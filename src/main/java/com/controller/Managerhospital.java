package com.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.bson.Document;

import com.connect.mongo.Connect;
import com.dao.ManagerhospitalDao;
import com.dto.ManagerhospitalDto;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;

@Path("/Managerhospital")
public class Managerhospital {
	
	@POST
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(ManagerhospitalDto ManagerhospitalDto) {
		
		//importmongo
		Connect mongo = new Connect();
		MongoCollection<Document> collection = mongo.db.getCollection("hospital");
		
		//import json , modelmapper
		JsonObject message = new JsonObject();
		Gson gson = new Gson();
		
		
		ManagerhospitalDao ManagerhospitalDao = new ManagerhospitalDao();
		
		
		ManagerhospitalDao.setNameofhospital(ManagerhospitalDto.getNameofhospital());
		ManagerhospitalDao.setLatijude(ManagerhospitalDto.getLatijude());
		ManagerhospitalDao.setLongjijude(ManagerhospitalDto.getLongjijude());
		ManagerhospitalDao.setTell(ManagerhospitalDto.getTell());
		ManagerhospitalDao.setProvince(ManagerhospitalDto.getProvince());
		ManagerhospitalDao.setDistrict(ManagerhospitalDto.getDistrict());
		ManagerhospitalDao.setSubdistrict(ManagerhospitalDto.getSubdistrict());
		
		String json = gson.toJson(ManagerhospitalDao);
		Document document = Document.parse(json);
		
		BasicDBObject setQuery = new BasicDBObject();
        setQuery.put("$set", document);
		
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("_id", ManagerhospitalDto.get_id());
		
		try {
			collection.updateOne(searchQuery, setQuery);
			message.addProperty("message", true);
		}catch (Exception e) {
			message.addProperty("message", false);
		}
		
		return Response.ok(gson.toJson(message), MediaType.APPLICATION_JSON).build();
	}
		
}
