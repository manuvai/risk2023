package res.utils;

import res.utils.exceptions.DbConnectionException;
import res.utils.exceptions.NoDbDriverFoundException;

import java.sql.*;

public class Database {

    public static void main(String[] args) throws SQLException {
        Connection conn = getConnection();

        Statement statement = conn.createStatement();

        ResultSet rs = statement.executeQuery("SELECT * FROM personne");

        while (rs.next()) {
            int id = rs.getInt("id");
            String nom = rs.getString("nom");
            String prenom = rs.getString("prenom");

            System.out.println("id : ".concat(Integer.toString(id)));
            System.out.println("nom : ".concat(nom));
            System.out.println("prenom : ".concat(prenom));
        }

        rs.close();
        conn.close();

    }

    public static Connection getConnection() {
        Connection connection;
        String driverClassName = Config.get("db.driver");

        try {
            Class.forName(driverClassName);

        } catch (ClassNotFoundException e) {
            throw new NoDbDriverFoundException(e);
        }

        try {
            connection = DriverManager.getConnection(
                    Config.get("db.url"),
                    Config.get("db.username"),
                    Config.get("db.userpass"));
        } catch (SQLException e) {
            throw new DbConnectionException(e);
        }

        return connection;
    }
}
