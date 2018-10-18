package com.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.bson.Document;
import org.modelmapper.ModelMapper;

import com.connect.mongo.Connect;
import com.dao.MachineDao;
import com.dto.MachineDto;
import com.dto.TimetogetpillowDto;
import com.google.common.collect.Iterables;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

@Path("/machine")
public class Machine {
	
	@POST
	@Path("/search")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response search(MachineDto MachineDto) {
//		importmongo
		Connect mongo = new Connect();
		MongoCollection<Document> collection = mongo.db.getCollection("machine");
		
//		import json , modelmapper
		JsonObject message = new JsonObject();
		Gson gson = new Gson();
		ModelMapper Mapper = new ModelMapper();
		
		// find when water = 'value' and seed = 'value'
		BasicDBObject query = new BasicDBObject();
			
		List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
		obj.add(new BasicDBObject("Status", MachineDto.getStatus()));
		query.put("$and", obj);
				
		MachineDto[] value = null;
		
		try {
			FindIterable<Document> data = collection.find(query);
			int size = Iterables.size(data);
			value = new MachineDto[size];
			int key = 0;
			for (Document document : data) {
				value[key++] = Mapper.map(document, MachineDto.class);
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
	@Path("/insert")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insert(MachineDto MachineDto) {
//		importmongo
		Connect mongo = new Connect();
		MongoCollection<Document> collection = mongo.db.getCollection("machine");
		
//		import json , modelmapper
		JsonObject message = new JsonObject();
		Gson gson = new Gson();
		ModelMapper Mapper = new ModelMapper();
		
		
		MachineDao MachineDao = Mapper.map(MachineDto, MachineDao.class);
		
		
		String json = gson.toJson(MachineDao);
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
	public Response update(MachineDto MachineDto) {
		
//		importmongo
		Connect mongo = new Connect();
		MongoCollection<Document> collection = mongo.db.getCollection("machine");
		
//		import json , modelmapper
		JsonObject message = new JsonObject();
		Gson gson = new Gson();
		
		MachineDao MachineDao = new MachineDao();
		
//		MachineDao.setUserid(MachineDto.getUserid());
		MachineDao.setNameofmachine(MachineDto.getNameofmachine());
		MachineDao.setLatijude(MachineDto.getLatijude());
		MachineDao.setLongjijude(MachineDto.getLongjijude());
		MachineDao.setProvinceId(MachineDto.getProvinceId());
		MachineDao.setDistrictId(MachineDto.getDistrictId());
		MachineDao.setSubdistrictId(MachineDto.getSubdistrictId());
		MachineDao.setStatus(MachineDto.getStatus());
		
		String json = gson.toJson(MachineDao);
		Document document = Document.parse(json);
		
		BasicDBObject setQuery = new BasicDBObject();
        setQuery.put("$set", document);
		
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("_id", MachineDto.getId());
		
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
	public Response delete(MachineDto MachineDto) {
		
//		importmongo
		Connect mongo = new Connect();
		MongoCollection<Document> collection = mongo.db.getCollection("machine");
		
//		import json , modelmapper
		JsonObject message = new JsonObject();
		Gson gson = new Gson();

		
		try {
			collection.deleteOne(Filters.eq("_id", MachineDto.getId())); 
			message.addProperty("message", true);
		}catch (Exception e) {
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
		MongoCollection<Document> collection = mongo.db.getCollection("machine");
		
		ModelMapper Mapper = new ModelMapper();
		MachineDto[] value = null;

		try {
			FindIterable<Document> data = collection.find();
			int size = Iterables.size(data);
			value = new MachineDto[size];
			int key = 0;
			for (Document document : data) {
				value[key++] = Mapper.map(document, MachineDto.class);
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
