package com.example.InvoiceCollector;

import db.DBConnector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;

@SpringBootApplication(scanBasePackages = {"controller"})
public class InvoiceCollectorApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvoiceCollectorApplication.class, args);
		testDB();
	}

	public static void testDB() {
		Connection connection = DBConnector.getInstance().getConnection();
		System.out.println(connection);
		System.out.println("OK");
	}

}
