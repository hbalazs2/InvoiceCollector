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
//		Connection connection = DBConnector.getInstance().getConnection();
		PartnerDB partnerDB = new PartnerDB();
		Date startComplDate = Date.valueOf(LocalDate.parse("2022-02-16"));

		List<Partner> partners = partnerDB.getPartnersByCity("est");
		System.out.println(partners);


//		Partner partner = partnerDB.insertPartner(new Partner("Medium Ltd", "DE", "789634", "Berlin", "Hauptstrasse 23.", Date.valueOf(LocalDate.now())));
//		System.out.println(partner);
		boolean isDeleted = partnerDB.deletePartner(4);
		System.out.println(isDeleted);

	}

}
