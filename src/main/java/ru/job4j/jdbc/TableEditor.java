package ru.job4j.jdbc;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;
    private Properties properties;

    public TableEditor(Properties properties) {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() {
        try {
            Class.forName(properties.getProperty("hibernate.connection.driver_class"));
            String url = properties.getProperty("hibernate.connection.url");
            String login = properties.getProperty("hibernate.connection.username");
            String password = properties.getProperty("hibernate.connection.password");
            connection = DriverManager.getConnection(url, login, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createTable(String tableName) {
        exec(String.format("create table if not exists %s()", tableName));
    }

    public void dropTable(String tableName) {
        exec(String.format("DROP TABLE %s", tableName));
    }

    public void addColumn(String tableName, String columnName, String type) {
        exec(String.format("ALTER TABLE %s ADD COLUMN %s %s",
                tableName, columnName, type));
    }

    public void dropColumn(String tableName, String columnName) {
        exec(String.format("ALTER TABLE %s DROP %s",
                tableName, columnName));
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        exec(String.format("ALTER TABLE %s RENAME COLUMN %s TO %s",
                tableName, columnName, newColumnName));
    }

    public void exec(String sql) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    public static void main(String[] args) throws Exception {
        Properties pr = new Properties();
        pr.load(new FileInputStream("db_connect.properties"));
        TableEditor te = new TableEditor(pr);
        te.initConnection();
        te.createTable("parts");
        te.addColumn("parts", "glass", "varchar(100)");
        te.addColumn("parts", "doors", "varchar(100)");
        te.renameColumn("parts", "glass", "glasss_2");
        System.out.println(getTableScheme(te.connection, "parts"));
        te.dropColumn("parts", "glasss_2");
        te.dropTable("parts");
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}