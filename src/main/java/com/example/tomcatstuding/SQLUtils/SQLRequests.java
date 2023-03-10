package com.example.tomcatstuding.SQLUtils;

import com.example.tomcatstuding.DataBaseConnection;
import com.example.tomcatstuding.Users;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class SQLRequests {
    private static ResultSet resultSet;
    private static final Logger log = Logger.getLogger(String.valueOf(SQLRequests.class));
    public static Users createUser(String login, String password) throws SQLException {
        PreparedStatement preparedStatement;
        log.info("method createUser started");
        preparedStatement = DataBaseConnection.getConnection().prepareStatement("INSERT INTO users (login,password) values (?,?)");
        preparedStatement.setString(1,login);
        preparedStatement.setString(2,password);
        preparedStatement.execute();
        preparedStatement.close();
        log.info("User with login " + login + " was created");
        return new Users(login,password);
    }

    public static List<Users> getUsers() throws SQLException {
        Statement statement;
        statement = DataBaseConnection.getConnection().createStatement();
        log.info("method getUsers started");
        List<Users> usersList = new ArrayList<>();
        resultSet = statement.executeQuery("select login, password from users");
        while (resultSet.next()) {
            usersList.add(new Users(resultSet.getString("login"), resultSet.getString("password")));
        }
        resultSet.close();
        statement.close();
        log.info("method getUsers end");
        return usersList;
    }

    public SQLRequests() throws SQLException {
    }


}
