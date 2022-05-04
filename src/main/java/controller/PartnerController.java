package controller;


import db.InvoiceDB;
import db.PartnerDB;
import model.Invoice;
import model.Partner;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins="*")
@RestController
public class PartnerController {

    @GetMapping("/partners")
    public Map<String, Object> getAllPartners() {
        PartnerDB partnerDB = new PartnerDB();
        List<Partner> partners = partnerDB.getAllPartners();
        Map<String, Object> result = new HashMap<>();
        result.put("partners", partners);
        result.put("count", partners.size());

        return result;
    }

    @GetMapping("/partners/{id}")
    public Map<String, Object> getPartnerById(@PathVariable long id) {
        PartnerDB partnerDB = new PartnerDB();
        Partner partner = partnerDB.getPartnerById(id);
        Map<String, Object> result = new HashMap<>();

        if (partner == null) {
            result.put("Message", String.format("No partner found with ID %d.", id));
        } else {
            result.put("Partner", partner);
        }
        return result;
    }

    @GetMapping("/partners/searchByName")
    public Map<String, Object> getPartnersByName(@RequestParam String name) {
        PartnerDB partnerDB = new PartnerDB();
        List <Partner> partners = partnerDB.getPartnersByName(name);;
        Map<String, Object> result = new HashMap<>();

        if (partners == null) {
            result.put("Message", String.format("No partner found with name like %s.", name));
        } else {
            result.put("Partner", partners);
            result.put("Message", String.format("%d partner(s) found with name like %s.", partners.size(), name));
        }
        return result;
    }
    @GetMapping("/partners/searchByCountryCode")
    public Map<String, Object> getPartnersByCountryCode(@RequestParam String countryCode) {
        PartnerDB partnerDB = new PartnerDB();
        List <Partner> partners = partnerDB.getPartnersByCountryCode(countryCode);
        Map<String, Object> result = new HashMap<>();

        if (partners == null) {
            result.put("Message", String.format("No partner found with country code like %s.", countryCode));
        } else {
            result.put("Partner", partners);
            result.put("Message", String.format("%d partner(s) found with country code like %s.", partners.size(), countryCode));
        }
        return result;
    }

    @GetMapping("/partners/searchByCity")
    public Map<String, Object> getPartnersByCity(@RequestParam String city) {
        PartnerDB partnerDB = new PartnerDB();
        List <Partner> partners = partnerDB.getPartnersByCity(city);
        Map<String, Object> result = new HashMap<>();

        if (partners == null) {
            result.put("Message", String.format("No partner found in city like %s.", city));
        } else {
            result.put("Partner", partners);
            result.put("Message", String.format("%d partner(s) found in city like %s.", partners.size(), city));
        }
        return result;
    }

    @PostMapping("/partners")
    public Object insertPartner(@RequestBody Map<String, Object> body) {
        PartnerDB partnerDB = new PartnerDB();

        String name = (String) body.get("name");
        String countryCode = (String) body.get("countryCode");
        String postalCode = (String) body.get("postalCode");
        String city = (String) body.get("city");
        String address = (String) body.get("address");
        Date connectionDate = Date.valueOf((String) body.get("connectionDate"));

        Partner insertedPartner = partnerDB.insertPartner(new Partner(name, countryCode, postalCode, city, address, connectionDate));

        if (insertedPartner == null) {
            return "No partner was inserted";
        }
        else {
            return insertedPartner;
        }
    }

    @PutMapping("/partners")
    public Object updatePartner(@RequestBody Map<String, Object> body) {
        PartnerDB partnerDB = new PartnerDB();

        long id = (Integer) body.get("id");
        String name = (String) body.get("name");
        String countryCode = (String) body.get("countryCode");
        String postalCode = (String) body.get("postalCode");
        String city = (String) body.get("city");
        String address = (String) body.get("address");
        Date connectionDate = Date.valueOf((String) body.get("connectionDate"));

        Partner updatedPartner = partnerDB.updatePartner(new Partner(id, name, countryCode, postalCode, city, address, connectionDate));

        if (updatedPartner == null) {
            return "No partner was updated";
        }
        else {
            return updatedPartner;
        }
    }

    @PutMapping("/partners/updateCity")
    public Object updatePartnersCity(@RequestBody Map<String, Object> body) {
        PartnerDB partnerDB = new PartnerDB();

        long id = (Integer) body.get("id");
        String postalCode = (String) body.get("postalCode");
        String city = (String) body.get("city");


        Partner updatedPartner = partnerDB.updatePartnersCity(id, city, postalCode);

        if (updatedPartner == null) {
            return "No partner was updated";
        }
        else {
            return updatedPartner;
        }
    }

    @PutMapping("/partners/updateAddress")
    public Object updatePartnersAddress(@RequestBody Map<String, Object> body) {
        PartnerDB partnerDB = new PartnerDB();

        long id = (Integer) body.get("id");
        String address = (String) body.get("address");

        Partner updatedPartner = partnerDB.updatePartnersAddress(id, address);

        if (updatedPartner == null) {
            return "No partner was updated";
        }
        else {
            return updatedPartner;
        }
    }

    @PutMapping("/partners/updateName")
    public Object updatePartnersName(@RequestBody Map<String, Object> body) {
        PartnerDB partnerDB = new PartnerDB();

        long id = (Integer) body.get("id");
        String name = (String) body.get("name");

        Partner updatedPartner = partnerDB.updatePartnersName(id, name);

        if (updatedPartner == null) {
            return "No partner was updated";
        }
        else {
            return updatedPartner;
        }
    }

    @DeleteMapping("/partners/{id}")
    public Object deleteInvoice(@PathVariable long id) {

        PartnerDB partnerDB = new PartnerDB();
        boolean isDeleted = partnerDB.deletePartner(id);

        if (isDeleted) {
            return String.format("Partner with ID %s was deleted.", id);
        } else {
            return String.format("Partner with ID %s was not deleted.", id);
        }
    }
}
