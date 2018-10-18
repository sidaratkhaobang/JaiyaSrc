package com.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.bson.Document;
import org.modelmapper.ModelMapper;

import com.connect.mongo.Connect;
import com.dao.TimetogetpillowDao;
import com.dto.HistoryDto;
import com.dto.HospitalAdminDto;
import com.dto.SubdistrictDto;
import com.dto.TimetogetpillowDto;
import com.google.common.collect.Iterables;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

@Path("/timetogetpillow")
public class Timetogetpillow {
	
	@POST
	@Path("/insert")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insert(TimetogetpillowDto TimetogetpillowDto) {
//		importmongo
		Connect mongo = new Connect();
		MongoCollection<Document> collection = mongo.db.getCollection("timetogetpillow");
		
//		import json , modelmapper
		JsonObject message = new JsonObject();
		Gson gson = new Gson();
		ModelMapper Mapper = new ModelMapper();
		
		
		TimetogetpillowDao TimetogetpillowDao = Mapper.map(TimetogetpillowDto, TimetogetpillowDao.class);
		TimetogetpillowDao.setStatustoeatpillow("1");
		
		String json = gson.toJson(TimetogetpillowDao);
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
	public Response update(TimetogetpillowDto TimetogetpillowDto) {
//		importmongo
		Connect mongo = new Connect();
		MongoCollection<Document> collection = mongo.db.getCollection("timetogetpillow");
		
//		import json
		JsonObject message = new JsonObject();
		Gson gson = new Gson();
		
		TimetogetpillowDao timetogetpillow = new TimetogetpillowDao();
		timetogetpillow.setStatustoeatpillow("2");
		
		String json = gson.toJson(timetogetpillow);
		Document document = Document.parse(json);
		
		BasicDBObject setQuery = new BasicDBObject();
        setQuery.put("$set", document);
        
        BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("_id", TimetogetpillowDto.getId());
		
//		insert document
		try {
			collection.updateOne(searchQuery, setQuery);
			message.addProperty("message", true);
			
		} catch (Exception e) {
			message.addProperty("message", false);
		}
		
		
		return Response.ok(gson.toJson(message), MediaType.APPLICATION_JSON).build();
		
		
	}
	
	@POST
	@Path("/findAllTimetogetpillow")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response findAll() {
		Connect mongo = new Connect();
		JsonObject message = new JsonObject();
		Gson gson = new Gson();
		MongoCollection<Document> collection = mongo.db.getCollection("timetogetpillow");
		
		ModelMapper Mapper = new ModelMapper();
		TimetogetpillowDto[] value = null;

		try {
			FindIterable<Document> data = collection.find();
			int size = Iterables.size(data);
			value = new TimetogetpillowDto[size];
			int key = 0;
			for (Document document : data) {
				value[key++] = Mapper.map(document, TimetogetpillowDto.class);
			}
			message.addProperty("message", true);
		}
		catch (Exception e) {
			message.addProperty("message", false);
		}
		finally {
			message.add("data", gson.toJsonTree(value));
		}
		
		return Response.ok(gson.toJson(message), MediaType.APPLICATION_JSON).build();
	}

	
}
