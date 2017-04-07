package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anna on 06.04.2017.
 */
public class ReadBooks implements IOpenClose<Books> {
    static public Connection connection;
    public ReadBooks() {
        try{
            Class.forName(Driver);
           // ReadBooks.connection = DriverManager.getConnection(Url, NameOfPerson, Password);
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
        ReadBooks.connection.close();
        return true;
    }

    @Override
    public List<Books> ReadData() throws SQLException {
        List<Books> booksList = new ArrayList<Books>();
        PreparedStatement query_1 =  ReadBooks.connection.prepareStatement("SELECT * FROM Books;");
        ResultSet resultSet = query_1.executeQuery();
        while (resultSet.next())
        {
            Books books = new Books();
            books.name = resultSet.getString("Name");
            books.author = resultSet.getString("Author");
            books.year = resultSet.getInt("Year");
            books.publisher = resultSet.getString("Publisher");
            booksList.add(books);
        }
        System.out.println("-----------------------------------------------------------");

        return  booksList;

    }
    public List<Books> Query_1(int first, int second) throws SQLException {
        List<Books> booksList = new ArrayList<Books>();
        PreparedStatement query_1 =  ReadBooks.connection.prepareStatement("SELECT * FROM Books WHERE Year > ? and Year < ?;");
        query_1.setInt(1, first);
        query_1.setInt(2, second);
        ResultSet resultSet = query_1.executeQuery();
        while (resultSet.next())
        {
            Books books = new Books();
            books.name = resultSet.getString("Name");
            books.author = resultSet.getString("Author");
            books.year = resultSet.getInt("Year");
            books.publisher = resultSet.getString("Publisher");
            booksList.add(books);
        }
        System.out.println("-----------------------------------------------------------");
        return  booksList;

    }

    public boolean Query_4(int x) throws SQLException {

        PreparedStatement query_1 =  ReadBooks.connection.prepareStatement("DELETE  FROM Books WHERE Year < ? ;");
        query_1.setInt(1, x);
        query_1.executeUpdate();
        System.out.println("-----------------------------------------------------------");
        return true;


    }

}
