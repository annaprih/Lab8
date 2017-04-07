package com.company;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        try {
            PrintWriter fileWriter = new PrintWriter("Laba8.txt");

            ReadAuthor readAuthor = new ReadAuthor();
            ReadBooks readBooks = new ReadBooks();


            try {
                ReadAuthor.connection = readAuthor.GetConnection();
                ReadBooks.connection = readBooks.GetConnection();

                ReadBooks.connection.setAutoCommit(false);
                ReadAuthor.connection.setAutoCommit(false);

                fileWriter.println("Начало транзакции");
                fileWriter.println("Все книги, вышедшие в 1800-1900 годах");
                System.out.println(readBooks.Query_1(1800, 1900));
                fileWriter.println("Запрос к таблице авторов");
                System.out.println(readAuthor.ReadData());
                fileWriter.println("Все авторы, с колиеством написанных книг не меньше 2");
                System.out.println(readAuthor.Query_3(2));
                fileWriter.println("Удаление книг, публикация которых была позднее 1850 года");
                System.out.println(readBooks.Query_4(1850));
                fileWriter.println("Окончание транзакции");
                ReadBooks.connection.commit();
                ReadAuthor.connection.commit();
                fileWriter.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                try {
                    fileWriter.println("Откат транзакции");
                    fileWriter.close();
                    ReadBooks.connection.rollback();
                    ReadAuthor.connection.rollback();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } catch (Exception ex) {

                System.out.println(ex.getStackTrace());
            } finally {
                try {
                    readAuthor.CloseConnection();
                    readBooks.CloseConnection();

                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }


        }
        catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}
