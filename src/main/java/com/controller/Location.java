package com.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.bson.Document;
import org.modelmapper.ModelMapper;

import com.connect.mongo.Connect;
import com.dto.DistrictDto;
import com.dto.HospitalAdminDto;
import com.dto.MachineDto;
import com.dto.ProvinceDto;
import com.dto.RegisterDto;
import com.dto.SubdistrictDto;
import com.dto.UserDataAdminDto;
import com.google.common.collect.Iterables;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

@Path("/location")
public class Location {

	@POST
	@Path("/findprovince")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response findAll() {
		Connect mongo = new Connect();
		JsonObject message = new JsonObject();
		Gson gson = new Gson();
		MongoCollection<Document> collection = mongo.db.getCollection("province");
		ModelMapper Mapper = new ModelMapper();
		
		ProvinceDto[] value = null;
//		 ห้ามใช้     Dao
		try {
			FindIterable<Document> data = collection.find();
			int size = Iterables.size(data);
			value = new ProvinceDto[size];
			int key = 0;
			for (Document document : data) {
				value[key++] = Mapper.map(document, ProvinceDto.class);
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
	@Path("/findDistrict")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response findOne(DistrictDto DistrictDto) {
		Connect mongo = new Connect();
		JsonObject message = new JsonObject();
		Gson gson = new Gson();
		MongoCollection<Document> collection = mongo.db.getCollection("district");
		ModelMapper Mapper = new ModelMapper();
		
//	{}  สร้าง object in robo db หาค่าที่ input เข้าไป	
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("provinceId", DistrictDto.getProvinceId());
		
		DistrictDto value = new DistrictDto();
		
		try {
			FindIterable<Document> data = collection.find(searchQuery);
			value = Mapper.map(data.first(), DistrictDto.class);
			message.addProperty("message", true);
		}catch (Exception e) {
			message.addProperty("message", false);
		}finally {
			message.add("data", gson.toJsonTree(value));
		}
		
		return Response.ok(gson.toJson(message), MediaType.APPLICATION_JSON).build();
	}
	@POST
	@Path("/search")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response search(DistrictDto DistrictDto) {
//		importmongo
		Connect mongo = new Connect();
		MongoCollection<Document> collection = mongo.db.getCollection("district");  // db.district
		
//		import json , modelmapper
		JsonObject message = new JsonObject();
		Gson gson = new Gson();
		ModelMapper Mapper = new ModelMapper();
		
		// find when water = 'value' and seed = 'value'
		BasicDBObject query = new BasicDBObject();
		query.put("provinceId", DistrictDto.getProvinceId()); // {"provinceId":6}
				
		DistrictDto[] value = null;
		
		try {
			FindIterable<Document> data = collection.find(query); // db.district.find({"provinceId":6})
			int size = Iterables.size(data);
			value = new DistrictDto[size];
			int key = 0;
			for (Document document : data) {
				value[key++] = Mapper.map(document, DistrictDto.class);
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
	@Path("/searchsubdis")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response search(com.dto.SubdistrictDto SubdistrictDto) {
//		importmongo
		Connect mongo = new Connect();
		MongoCollection<Document> collection = mongo.db.getCollection("subdistrict");  // db.district
		
//		import json , modelmapper
		JsonObject message = new JsonObject();
		Gson gson = new Gson();
		ModelMapper Mapper = new ModelMapper();
		
		// find when water = 'value' and seed = 'value'
		BasicDBObject query = new BasicDBObject();
		query.put("provinceId", SubdistrictDto.getProvinceId()); // {"provinceId":6}
		query.put("districtId", SubdistrictDto.getDistrictId()); // {"provinceId":6}
		
		SubdistrictDto[] value = null;
		
		try {
			FindIterable<Document> data = collection.find(query); // db.district.find({"provinceId":6})
			int size = Iterables.size(data);
			value = new SubdistrictDto[size];
			int key = 0;
			for (Document document : data) {
				value[key++] = Mapper.map(document, SubdistrictDto.class);
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
	@Path("/findHostpital")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response findAllHospital() {
		Connect mongo = new Connect();
		JsonObject message = new JsonObject();
		Gson gson = new Gson();
		MongoCollection<Document> collection = mongo.db.getCollection("hospital");
		ModelMapper Mapper = new ModelMapper();
		
		HospitalAdminDto[] value = null;
//		 ห้ามใช้     Dao
		try {
			FindIterable<Document> data = collection.find();
			int size = Iterables.size(data);
			value = new HospitalAdminDto[size];
			int key = 0;
			for (Document document : data) {
				value[key++] = Mapper.map(document, HospitalAdminDto.class);
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
	@Path("/findmachine")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response findmachine() {
		Connect mongo = new Connect();
		JsonObject message = new JsonObject();
		Gson gson = new Gson();
		MongoCollection<Document> collection = mongo.db.getCollection("machine");
		ModelMapper Mapper = new ModelMapper();
		
		MachineDto[] value = null;
//		 ห้ามใช้     Dao
		try {
			FindIterable<Document> data = collection.find();
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
	@Path("/findmachine1")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response findmachine1() {
		Connect mongo = new Connect();
		JsonObject message = new JsonObject();
		Gson gson = new Gson();
		MongoCollection<Document> collection = mongo.db.getCollection("machine");
		MongoCollection<Document> collectiontwo = mongo.db.getCollection("province");
		MongoCollection<Document> collectionthree = mongo.db.getCollection("district");
		ModelMapper Mapper = new ModelMapper();
		
		MachineDto[] value = null;
		ProvinceDto[] province = null;
		DistrictDto[] district = null;
//		 ห้ามใช้     Dao
		try {
			FindIterable<Document> data = collection.find();
			FindIterable<Document> data1 = collectiontwo.find();
			FindIterable<Document> data2 = collectionthree.find();
			int size = Iterables.size(data);
			int size1 = Iterables.size(data1);
			int size2 = Iterables.size(data2);
			
			value = new MachineDto[size];
			province = new ProvinceDto[size1];
			district = new DistrictDto[size2];
			
			int key = 0;
			for (Document document : data) {
				value[key++] = Mapper.map(document, MachineDto.class);
			}
			int key1 = 0;
			for (Document document : data1) {
				province[key1++] = Mapper.map(document, ProvinceDto.class);
			}
			key= 0 ;
			for (Document document : data2) {
				district[key++] = Mapper.map(document, DistrictDto.class);
			}
			for (int i=0;i<value.length;i++) {
				for (int j=0;j<province.length;j++) {
					for(int k=0;k<district.length;k++) {
						if(value[i].getProvinceId().equals(province[j].getProvinceId())){
							value[i].setProvinceId(province[j].getProvinceName());
//							if(value[i].getDistrictId().equals(district[k].getDistricId())) {
//								value[i].setProvinceId(province[j].getProvinceName());
//								value[i].setDistrictId(district[k].getDistrictName());
//							}
							
						}
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
