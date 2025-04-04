package org.example;

import org.example.scraping.service.ExtractFileToCsv;
import org.example.scraping.service.GetAllPdfsByURL;
import org.xml.sax.SAXException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, SAXException {
        System.out.println("Hello, World!");

        GetAllPdfsByURL webGetAllPdfsByURLBooksTitle = new GetAllPdfsByURL();

        webGetAllPdfsByURLBooksTitle.getLinkPDF("https://www.gov.br/ans/pt-br/acesso-a-informacao/participacao-da-sociedade/atualizacao-do-rol-de-procedimentos");

        ExtractFileToCsv extractFileToCsv = new ExtractFileToCsv();

        extractFileToCsv.parse();
    }


}