package com.habbib.controller;

import com.habbib.connection.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SessionManager {

    public boolean login(String name,String password){

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean validation = false;

        try {
            stmt = con.prepareStatement("SELECT Nome,Senha FROM Instituicao WHERE Nome = ? AND Senha = ?");
            stmt.setString(1, name);
            stmt.setString(2, password);
            rs = stmt.executeQuery();

            while(rs.next()){
                validation = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return validation;
    }
}
