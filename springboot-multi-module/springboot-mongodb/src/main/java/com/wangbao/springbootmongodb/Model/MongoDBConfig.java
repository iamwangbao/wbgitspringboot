package com.wangbao.springbootmongodb.Model;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoDBConfig {
	@Bean
	public MongoClient mongoClient() {
	    return MongoClients.create("mongodb://localhost:27017");
	}
	@Bean
	public MongoTemplate mongoTemplate() {
	    return new MongoTemplate(mongoClient(), "test2");
	}
}
