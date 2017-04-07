package com.company;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Anna on 06.04.2017.
 */
public interface IOpenClose<T> {
    public String NameOfPerson = "root";
    public String Password = "80291227107";
    public String Driver = "com.mysql.jdbc.Driver";
    public String Url = "jdbc:mysql://localhost:3306/Java8";

    public Connection GetConnection()throws SQLException;
    public boolean CloseConnection() throws SQLException;
    public List<T> ReadData() throws SQLException;
}
