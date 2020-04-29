package Habbib.connection;

import java.io.Closeable;
import java.io.IOException;
import java.sql.*;

public class BaseDAO implements Closeable {

    private static final String URL = "jdbc:mysql://localhost:3306/db_Habbib?useTimezone=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "meleca123";

    protected Connection connection;

    public BaseDAO()
    {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Database connection failure", e);
        }
    }

    @Override
    public void close() throws IOException {
        try {
            if(connection != null){
                connection.close();
            }
        } catch (SQLException e) {
            throw new IOException("Database closing failure", e);
        }
    }
}
