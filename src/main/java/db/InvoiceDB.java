package db;

import model.Invoice;

import java.sql.*;
import java.time.LocalDate;
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
                        result.getDate("creation_date"),
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
                        result.getDate("creation_date"),
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

    public List<Invoice> getInvoicesBetweenDatesByCompletionDate(Date startCompDate, Date endCompDate) {

        List<Invoice> invoices = new ArrayList<>();
        String sql = "SELECT * FROM invoices " +
                "WHERE completion_date " +
                "BETWEEN ? AND ?;";
        Invoice invoice;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDate(1, startCompDate);
            preparedStatement.setDate(2, endCompDate);
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

    public List<Invoice> getInvoicesBetweenDatesByCreationDate(Date startCreationDate, Date endCreationDate) {

        List<Invoice> invoices = new ArrayList<>();
        String sql = "SELECT * FROM invoices " +
                "WHERE creation_date " +
                "BETWEEN ? AND ?;";
        Invoice invoice;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDate(1, startCreationDate);
            preparedStatement.setDate(2, endCreationDate);
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

    public List<Invoice> getInvoicesBetweenDatesByDeadlineDate(Date startDeadlineDate, Date endDeadlineDate) {

        List<Invoice> invoices = new ArrayList<>();
        String sql = "SELECT * FROM invoices " +
                "WHERE payment_deadline " +
                "BETWEEN ? AND ?;";
        Invoice invoice;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDate(1, startDeadlineDate);
            preparedStatement.setDate(2, endDeadlineDate);
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

    public List<Invoice> getOverdueInvoices() {

        Date now = Date.valueOf(LocalDate.now());
        List<Invoice> invoices = new ArrayList<>();
        String sql = "SELECT * FROM invoices " +
                "WHERE payment_deadline <= ?;";
        Invoice invoice;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDate(1, now);
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

    public List<Invoice> getInvoicesByGrandTotalUnder(long limit) {

        List<Invoice> invoices = new ArrayList<>();
        String sql = "SELECT * FROM invoices " +
                "WHERE grand_total <= ?;";
        Invoice invoice;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, limit);
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

    public List<Invoice> getInvoicesByGrandTotalOver(long limit) {

        List<Invoice> invoices = new ArrayList<>();
        String sql = "SELECT * FROM invoices " +
                "WHERE grand_total >= ?;";
        Invoice invoice;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, limit);
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

    public List<Invoice> getInvoicesByGrandTotalBetween(long minLimit, long maxLimit) {

        List<Invoice> invoices = new ArrayList<>();
        String sql = "SELECT * FROM invoices " +
                "WHERE grand_total BETWEEN ? AND ?;";
        Invoice invoice;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, minLimit);
            preparedStatement.setLong(2, maxLimit);
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

    public List<Invoice> getIncomingInvoices() {

        List<Invoice> invoices = new ArrayList<>();
        String sql = "SELECT * FROM invoices " +
                "WHERE incoming = true;";
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

    public List<Invoice> getOutgoingInvoices() {

        List<Invoice> invoices = new ArrayList<>();
        String sql = "SELECT * FROM invoices " +
                "WHERE outgoing = true;";
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

    public List<Invoice> getInvoicesByPartnerName(String partnerName) {

        List<Invoice> invoices = new ArrayList<>();
        String sql = "SELECT invoices.* FROM invoices " +
                "JOIN partners " +
                "ON invoices.partners_id = partners.id " +
                "WHERE partners.name LIKE ?;";
        Invoice invoice;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + partnerName + "%");
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

    public List<Invoice> getInvoicesByCategory(String category) {

        List<Invoice> invoices = new ArrayList<>();
        String sql = "SELECT * FROM invoices " +
                "JOIN categories " +
                "ON invoices.categories_id = categories.id " +
                "WHERE categories.name LIKE ?;";
        Invoice invoice;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + category + "%");
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

    public Invoice insertInvoice(Invoice invoice) {
        String sql = "INSERT INTO invoices VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, invoice.getId());
            preparedStatement.setDate(2, (Date) invoice.getCreationDate());
            preparedStatement.setDate(3, (Date) invoice.getCompletionDate());
            preparedStatement.setDate(4, (Date) invoice.getPaymentDeadline());
            preparedStatement.setLong(5, invoice.getGrandTotal());
            preparedStatement.setBoolean(6, invoice.isIncoming());
            preparedStatement.setBoolean(7, invoice.isOutgoing());
            preparedStatement.setLong(8, invoice.getPartnersId());
            preparedStatement.setLong(9, invoice.getCategoriesId());
            preparedStatement.executeUpdate();

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return getInvoiceById(invoice.getId());
    }

    public Invoice updateInvoiceCompletionDate(String id, Date completionDate) {
        String sql = "UPDATE invoices SET `completion_date` = ? " +
                "WHERE `id` = ?;";

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
                "SET invoices.categories_id = (SELECT id FROM categories WHERE categories.name LIKE ?) " +
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
                "partners_id = ?, " +
                "categories_id = ? " +
                "WHERE id = ?;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, invoice.getId());
            preparedStatement.setDate(2, (Date) invoice.getCreationDate());
            preparedStatement.setDate(3, (Date) invoice.getCompletionDate());
            preparedStatement.setDate(4, (Date) invoice.getPaymentDeadline());
            preparedStatement.setLong(5, invoice.getGrandTotal());
            preparedStatement.setBoolean(6, invoice.isIncoming());
            preparedStatement.setBoolean(7, invoice.isOutgoing());
            preparedStatement.setLong(8, invoice.getPartnersId());
            preparedStatement.setLong(9, invoice.getCategoriesId());
            preparedStatement.setString(10, invoice.getId());
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
