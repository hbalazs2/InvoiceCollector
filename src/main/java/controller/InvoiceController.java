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
    public Object getInvoiceById(@RequestParam(defaultValue = "") String id) {
        InvoiceDB invoiceDB = new InvoiceDB();
        System.out.println("ID class: " + id.getClass());
        List<Invoice> invoices = new ArrayList<>();
        Invoice invoice = invoiceDB.getInvoiceById(id);
        if (invoice == null) {
            invoices = invoiceDB.getInvoiceByIdLike(id);
        } else {
            invoices.add(invoice);
        }
        return invoices;
    }

    @GetMapping("/invoiceByAll")
    public Map<String, Object> getInvoicesByAll(@RequestParam(defaultValue = "") String id, Date startCreationDate,
                                                Date endCreationDate, Date startCompDate, Date endCompDate,
                                                Date startDeadlineDate, Date endDeadlineDate, int minLimit, int maxLimit,
                                                boolean isIncoming, boolean isOutgoing, String partnerName,
                                                String categoryName) {
        InvoiceDB invoiceDB = new InvoiceDB();
        List<Invoice> invoices = invoiceDB.getInvoicesByAll(id, startCreationDate, endCreationDate, startCompDate,
                endCompDate, startDeadlineDate, endDeadlineDate, minLimit, maxLimit, isIncoming, isOutgoing,
                partnerName, categoryName);
        Map<String, Object> result = new HashMap<>();

        if (invoices == null) {
            result.put("Message", "No invoices found.");
        } else {
            result.put("Message", String.format("%d invoices found.", invoices.size()));
            result.put("invoices", invoices);
        }
        System.out.println(result);
        return result;
    }

    @GetMapping("/invoiceByPartnerId")
    public Object getInvoicesByPartnerId(@RequestParam(defaultValue = "") String id) {
        InvoiceDB invoiceDB = new InvoiceDB();

        List<Invoice> invoices = invoiceDB.getInvoicesByPartnerId(id);

        if (invoices == null) {
            invoices = invoiceDB.getInvoiceByIdLike(id);
        }

        return invoices;
    }

    @PostMapping("/invoices/insert")
    public Object insertInvoice(@RequestBody Map<String, Object> body) {
        InvoiceDB invoiceDB = new InvoiceDB();

        String id = (String) body.get("id");
        Date creationDate = Date.valueOf((String) body.get("creationDate"));
        Date completionDate = Date.valueOf((String) body.get("completionDate"));
        Date paymentDeadline =  Date.valueOf((String) body.get("paymentDeadline"));
        long grandTotal = (Integer) body.get("grandTotal");
        boolean isIncoming = (Boolean) body.get("incoming");
        boolean isOutgoing = (Boolean) body.get("outgoing");
        String partnerName = (String) body.get("partnerName");
        String category = (String) body.get("category");

        Invoice insertedInvoice = invoiceDB.insertInvoice(new Invoice(id, creationDate, completionDate, paymentDeadline, grandTotal,
                isIncoming, isOutgoing, partnerName, category));

        if (insertedInvoice == null) {
            return "No invoice was inserted";
        }
        else {
            return insertedInvoice;
        }
    }

    @PutMapping("/invoices/update")
    public Object updateInvoice(@RequestBody Map<String, Object> body) {
        InvoiceDB invoiceDB = new InvoiceDB();

        String id = (String) body.get("id");
        Date creationDate = Date.valueOf((String) body.get("creationDate"));
        Date completionDate = Date.valueOf((String) body.get("completionDate"));
        Date paymentDeadline =  Date.valueOf((String) body.get("paymentDeadline"));
        long grandTotal = (Integer) body.get("grandTotal");
        boolean isIncoming = (Boolean) body.get("incoming");
        boolean isOutgoing = (Boolean) body.get("outgoing");
        String partnerName = (String) body.get("partnerName");
        String category = (String) body.get("category");

        Invoice updatedInvoice = invoiceDB.updateInvoice(new Invoice(id, creationDate, completionDate, paymentDeadline, grandTotal,
                isIncoming, isOutgoing, partnerName, category));

        if (updatedInvoice == null) {
            return "No invoice was updated";
        } else {
            return updatedInvoice;
        }
    }

    @PutMapping("/invoices/update/cd")
    public Object updateInvoiceCompletionDate(@RequestBody Map<String, Object> body) {
        InvoiceDB invoiceDB = new InvoiceDB();

        String id = (String) body.get("id");
        Date completionDate = Date.valueOf((String) body.get("completionDate"));

        Invoice updatedInvoice = invoiceDB.updateInvoiceCompletionDate(id, completionDate);

        if (updatedInvoice == null) {
            return "No invoice was updated";
        } else {
            return updatedInvoice;
        }
    }

    @PutMapping("/invoices/update/cat")
    public Object updateInvoiceCategories(@RequestBody Map<String, Object> body) {
        InvoiceDB invoiceDB = new InvoiceDB();

        String id = (String) body.get("id");
        String category = (String) body.get("category");

        Invoice updatedInvoice = invoiceDB.updateInvoiceCategories(id, category);

        if (updatedInvoice == null) {
            return "No invoice was updated";
        } else {
            return updatedInvoice;
        }
    }

    @DeleteMapping("/invoices")
    public Object deleteInvoice(@RequestParam String id) {

        InvoiceDB invoiceDB = new InvoiceDB();
        boolean isDeleted = invoiceDB.deleteInvoice(id);

        if (isDeleted) {
            return String.format("Invoice with ID %s was deleted.", id);
        } else {
            return String.format("Invoice with ID %s was not deleted.", id);
        }
    }
}
