package db;

import model.Invoice;

import java.sql.*;
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
                        result.getString("id"),
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

    public Invoice getInvoiceById(long id) {

        String sql = "SELECT * FROM invoices WHERE id = ?";
        Invoice invoice = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                invoice = new Invoice(
                        result.getString("id"),
                        result.getDate("completion_date"),
                        result.getDate("payment_deadline"),
                        result.getLong("grand_total"),
                        result.getLong("partners_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return invoice;
    }

    public Invoice insertInvoice(Invoice invoice) {
        String sql = "INSERT INTO invoices VALUES (?, ?, ?, ?, ?);";
        long insertedId = -1;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, invoice.getId());
            preparedStatement.setDate(2, (Date) invoice.getCompletionDate());
            preparedStatement.setDate(3, (Date) invoice.getPaymentDeadline());
            preparedStatement.setLong(4, invoice.getGrandTotal());
            preparedStatement.setLong(5, invoice.getPartnersId());
            preparedStatement.executeUpdate();

            ResultSet result = preparedStatement.getGeneratedKeys();

            if (result.next()) {
                insertedId = result.getLong(1);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return getInvoiceById(insertedId);
    }

    public Invoice updateInvoice(Invoice invoice) {
        String sql = "UPDATE invoices SET (DEFAULT, ?, ?, ?, ?);";
        long insertedId = -1;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setDate(1, (Date) invoice.getCompletionDate());
            preparedStatement.setDate(2, (Date) invoice.getPaymentDeadline());
            preparedStatement.setLong(3, invoice.getGrandTotal());
            preparedStatement.setLong(4, invoice.getPartnersId());
            preparedStatement.executeUpdate();

            ResultSet result = preparedStatement.getGeneratedKeys();

            if (result.next()) {
                insertedId = result.getLong(1);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return getInvoiceById(insertedId);
    }
}
