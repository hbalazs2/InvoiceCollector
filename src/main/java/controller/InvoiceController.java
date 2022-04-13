package controller;

import db.InvoiceDB;
import model.Invoice;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@CrossOrigin(origins="*")
@RestController
public class InvoiceController {

    @GetMapping("/invoices")
    public Map<String, Object> getAllInvoices() {
        InvoiceDB invoiceDB = new InvoiceDB();
        List<Invoice> invoices = invoiceDB.getAllInvoices();
        Map<String, Object> result = new HashMap<>();
        result.put("invoices", invoices);
        result.put("count", invoices.size());

        return result;
    }

    @GetMapping("/invoice")
    public Map<String, Object> getInvoiceById(@RequestParam(defaultValue = "") String id) {
        InvoiceDB invoiceDB = new InvoiceDB();
        System.out.println("ID: "+id);
        List<Invoice> invoices = new ArrayList<>();
        Invoice invoice = invoiceDB.getInvoiceById(id);
        Map<String, Object> result = new HashMap<>();
        if (invoice == null) {
            invoices = invoiceDB.getInvoiceByIdLike(id);
            if (invoices == null) {
                result.put("Message", "No invoices found with ID: " + id + ".");
            }
            else {
                result.put("Message", "More invoices found with ID: " + id + ".");
                result.put("invoices", invoices);
            }
        } else {
            result.put("invoice", invoice);
        }
        return result;
    }

    @GetMapping("/invoiceCod")
    public Map<String, Object> getInvoicesBetweenDatesByCompletionDate(@RequestParam(defaultValue = "") Date startCompDate, Date endCompDate) {
        InvoiceDB invoiceDB = new InvoiceDB();
        List<Invoice> invoices = invoiceDB.getInvoicesBetweenDatesByCompletionDate(startCompDate, endCompDate);
        Map<String, Object> result = new HashMap<>();

        if (invoices == null) {
            result.put("Message", String.format("No invoices found with completion date between: %s and %s.", startCompDate, endCompDate));
        }
        else {
            result.put("Message", String.format("Invoices found with completion date between: %s and %s.", startCompDate, endCompDate));
            result.put("invoices", invoices);
        }
        return result;
    }

    @GetMapping("/invoiceCrd")
    public Map<String, Object> getInvoicesBetweenDatesByCreationDate(@RequestParam(defaultValue = "") Date startCreationDate, Date endCreationDate) {
        InvoiceDB invoiceDB = new InvoiceDB();
        List<Invoice> invoices = invoiceDB.getInvoicesBetweenDatesByCreationDate(startCreationDate, endCreationDate);
        Map<String, Object> result = new HashMap<>();

        if (invoices == null) {
            result.put("Message", String.format("No invoices found with creation date between: %s and %s.", startCreationDate, endCreationDate));
        }
        else {
            result.put("Message", String.format("Invoices found with creation date between: %s and %s.", startCreationDate, endCreationDate));
            result.put("invoices", invoices);
        }
        return result;
    }

    @GetMapping("/invoiceDld")
    public Map<String, Object> getInvoicesBetweenDatesByDeadlineDate(@RequestParam(defaultValue = "") Date startDeadlineDate, Date endDeadlineDate) {
        InvoiceDB invoiceDB = new InvoiceDB();
        List<Invoice> invoices = invoiceDB.getInvoicesBetweenDatesByDeadlineDate(startDeadlineDate, endDeadlineDate);
        Map<String, Object> result = new HashMap<>();

        if (invoices == null) {
            result.put("Message", String.format("No invoices found with deadline date between: %s and %s.", startDeadlineDate, endDeadlineDate));
        }
        else {
            result.put("Message", String.format("Invoices found with deadline date between: %s and %s.", startDeadlineDate, endDeadlineDate));
            result.put("invoices", invoices);
        }
        return result;
    }

    @GetMapping("/invoiceOd")
    public Map<String, Object> getOverdueInvoices() {
        InvoiceDB invoiceDB = new InvoiceDB();
        List<Invoice> invoices = invoiceDB.getOverdueInvoices();
        Map<String, Object> result = new HashMap<>();

        if (invoices == null) {
            result.put("Message", "No overdue invoices found.");
        }
        else {
            result.put("Message", "Following overdue Invoices found.");
            result.put("invoices", invoices);
        }
        return result;
    }
}
