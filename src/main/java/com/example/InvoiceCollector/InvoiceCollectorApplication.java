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
		Date complDate = Date.valueOf(LocalDate.parse("2022-02-25"));
		Date deadline = Date.valueOf(LocalDate.parse("2022-03-15"));
		List<Invoice> invoices = invoiceDB.getAllInvoices();
		System.out.println(invoices);
//		Invoice insertInvoice = invoiceDB.insertInvoice(new Invoice("3" ,complDate, deadline, 7000, false, true, 1, 3));
//		Invoice updateInvoice = invoiceDB.updateInvoice(new Invoice("3" ,complDate, deadline, 98700, false, true, 1, 3));
//		boolean isInvoiceDeleted = invoiceDB.deleteInvoice("3");
//		System.out.println(insertInvoice);
//		System.out.println(isInvoiceDeleted);

	}

}
