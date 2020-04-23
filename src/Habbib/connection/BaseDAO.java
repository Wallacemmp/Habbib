package Habbib.connection;

import java.io.Closeable;
import java.io.IOException;
import java.sql.*;

public  class BaseDAO implements Closeable {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/db_Habbib?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT&useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    protected Connection connection;

    public BaseDAO()
    {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to database", e);
        }
    }

    @Override
    public void close() throws IOException {
        try {
            if(connection != null){
                connection.close();
            }
        } catch (SQLException e) {
            throw new IOException("Failed to close connection of database", e);
        }
    }
}
