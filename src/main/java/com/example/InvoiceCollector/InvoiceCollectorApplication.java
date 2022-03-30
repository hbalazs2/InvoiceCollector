package com.example.InvoiceCollector;

import db.DBConnector;
import db.InvoiceDB;
import model.Invoice;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;

@SpringBootApplication(scanBasePackages = {"controller"})
public class InvoiceCollectorApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvoiceCollectorApplication.class, args);
		testDB();
	}

	public static void testDB() {
//		Connection connection = DBConnector.getInstance().getConnection();
		InvoiceDB invoiceDB = new InvoiceDB();
		Date complDate = Date.valueOf(LocalDate.parse("2022-02-25"));
		Date deadline = Date.valueOf(LocalDate.parse("2022-03-15"));
		Invoice invoice = invoiceDB.insertInvoice(new Invoice("ZS-2022/101" ,complDate, deadline, 55000, 2));
		System.out.println(invoice);

	}

}
