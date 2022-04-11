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
                        result.getBoolean("incoming"),
                        result.getBoolean("outgoing"),
                        result.getLong("partners_id"),
                        result.getLong("categories_id")
                );
                invoices.add(invoice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return invoices;
    }

    public Invoice getInvoiceById(String id) {

        String sql = "SELECT * FROM invoices WHERE id = ?";
        Invoice invoice = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                invoice = new Invoice(
                        result.getString("id"),
                        result.getDate("completion_date"),
                        result.getDate("payment_deadline"),
                        result.getLong("grand_total"),
                        result.getBoolean("incoming"),
                        result.getBoolean("outgoing"),
                        result.getLong("partners_id"),
                        result.getLong("categories_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return invoice;
    }

    public Invoice getInvoiceByCategory(String category) {

        String sql = "SELECT * FROM invoices WHERE id = ?";
        Invoice invoice = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, category);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                invoice = new Invoice(
                        result.getString("id"),
                        result.getDate("completion_date"),
                        result.getDate("payment_deadline"),
                        result.getLong("grand_total"),
                        result.getBoolean("incoming"),
                        result.getBoolean("outgoing"),
                        result.getLong("partners_id"),
                        result.getLong("categories_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return invoice;
    }

    public Invoice insertInvoice(Invoice invoice) {
        String sql = "INSERT INTO invoices VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, invoice.getId());
            preparedStatement.setDate(2, (Date) invoice.getCompletionDate());
            preparedStatement.setDate(3, (Date) invoice.getPaymentDeadline());
            preparedStatement.setLong(4, invoice.getGrandTotal());
            preparedStatement.setBoolean(5, invoice.getIsIncoming());
            preparedStatement.setBoolean(6, invoice.getIsOutgoing());
            preparedStatement.setLong(7, invoice.getPartnersId());
            preparedStatement.setLong(8, invoice.getCategoriesId());
            preparedStatement.executeUpdate();

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return getInvoiceById(invoice.getId());
    }

    public Invoice updateInvoice(Invoice invoice) {
        String sql = "UPDATE invoices SET `id` = ?, " +
                "`completion_date` = ?, " +
                "`payment_deadline` = ?, " +
                "`grand_total` = ?, " +
                "`incoming` = ?, " +
                "`outgoing` = ?, " +
                "`partners_id` = ?, " +
                "`categories_id` = ? " +
                "WHERE `id` = ?;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, invoice.getId());
            preparedStatement.setDate(2, (Date) invoice.getCompletionDate());
            preparedStatement.setDate(3, (Date) invoice.getPaymentDeadline());
            preparedStatement.setLong(4, invoice.getGrandTotal());
            preparedStatement.setBoolean(5, invoice.getIsIncoming());
            preparedStatement.setBoolean(6, invoice.getIsOutgoing());
            preparedStatement.setLong(7, invoice.getPartnersId());
            preparedStatement.setLong(8, invoice.getCategoriesId());
            preparedStatement.setString(9, invoice.getId());
            preparedStatement.executeUpdate();

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return getInvoiceById(invoice.getId());
    }

    public boolean deleteInvoice(String id) {
        String sql = "DELETE FROM invoices WHERE id = ?";
        int deletedRow = -1;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            deletedRow = preparedStatement.executeUpdate();

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return deletedRow == 1;
    }
}
