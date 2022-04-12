package com.example.InvoiceCollector;

import db.DBConnector;
import db.InvoiceDB;
import model.Invoice;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
		InvoiceDB invoiceDB = new InvoiceDB();
		Date startComplDate = Date.valueOf(LocalDate.parse("2022-02-16"));
		Date endComplDate = Date.valueOf(LocalDate.parse("2023-02-28"));
		Date complDate = Date.valueOf(LocalDate.parse("2022-01-25"));
		Date creationDate = Date.valueOf(LocalDate.parse("2022-01-25"));
		Date deadline = Date.valueOf(LocalDate.parse("2022-09-15"));

//		List<Invoice> invoices = invoiceDB.getInvoicesByPartnerName("big");
//		System.out.println(invoices);
//		Invoice insertInvoice = invoiceDB.insertInvoice(new Invoice("2022/BH-10" ,complDate, deadline, 90000, false, true, 2, 4));
		Invoice updateInvoice = invoiceDB.updateInvoiceCategories("ZS-2022/139", "Wages");
		System.out.println(updateInvoice);

	}

}
