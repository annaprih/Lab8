package com.company;

/**
 * Created by Anna on 06.04.2017.
 */
public class Authors {
    public String author;
    public String country;

    @Override
    public String toString() {
        return "Authors:" +
                "author='" + author +
                " country='" + country +
                '\n';
    }
}
