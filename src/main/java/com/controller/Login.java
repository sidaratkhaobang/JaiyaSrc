package com.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.bson.Document;
import org.modelmapper.ModelMapper;

import com.connect.mongo.Connect;
import com.dto.DistrictDto;
import com.dto.JaiyaDto;
import com.dto.RegisterDto;
import com.dto.SubdistrictDto;
import com.google.common.collect.Iterables;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
@Path("/login")
public class Login {
	@POST
	@Path("/findOne")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response findOne(JaiyaDto JaiyaDto) {
		Connect mongo = new Connect();
		JsonObject message = new JsonObject();
		Gson gson = new Gson();
		MongoCollection<Document> collection = mongo.db.getCollection("user");
		ModelMapper Mapper = new ModelMapper();		
		
//	{}  สร้าง object in robo db หาค่าที่ input เข้าไป	
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("username", JaiyaDto.getUsername());
		searchQuery.put("password", JaiyaDto.getPassword());
		JaiyaDto value = new JaiyaDto();
		
		try {
			FindIterable<Document> data = collection.find(searchQuery);
			value = Mapper.map(data.first(), JaiyaDto.class);
			message.addProperty("message", true);
		}catch (Exception e) {
			message.addProperty("message", false);
		}finally {
			message.add("data", gson.toJsonTree(value));
		}
		
		return Response.ok(gson.toJson(message), MediaType.APPLICATION_JSON).build();
	}
}
