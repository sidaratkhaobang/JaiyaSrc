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
import com.dto.DistrictDto;
import com.dto.HistoryDto;
import com.dto.HospitalAdminDto;
import com.dto.JaiyaDto;
import com.dto.MachineDto;
import com.dto.ProvinceDto;
import com.google.common.collect.Iterables;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

@Path("/HospitalAdmin")
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
		
		hospitalAdminDao.setLatitude(hospitalAdminDto.getLatitude());
		hospitalAdminDao.setLongitude(hospitalAdminDto.getLongitude());
		hospitalAdminDao.setNameofhospital(hospitalAdminDto.getNameofhospital());
		hospitalAdminDao.setProvinceId(hospitalAdminDto.getProvinceId());
//		hospitalAdminDao.setDistrictId(hospitalAdminDto.getDistrictId());
//		hospitalAdminDao.setSubdistrictId(hospitalAdminDto.getSubdistrictId());
		hospitalAdminDao.setTell(hospitalAdminDto.getTell());
//		
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

	
	@POST
	@Path("/findAll")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response findAll() {
		Connect mongo = new Connect();
		JsonObject message = new JsonObject();
		Gson gson = new Gson();
		MongoCollection<Document> collection = mongo.db.getCollection("hospital");
		MongoCollection<Document> collection2 = mongo.db.getCollection("province");
		
		ModelMapper Mapper = new ModelMapper();
		
		HospitalAdminDto[] value = null;
		ProvinceDto[] province = null;
		
//		 ห้ามใช้     Dao
		try {
			FindIterable<Document> data = collection.find();
			FindIterable<Document> data1 = collection2.find();
			
			int size = Iterables.size(data);
			int size1 = Iterables.size(data1);
			
			
			value = new HospitalAdminDto[size];
			province = new ProvinceDto[size1];
			
			
			int key = 0;
			for (Document document : data) {
				value[key++] = Mapper.map(document, HospitalAdminDto.class);
			}
			int key1 = 0;
			for (Document document : data1) {
				province[key1++] = Mapper.map(document, ProvinceDto.class);
			}
			
			for (int i=0;i<value.length;i++) {
				for (int j=0;j<province.length;j++) {
						if(value[i].getProvinceId().equals(province[j].getProvinceId())){
								value[i].setProvinceId(province[j].getProvinceName());
							}
							
						}
				}
		
	

	  message.addProperty("message", true);
      }catch (Exception e) {
	     message.addProperty("message", false);
	       System.out.println(e.getMessage());
         }
          finally {
	       message.add("data", gson.toJsonTree(value));
          }
        
           return Response.ok(gson.toJson(message), MediaType.APPLICATION_JSON).build();
   }
		}
		