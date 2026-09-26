package com.tvmaze.api.midd.config;


import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoConfig {

    @Bean
    public MongoClient mongoClient() {
        // Inyectamos la cadena directamente ya que tarde un buen rato en configurar el application.properties y .yml pero no tomó la configuracion desde ahí =(
        String uri = "mongodb+srv://jcesarmagaion_db_user:8cRrr6tIBwLCD1Z5@cluster0.spedkc4.mongodb.net/tvmaze_db?retryWrites=true&w=majority&appName=Cluster0";
        return MongoClients.create(uri);
    }
}