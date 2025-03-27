package org.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello, World!");

        Scraping webScrapingBooksTitle = new Scraping();

        webScrapingBooksTitle.linksPDF();
    }


}