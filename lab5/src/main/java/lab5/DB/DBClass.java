package lab5.DB;

import java.util.Scanner;
import java.sql.*;

public class DBClass implements DBInterface{
    private Connection connection;
    private PreparedStatement statement;
    private String query;
    private final String dbName = "lab5";
    private final String dbTable = "goods";
    private final String dbTableStruct = "id INT NOT NULL PRIMARY KEY AUTO_INCREMENT," +
            "prodid INT NOT NULL UNIQUE ," +
            "title VARCHAR(20) NOT NULL UNIQUE ," +
            "price INT NOT NULL";

    public DBClass() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dbName + "?user=root&password=root&useSSL=false&serverTimezone=UTC");
            if (connection.isValid(1)) {
                System.out.println("Connection established.");
            }
        } catch (SQLException e) {
            printException(e);
        }
    }

    public void create() {
        query = "CREATE TABLE IF NOT EXISTS " + dbTable + "(" + dbTableStruct + ")";
        executeQuery("create");
    }

    public void clear() {
        query = "DROP TABLE " + dbTable;
        executeQuery("clear");
        create();
    }

    public void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            printException(e, "disconnect");
        }
    }

    public void add(Scanner s) {
        String title;
        if (s.hasNext()) {
            title = s.next();
        } else {
            System.out.println("Incorrect title!");
            return;
        }

        int price;
        if (s.hasNextInt()) {
            price = s.nextInt();
        } else {
            System.out.println("Incorrect price!");
            return;
        }

        addItem(title, price);
    }

    public void delete(Scanner s) {
        if (s.hasNext()) {
            deleteItem(s.next());
        } else {
            System.out.println("Incorrect title!");
        }
    }

    public void show_all(Scanner s) {
        query = "SELECT prodid, title, price FROM " + dbTable;
        try {
            statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                System.out.print(rs.getString(2));
                System.out.print("(" + rs.getInt(1) + "): ");
                System.out.println(rs.getInt(3) + " units.");
            }
        } catch (SQLException e) {
            printException(e, "show_all");
        }
    }

    public void price(Scanner s) {
        query = "SELECT price FROM " + dbTable + " WHERE title = ? LIMIT 1";
        try {
            statement = connection.prepareStatement(query);

            if (s.hasNext()) {
                statement.setString(1, s.next());
            } else {
                System.out.println("Incorrect title!");
                return;
            }

            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                System.out.println("Price is " + rs.getInt(1));
            } else {
                System.out.println("No such goods with given title.");
            }
        } catch (SQLException e) {
            printException(e, "price");
        }
    }

    public void change_price(Scanner s) {
        String title;
        if (s.hasNext()) {
            title = s.next();
        } else {
            System.out.println("Incorrect title!");
            return;
        }

        int price;
        if (s.hasNextInt()) {
            price = s.nextInt();
        } else {
            System.out.println("Incorrect price!");
            return;
        }

        updateItem(title, price);
    }

    public void filter_by_price(Scanner s) {
        query = "SELECT prodid, title, price FROM " + dbTable + " WHERE price BETWEEN ? AND ?";
        try {
            statement = connection.prepareStatement(query);

            if (s.hasNextInt()) {
                statement.setInt(1, s.nextInt());
            } else {
                System.out.println("Incorrect price!");
                return;
            }

            if (s.hasNextInt()) {
                statement.setInt(2, s.nextInt());
            } else {
                System.out.println("Incorrect price!");
                return;
            }

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                System.out.print(rs.getString(2));
                System.out.print("(" + rs.getInt(1) + "): ");
                System.out.println(rs.getInt(3) + " rubs.");
            }
        } catch (SQLException e) {
            printException(e, "filter_by_price");
        }
    }

    public void addItem(String title, int price) {
        query = "INSERT INTO " + dbTable + "(prodid, title, price) VALUES (?, ?, ?)";
        String subQuery = "SELECT EXISTS(SELECT title FROM " + dbTable + " WHERE title = ?)";
        try {
            statement = connection.prepareStatement(query);
            PreparedStatement subStatement = connection.prepareStatement(subQuery);

            int prodid = price + 1337;
            statement.setInt(1, prodid);
            statement.setString(2, title);
            subStatement.setString(1, title);

            statement.setInt(3, price);

            ResultSet rs = subStatement.executeQuery();
            rs.next();
            if (!rs.getBoolean(1)) {
                statement.execute();
            } else {
                System.out.println("Product doesn't exist.");
            }
        } catch (SQLException e) {
            printException(e, "addItem");
        }
    }

    public void updateItem(String title, int price) {
        query = "UPDATE " + dbTable + " SET price = ? WHERE title = ?";
        try {
            statement = connection.prepareStatement(query);
            statement.setString(2, title);
            statement.setInt(1, price);

            statement.execute();
        } catch (SQLException e) {
            printException(e, "change_price");
        }
    }

    public void deleteItem(String title) {
        query = "DELETE FROM " + dbTable + " WHERE title = ?";
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, title);

            statement.execute();
        } catch (SQLException e) {
            printException(e, "delete");
        }
    }

    public ResultSet getByQuery(String query) {
        try {
            statement = connection.prepareStatement(query);
            return statement.executeQuery();
        } catch (SQLException e) {
            printException(e, "getByQuery");
            return null;
        }
    }


    private void executeQuery(String caller) {
        try {
            statement = connection.prepareStatement(query);
            statement.execute();
        } catch (SQLException e) {
            printException(e, caller);
        }
    }

    private void executeQuery() {
        try {
            statement = connection.prepareStatement(query);
            statement.execute();
        } catch (SQLException e) {
            printException(e);
        }
    }

    private void printException(SQLException e, String caller) {
        System.out.println("Exception occurred in \"" + caller + "\"! Stack trace:");
        e.printStackTrace();
    }

    private void printException(SQLException e) {
        System.out.println("Exception occurred! Stack trace:");
        e.printStackTrace();
    }
}