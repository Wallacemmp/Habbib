package Habbib.connection;

import javax.swing.*;
import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDAO implements Closeable {

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/db_Habbib?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT&useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "cocacola";

    protected Connection connection;

    public BaseDAO()
    {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Erro ao se conectar com o banco.\n\n"+ e.getMessage(),"WARNING",JOptionPane.WARNING_MESSAGE);
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
