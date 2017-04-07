package com.company;

/**
 * Created by Anna on 06.04.2017.
 */
public class Books {
    public String name;
    public String author;
    public int year;
    public String publisher;

    @Override
    public String toString() {
        return "Books:" +
                "name='" + name +
                " author='" + author +
                " year=" + year +
                " publisher='" + publisher +
                '\n';
    }
}
