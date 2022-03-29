package db;

import model.Invoice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InvoiceDB {
    private Connection connection = DBConnector.getInstance().getConnection();

    public List<Invoice> getAllInvoices() {

        List<Invoice> invoices = new ArrayList<>();
        String sql = "SELECT * FROM invoices";
        Invoice invoice;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                invoice = new Invoice(
                        result.getLong("id"),
                        result.getDate("completion_date"),
                        result.getDate("payment_deadline"),
                        result.getLong("grand_total"),
                        result.getLong("partners_id")
                );
                invoices.add(invoice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return invoices;
    }
}
