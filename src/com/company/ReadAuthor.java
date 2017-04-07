package com.company;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anna on 06.04.2017.
 */
public class ReadAuthor implements IOpenClose<Authors>{

    static public Connection connection;
    public ReadAuthor() {
        try{
            Class.forName(Driver);
           // ReadAuthor.connection = DriverManager.getConnection(Url, NameOfPerson, Password);
        }
        catch(ClassNotFoundException ex)
        {
            System.out.println(ex.getStackTrace());
        }
       /* catch (SQLException ex){
            System.out.println(ex.getStackTrace());
        }*/
    }

    @Override
    public Connection GetConnection() throws SQLException {
        return DriverManager.getConnection(Url, NameOfPerson, Password);
    }

    @Override
    public boolean CloseConnection() throws SQLException {
        ReadAuthor.connection.close();
        return true;
    }

    @Override
    public List<Authors> ReadData() throws SQLException {
        List<Authors> authorsList = new ArrayList<Authors>();
        PreparedStatement query_1 = ReadAuthor.connection.prepareStatement("SELECT * FROM Authors;");
        ResultSet resultSet = query_1.executeQuery();
        while (resultSet.next())
        {
            Authors authors = new Authors();
            authors.country = resultSet.getString("Country");
            authors.author = resultSet.getString("Author");
            authorsList.add(authors);
        }
        System.out.println("-----------------------------------------------------------");
        return  authorsList;

    }
    public List<Authors> Query_3(int x) throws SQLException {
        List<Authors> authorsList = new ArrayList<Authors>();
        PreparedStatement query_1 = ReadAuthor.connection.prepareStatement(
                    "SELECT Authors.Author, Authors.Country, COUNT(Books.Author) as 'countOfBooks' " +
                        " FROM Authors, Books WHERE  Authors.Author = Books.Author " +
                        "GROUP BY Authors.Author HAVING countOfBooks >= ?;");
        query_1.setInt(1, x);
        ResultSet resultSet = query_1.executeQuery();
        while (resultSet.next())
        {
            Authors authors = new Authors();
            authors.country = resultSet.getString("Country");
            authors.author = resultSet.getString("Author");
            authorsList.add(authors);
        }
        System.out.println("-----------------------------------------------------------");
        return  authorsList;

    }
}
