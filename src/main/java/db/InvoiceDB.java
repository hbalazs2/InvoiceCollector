package db;

import model.Invoice;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InvoiceDB {
    private Connection connection = DBConnector.getInstance().getConnection();
    private String baseSql = "SELECT " +
            "invoices.id, " +
            "invoices.creation_date, " +
            "invoices.completion_date, " +
            "invoices.payment_deadline, " +
            "invoices.grand_total, " +
            "invoices.incoming, " +
            "invoices.outgoing, " +
            "partners.name AS partner_name, " +
            "categories.name AS category_name " +
            "FROM invoices " +
            "JOIN partners " +
            "ON invoices.partners_id = partners.id " +
            "JOIN categories " +
            "ON invoices.categories_id = categories.id ";

    public List<Invoice> getAllInvoices() {

        List<Invoice> invoices = new ArrayList<>();
        String sql = this.baseSql + ";";
        Invoice invoice;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                invoice = new Invoice(
                        result.getString("id"),
                        result.getDate("creation_date"),
                        result.getDate("completion_date"),
                        result.getDate("payment_deadline"),
                        result.getLong("grand_total"),
                        result.getBoolean("incoming"),
                        result.getBoolean("outgoing"),
                        result.getString("partner_name"),
                        result.getString("category_name")
                );
                invoices.add(invoice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return invoices;
    }

    public Invoice getInvoiceById(String id) {

        String sql = this.baseSql + "WHERE invoices.id = ?";
        Invoice invoice = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                invoice = new Invoice(
                        result.getString("id"),
                        result.getDate("completion_date"),
                        result.getDate("creation_date"),
                        result.getDate("payment_deadline"),
                        result.getLong("grand_total"),
                        result.getBoolean("incoming"),
                        result.getBoolean("outgoing"),
                        result.getString("partner_name"),
                        result.getString("category_name")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return invoice;
    }

    public List<Invoice> getInvoiceByIdLike(String id) {
        String sql = this.baseSql + "WHERE invoices.id LIKE ?";
        Invoice invoice;
        List<Invoice> invoices = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + id + "%");
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                invoice = new Invoice(
                        result.getString("id"),
                        result.getDate("completion_date"),
                        result.getDate("creation_date"),
                        result.getDate("payment_deadline"),
                        result.getLong("grand_total"),
                        result.getBoolean("incoming"),
                        result.getBoolean("outgoing"),
                        result.getString("partner_name"),
                        result.getString("category_name")
                );
                invoices.add(invoice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return invoices;
    }

    public List<Invoice> getInvoicesByAll(String id, Date startCreationDate, Date endCreationDate, Date startCompDate,
                                          Date endCompDate, Date startDeadlineDate, Date endDeadlineDate, int minLimit,
                                          int maxLimit, boolean isIncoming, boolean isOutgoing, String partnerName,
                                          String categoryName) {
        String sql = this.baseSql +
                "WHERE invoices.id LIKE ? " +
                "AND invoices.creation_date BETWEEN ? AND ? " +
                "AND invoices.completion_date BETWEEN ? AND ? " +
                "AND invoices.payment_deadline BETWEEN ? AND ? " +
                "AND invoices.grand_total BETWEEN ? AND ? " +
                "AND invoices.incoming = ? " +
                "AND invoices.outgoing = ? " +
                "AND partners.name LIKE ? " +
                "AND categories.name LIKE ?";
        Invoice invoice;
        List<Invoice> invoices = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + id + "%");
            preparedStatement.setDate(2, startCreationDate);
            preparedStatement.setDate(3, endCreationDate);
            preparedStatement.setDate(4, startCompDate);
            preparedStatement.setDate(5, endCompDate);
            preparedStatement.setDate(6, startDeadlineDate);
            preparedStatement.setDate(7, endDeadlineDate);
            preparedStatement.setInt(8, minLimit);
            preparedStatement.setInt(9, maxLimit);
            preparedStatement.setBoolean(10, isIncoming);
            preparedStatement.setBoolean(11, isOutgoing);
            preparedStatement.setString(12, "%" + partnerName + "%");
            preparedStatement.setString(13, "%" + categoryName + "%");
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                invoice = new Invoice(
                        result.getString("id"),
                        result.getDate("completion_date"),
                        result.getDate("creation_date"),
                        result.getDate("payment_deadline"),
                        result.getLong("grand_total"),
                        result.getBoolean("incoming"),
                        result.getBoolean("outgoing"),
                        result.getString("partner_name"),
                        result.getString("category_name")
                );
                invoices.add(invoice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return invoices;
    }

    public Invoice insertInvoice(Invoice invoice) {
        String sql = "INSERT INTO invoices VALUES " +
                "(?, ?, ?, ?, ?, ?, ?, " +
                "(SELECT partners.id FROM invoicecollector.partners " +
                "WHERE partners.name = ?), " +
                "(SELECT categories.id FROM invoicecollector.categories " +
                "WHERE categories.name = ?));";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, invoice.getId());
            preparedStatement.setDate(2, (Date) invoice.getCreationDate());
            preparedStatement.setDate(3, (Date) invoice.getCompletionDate());
            preparedStatement.setDate(4, (Date) invoice.getPaymentDeadline());
            preparedStatement.setLong(5, invoice.getGrandTotal());
            preparedStatement.setBoolean(6, invoice.isIncoming());
            preparedStatement.setBoolean(7, invoice.isOutgoing());
            preparedStatement.setString(8, invoice.getPartnerName());
            preparedStatement.setString(9, invoice.getCategory());
            preparedStatement.executeUpdate();

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return getInvoiceById(invoice.getId());
    }

    public Invoice updateInvoiceCompletionDate(String id, Date completionDate) {
        String sql = "UPDATE invoices " +
                "SET completion_date = ? " +
                "WHERE invoices.id = ?;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDate(1, completionDate);
            preparedStatement.setString(2, id);
            preparedStatement.executeUpdate();

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return getInvoiceById(id);
    }

    public Invoice updateInvoiceCategories(String id, String category) {
        String sql = "UPDATE invoices " +
                "JOIN categories " +
                "ON invoices.categories_id = categories.id " +
                "SET invoices.categories_id = " +
                "(SELECT id FROM categories WHERE categories.name LIKE ?) " +
                "WHERE invoices.id = ?;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + category + "%");
            preparedStatement.setString(2, id);
            preparedStatement.executeUpdate();

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return getInvoiceById(id);
    }

    public Invoice updateInvoice(Invoice invoice) {
        String sql = "UPDATE invoices SET id = ?, " +
                "completion_date = ?, " +
                "payment_deadline = ?, " +
                "grand_total = ?, " +
                "incoming = ?, " +
                "outgoing = ?, " +
                "partners_id = (SELECT partners.id FROM invoicecollector.partners WHERE partners.name = ?), " +
                "categories_id = (SELECT categories.id FROM invoicecollector.categories WHERE categories.name = ?) " +
                "WHERE invoices.id = ?;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, invoice.getId());
            preparedStatement.setDate(2, (Date) invoice.getCreationDate());
            preparedStatement.setDate(3, (Date) invoice.getCompletionDate());
            preparedStatement.setDate(4, (Date) invoice.getPaymentDeadline());
            preparedStatement.setLong(5, invoice.getGrandTotal());
            preparedStatement.setBoolean(6, invoice.isIncoming());
            preparedStatement.setBoolean(7, invoice.isOutgoing());
            preparedStatement.setString(8, invoice.getPartnerName());
            preparedStatement.setString(9, invoice.getCategory());
            preparedStatement.setString(10, invoice.getId());
            preparedStatement.executeUpdate();

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return getInvoiceById(invoice.getId());
    }

    public boolean deleteInvoice(String id) {
        String sql = "DELETE FROM invoices " +
                "WHERE invoices.id = ?";
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
