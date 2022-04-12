package com.example.InvoiceCollector;

import db.DBConnector;
import db.InvoiceDB;
import db.PartnerDB;
import model.Invoice;
import model.Partner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.servlet.http.Part;
import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication(scanBasePackages = {"controller"})
public class InvoiceCollectorApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvoiceCollectorApplication.class, args);
		testDB();
	}

	public static void testDB() {
		PartnerDB partnerDB = new PartnerDB();
		Date date = Date.valueOf(LocalDate.parse("2022-02-16"));

	}
}
