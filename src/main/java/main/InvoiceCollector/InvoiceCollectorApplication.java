package main.InvoiceCollector;

import db.CategoryDB;
import db.InvoiceDB;
import db.PartnerDB;
import model.Category;
import model.Invoice;
import model.Partner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Date;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication(scanBasePackages = {"controller"})
public class InvoiceCollectorApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvoiceCollectorApplication.class, args);
//		List<Integer> nums = new ArrayList<>();

//		for (int i = 0; i < 100; i++) {
//			int invNum = ThreadLocalRandom.current().nextInt(1, 9999);
//			while (nums.contains(invNum)) {
//				invNum = ThreadLocalRandom.current().nextInt(1, 9999);
//			}
//			nums.add(invNum);
//			testDB(invNum);
//		}
	}

	public static void testDB(int invNum) {
		InvoiceDB invoiceDB = new InvoiceDB();
		PartnerDB partnerDB = new PartnerDB();
		CategoryDB categoryDB = new CategoryDB();
		int year = ThreadLocalRandom.current().nextInt(2019, 2023);
		int month = ThreadLocalRandom.current().nextInt(1, 13);
		int day = ThreadLocalRandom.current().nextInt(1, 28);
		String invoiceId = "BH/" + year + "-" + invNum;
		Date creDate = Date.valueOf(year + "-" + month + "-" + day);
		if (month == 12) {
			year++;
		}
		else {
			month++;
		}
		Date compDate = null;
		if (day % 2 == 0) {
			compDate = Date.valueOf(year + "-" + month + "-" + day);
		}
		Date deadLine = Date.valueOf(year + "-" + month + "-" + day);
		int grandTotal = ThreadLocalRandom.current().nextInt(10000, 9000000);
		boolean isIncoming = ThreadLocalRandom.current().nextBoolean();
		boolean isOutgoing = !isIncoming;
		long partnerId = ThreadLocalRandom.current().nextLong(1, 4);
		long categoriesId = ThreadLocalRandom.current().nextLong(1, 6);
		Partner partner = partnerDB.getPartnerById(partnerId);
		Category category = categoryDB.getCategoryById(categoriesId);
		Invoice invoice = new Invoice(invoiceId, creDate, compDate, deadLine, grandTotal, isIncoming,
				isOutgoing, partner.getName(), category.getName());
		invoiceDB.insertInvoice(invoice);
	}
}
