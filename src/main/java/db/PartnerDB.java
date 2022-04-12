package db;

import model.Partner;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PartnerDB {
    private Connection connection = DBConnector.getInstance().getConnection();

    public List<Partner> getAllPartners() {

        List<Partner> partners = new ArrayList<>();
        String sql = "SELECT * FROM partners";
        Partner partner;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                partner = new Partner(
                        result.getLong("id"),
                        result.getString("name"),
                        result.getString("country_code"),
                        result.getString("postal_code"),
                        result.getString("city"),
                        result.getString("address"),
                        result.getDate("connection_date")
                );
                partners.add(partner);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return partners;
    }

    public Partner getPartnerById(long id) {

        String sql = "SELECT * FROM partners WHERE id = ?";
        Partner partner = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                partner = new Partner(
                        result.getLong("id"),
                        result.getString("name"),
                        result.getString("country_code"),
                        result.getString("postal_code"),
                        result.getString("city"),
                        result.getString("address"),
                        result.getDate("connection_date")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return partner;
    }

    public List<Partner> getPartnersByName(String name) {

        String sql = "SELECT * FROM partners WHERE name LIKE ?";
        List<Partner> partners = new ArrayList<>();
        Partner partner = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + name + "%");
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                partner = new Partner(
                        result.getLong("id"),
                        result.getString("name"),
                        result.getString("country_code"),
                        result.getString("postal_code"),
                        result.getString("city"),
                        result.getString("address"),
                        result.getDate("connection_date")
                );
                partners.add(partner);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return partners;
    }

    public List<Partner> getPartnersByCountryCode(String countryCode) {

        String sql = "SELECT * FROM partners WHERE country_code LIKE ?";
        List<Partner> partners = new ArrayList<>();
        Partner partner = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + countryCode + "%");
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                partner = new Partner(
                        result.getLong("id"),
                        result.getString("name"),
                        result.getString("country_code"),
                        result.getString("postal_code"),
                        result.getString("city"),
                        result.getString("address"),
                        result.getDate("connection_date")
                );
                partners.add(partner);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return partners;
    }

    public List<Partner> getPartnersByCity(String city) {

        String sql = "SELECT * FROM partners WHERE city LIKE ?";
        List<Partner> partners = new ArrayList<>();
        Partner partner = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + city + "%");
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                partner = new Partner(
                        result.getLong("id"),
                        result.getString("name"),
                        result.getString("country_code"),
                        result.getString("postal_code"),
                        result.getString("city"),
                        result.getString("address"),
                        result.getDate("connection_date")
                );
                partners.add(partner);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return partners;
    }

    public Partner insertPartner(Partner partner) {
        String sql = "INSERT INTO partners VALUES (DEFAULT, ?, ?, ?, ?, ?, ?);";
        long id = -1;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, partner.getName());
            preparedStatement.setString(2, partner.getCountryCode());
            preparedStatement.setString(3, partner.getPostalCode());
            preparedStatement.setString(4, partner.getCity());
            preparedStatement.setString(5, partner.getAddress());
            preparedStatement.setDate(6, (Date) partner.getConnectionDate());
            preparedStatement.executeUpdate();

            ResultSet result = preparedStatement.getGeneratedKeys();
            if (result.next()) {
                id = result.getLong(1);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return getPartnerById(id);
    }

    public Partner updatePartner(Partner partner) {
        String sql = "UPDATE partners SET id = ? " +
                "name = ?, " +
                "country_code = ?, " +
                "postal_code = ?, " +
                "city = ?, " +
                "address = ?, " +
                "connection_date = ? " +
                "WHERE id = ?;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, partner.getId());
            preparedStatement.setString(2, partner.getName());
            preparedStatement.setString(3, partner.getCountryCode());
            preparedStatement.setString(4, partner.getPostalCode());
            preparedStatement.setString(5, partner.getCity());
            preparedStatement.setString(6, partner.getAddress());
            preparedStatement.setDate(7, (Date) partner.getConnectionDate());
            preparedStatement.setLong(8, partner.getId());
            preparedStatement.executeUpdate();

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return getPartnerById(partner.getId());
    }

    public void updatePartnersCity(long id, String city, String postalCode) {
        String sql = "UPDATE partners SET city = ?, " +
                "postal_code = ? " +
                "WHERE id = ?;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, city);
            preparedStatement.setString(2, postalCode);
            preparedStatement.setLong(3, id);
            preparedStatement.executeUpdate();

        } catch (Exception exception) {
            exception.printStackTrace();
        }

//        return getPartnerById(id);
    }

    public void updatePartnersAddress(long id, String address) {
        String sql = "UPDATE partners SET address = ? " +
                "WHERE id = ?;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, address);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();

        } catch (Exception exception) {
            exception.printStackTrace();
        }

//        return getPartnerById(id);
    }

    public void updatePartnersName(long id, String name) {
        String sql = "UPDATE partners SET name = ? " +
                "WHERE id = ?;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();

        } catch (Exception exception) {
            exception.printStackTrace();
        }

//        return getPartnerById(id);
    }

    public boolean deletePartner(long id) {
        String sql = "DELETE FROM partners WHERE ID = ?;";
        long deletedRow = -1;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            deletedRow = preparedStatement.executeUpdate();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return deletedRow == 1;
    }

}