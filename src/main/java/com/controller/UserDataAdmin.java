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

import com.dao.UserDataAdminDao;

import com.dto.UserDataAdminDto;
import com.google.common.collect.Iterables;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;


@Path("/userdataadmin")
public class UserDataAdmin {
	
	@POST
	@Path("/findAll")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response findAll() {
		Connect mongo = new Connect();
		JsonObject message = new JsonObject();
		Gson gson = new Gson();
		MongoCollection<Document> collection = mongo.db.getCollection("userData");
		ModelMapper Mapper = new ModelMapper();
		
		UserDataAdminDto[] value = null;
//		 ห้ามใช้     Dao
		try {
			FindIterable<Document> data = collection.find();
			int size = Iterables.size(data);
			value = new UserDataAdminDto[size];
			int key = 0;
			for (Document document : data) {
				value[key++] = Mapper.map(document, UserDataAdminDto.class);
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
	public Response update(UserDataAdminDto UserDataAdminDto) {
		Connect mongo = new Connect();
		JsonObject message = new JsonObject();
		Gson gson = new Gson();
		MongoCollection<Document> collection = mongo.db.getCollection("userData");
		
		UserDataAdminDao UserDataAdminDao = new UserDataAdminDao();

	
		UserDataAdminDao.setFirstName(UserDataAdminDto.getFirstName());
		UserDataAdminDao.setLastName(UserDataAdminDto.getLastName());
		UserDataAdminDao.setDob(UserDataAdminDto.getDob());
		UserDataAdminDao.setGender(UserDataAdminDto.getGender());
		UserDataAdminDao.setAllergy(UserDataAdminDto.getAllergy());
		UserDataAdminDao.setBloodgroup(UserDataAdminDto.getBloodgroup());
		UserDataAdminDao.setDisease(UserDataAdminDto.getDisease());

		
		String json = gson.toJson(UserDataAdminDao);
		Document document = Document.parse(json);
		
		BasicDBObject setQuery = new BasicDBObject();
        setQuery.put("$set", document);
		
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("_id", UserDataAdminDto.getId());
		
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
	public Response delete(UserDataAdminDto UserDataAdminDto) {
		Connect mongo = new Connect();
		JsonObject message = new JsonObject();
		Gson gson = new Gson();
		MongoCollection<Document> collection = mongo.db.getCollection("userData");
		
		try {
			collection.deleteOne(Filters.eq("_id", UserDataAdminDto.getId())); 
			message.addProperty("message", true);
		}catch (Exception e) {
			message.addProperty("message", false);
		}
		
		return Response.ok(gson.toJson(message), MediaType.APPLICATION_JSON).build();
	}

}
