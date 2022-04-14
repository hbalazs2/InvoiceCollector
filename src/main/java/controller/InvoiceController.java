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
            } else {
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
        } else {
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
        } else {
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
        } else {
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
        } else {
            result.put("Message", "Following overdue Invoices found.");
            result.put("invoices", invoices);
        }
        return result;
    }

    @GetMapping("/invoiceUp")
    public Map<String, Object> getUnpaidInvoices() {
        InvoiceDB invoiceDB = new InvoiceDB();
        List<Invoice> invoices = invoiceDB.getUnpaidInvoices();
        Map<String, Object> result = new HashMap<>();

        if (invoices == null) {
            result.put("Message", "No unpaid invoices found.");
        } else {
            result.put("Message", "Following unpaid invoices found.");
            result.put("invoices", invoices);
        }
        return result;
    }

    @GetMapping("/invoiceGTU")
    public Map<String, Object> getInvoicesByGrandTotalUnder(@RequestParam(defaultValue = "") long limit) {
        InvoiceDB invoiceDB = new InvoiceDB();
        List<Invoice> invoices = invoiceDB.getInvoicesByGrandTotalUnder(limit);
        Map<String, Object> result = new HashMap<>();

        if (invoices == null) {
            result.put("Message", String.format("No invoices under limit %d found.", limit));
        } else {
            result.put("Message", String.format("Following invoices found under limit %d.", limit));
            result.put("invoices", invoices);
        }
        return result;
    }

    @GetMapping("/invoiceGTO")
    public Map<String, Object> getInvoicesByGrandTotalOver(@RequestParam(defaultValue = "") long limit) {
        InvoiceDB invoiceDB = new InvoiceDB();
        List<Invoice> invoices = invoiceDB.getInvoicesByGrandTotalOver(limit);
        Map<String, Object> result = new HashMap<>();

        if (invoices == null) {
            result.put("Message", String.format("No invoices over limit %d found.", limit));
        } else {
            result.put("Message", String.format("Following invoices found over limit %d.", limit));
            result.put("invoices", invoices);
        }
        return result;
    }

    @GetMapping("/invoiceGTB")
    public Map<String, Object> getInvoicesByGrandTotalBetween(@RequestParam(defaultValue = "") long maxLimit, long minLimit) {
        InvoiceDB invoiceDB = new InvoiceDB();
        List<Invoice> invoices = invoiceDB.getInvoicesByGrandTotalBetween(minLimit, maxLimit);
        Map<String, Object> result = new HashMap<>();

        if (invoices == null) {
            result.put("Message", String.format("No invoices between %d and %d found.", minLimit, maxLimit));
        } else {
            result.put("Message", String.format("Following invoices found between %d and %d.", minLimit, maxLimit));
            result.put("invoices", invoices);
        }
        return result;
    }

    @GetMapping("/invoiceIn")
    public Map<String, Object> getIncomingInvoices() {
        InvoiceDB invoiceDB = new InvoiceDB();
        List<Invoice> invoices = invoiceDB.getIncomingInvoices();
        Map<String, Object> result = new HashMap<>();

        if (invoices == null) {
            result.put("Message", "No incoming invoices found.");
        } else {
            result.put("Message", "Following incoming invoices found.");
            result.put("invoices", invoices);
        }
        return result;
    }

    @GetMapping("/invoiceOut")
    public Map<String, Object> getOutgoingInvoices() {
        InvoiceDB invoiceDB = new InvoiceDB();
        List<Invoice> invoices = invoiceDB.getOutgoingInvoices();
        Map<String, Object> result = new HashMap<>();

        if (invoices == null) {
            result.put("Message", "No outgoing invoices found.");
        } else {
            result.put("Message", "Following outgoing invoices found.");
            result.put("invoices", invoices);
        }
        return result;
    }

    @GetMapping("/invoicesPartner/{partner}")
    public Map<String, Object> getInvoicesByPartnerName(@PathVariable String partner) {
        InvoiceDB invoiceDB = new InvoiceDB();
        List<Invoice> invoices = invoiceDB.getInvoicesByPartnerName(partner);
        Map<String, Object> result = new HashMap<>();

        if (invoices == null) {
            result.put("Message", String.format("No invoices found for partner %s.", partner));
        } else {
            result.put("Message", String.format("Following invoices found for partner %s.", partner));
            result.put("invoices", invoices);
        }
        return result;
    }

    @GetMapping("/invoicesCat/{category}")
    public Map<String, Object> getInvoicesByCategory(@PathVariable String category) {
        InvoiceDB invoiceDB = new InvoiceDB();
        List<Invoice> invoices = invoiceDB.getInvoicesByCategory(category);
        Map<String, Object> result = new HashMap<>();

        if (invoices == null) {
            result.put("Message", String.format("No invoices found in category %s.", category));
        } else {
            result.put("Message", String.format("Following invoices found in category %s.", category));
            result.put("invoices", invoices);
        }
        return result;
    }

    @PostMapping("/invoices")
    public Object insertInvoice(@RequestBody Map<String, Object> body) {
        InvoiceDB invoiceDB = new InvoiceDB();

        String id = (String) body.get("id");
        Date creationDate = Date.valueOf((String) body.get("creationDate"));
        Date completionDate = Date.valueOf((String) body.get("completionDate"));
        Date paymentDeadline =  Date.valueOf((String) body.get("paymentDeadline"));
        long grandTotal = (Integer) body.get("grandTotal");
        boolean isIncoming = (Boolean) body.get("incoming");
        boolean isOutgoing = (Boolean) body.get("outgoing");
        long partnersId = (Integer) body.get("partnersId");
        long categoryId = (Integer) body.get("categoryId");

        Invoice insertedInvoice = invoiceDB.insertInvoice(new Invoice(id, creationDate, completionDate, paymentDeadline, grandTotal,
                isIncoming, isOutgoing, partnersId, categoryId));

        if (insertedInvoice == null) {
            return "No invoice was inserted";
        }
        else {
            return insertedInvoice;
        }
    }

    @PutMapping("/invoices")
    public Object updateInvoice(@RequestBody Map<String, Object> body) {
        InvoiceDB invoiceDB = new InvoiceDB();

        String id = (String) body.get("id");
        Date creationDate = Date.valueOf((String) body.get("creationDate"));
        Date completionDate = Date.valueOf((String) body.get("completionDate"));
        Date paymentDeadline =  Date.valueOf((String) body.get("paymentDeadline"));
        long grandTotal = (Integer) body.get("grandTotal");
        boolean isIncoming = (Boolean) body.get("incoming");
        boolean isOutgoing = (Boolean) body.get("outgoing");
        long partnersId = (Integer) body.get("partnersId");
        long categoryId = (Integer) body.get("categoryId");

        Invoice updatedInvoice = invoiceDB.updateInvoice(new Invoice(id, creationDate, completionDate, paymentDeadline, grandTotal,
                isIncoming, isOutgoing, partnersId, categoryId));

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
        System.out.println(id);
        String category = (String) body.get("category");
        System.out.println(category);

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
