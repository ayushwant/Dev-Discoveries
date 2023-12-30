package com.example.demo;

import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.mapping.Column;
import org.hibernate.mapping.PersistentClass;
import org.hibernate.mapping.Property;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@SpringBootApplication
public class GraphQlPocApplication {

	public static void main(String[] args) throws SQLException {
		SpringApplication.run(GraphQlPocApplication.class, args);

		generateEntities();
	}

	public static void generateEntities() {

	}
}
