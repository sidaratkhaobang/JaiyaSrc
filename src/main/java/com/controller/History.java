package com.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.bson.Document;
import org.modelmapper.ModelMapper;

import com.connect.mongo.Connect;
import com.dao.AlertDao;
import com.dao.HistoryDao;
import com.dto.AlertDto;
import com.dto.HistoryDto;
import com.dto.TimetogetpillowDto;
import com.google.common.collect.Iterables;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

@Path("/history")
public class History {
	@POST
	@Path("/insert")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insert(HistoryDto historyDto) {
//		importmongo
		Connect mongo = new Connect();
		MongoCollection<Document> collection = mongo.db.getCollection("historytogetpillow");
		
//		import json , modelmapper
		JsonObject message = new JsonObject();
		Gson gson = new Gson();
		ModelMapper Mapper = new ModelMapper();
		
		
		HistoryDao HistoryDao = Mapper.map(historyDto, HistoryDao.class);
		
		
		String json = gson.toJson(HistoryDao);
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
		MongoCollection<Document> collection = mongo.db.getCollection("timetogetpillow");
		
		ModelMapper Mapper = new ModelMapper();
		HistoryDto[] value = null;

		try {
			FindIterable<Document> data = collection.find();
			int size = Iterables.size(data);
			value = new HistoryDto[size];
			int key = 0;
			for (Document document : data) {
				value[key++] = Mapper.map(document, HistoryDto.class);
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
