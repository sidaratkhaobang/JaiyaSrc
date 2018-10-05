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
import com.dao.AlertDao;
import com.dao.ReisterDao;
import com.dto.AlertDto;
import com.dto.RegisterDto;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;


@Path("/alert")
public class Alert {
	
	@POST
	@Path("/insert")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insert(AlertDto AlertDto) {
//		importmongo
		Connect mongo = new Connect();
		MongoCollection<Document> collection = mongo.db.getCollection("timetogetpillow");
		
//		import json , modelmapper
		JsonObject message = new JsonObject();
		Gson gson = new Gson();
		ModelMapper Mapper = new ModelMapper();
		
		
		AlertDao AlertDao = Mapper.map(AlertDto, AlertDao.class);
		
		
		String json = gson.toJson(AlertDao);
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
	

	@DELETE
	@Path("/delete")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response delete(AlertDto AlertDto) {
		Connect mongo = new Connect();
		JsonObject message = new JsonObject();
		Gson gson = new Gson();
		MongoCollection<Document> collection = mongo.db.getCollection("timetogetpillow");
		
		try {
			collection.deleteOne(Filters.eq("_id", AlertDto.getId())); 
			message.addProperty("message", true);
		}catch (Exception e) {
			message.addProperty("message", false);
		}
		
		return Response.ok(gson.toJson(message), MediaType.APPLICATION_JSON).build();
	}
	


}