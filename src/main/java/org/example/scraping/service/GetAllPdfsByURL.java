package org.example.scraping;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipOutputStream;

import static org.example.scraping.ZipparPdf.compactarPDFs;

public class GetAllPdfsByURL {


        public void getLinkPDF(String urlLink) throws IOException {
            String pdfsDIR = "src/main/pdfs";
            String zipFilePath = "src/main/pdfs/pdfs-gov.zip";

            Files.createDirectories(Paths.get(pdfsDIR));
            Files.createDirectories(Paths.get(zipFilePath).getParent());

            Document doc = Jsoup.connect(urlLink).get();
            Elements linksPdfs = doc.select("li a[href$=.pdf]");

            List<String> pdfPaths = new ArrayList<>();

            for (Element linkPdf : linksPdfs) {
                String link = linkPdf.attr("abs:href");
                String fileName = link.substring(link.lastIndexOf("/") + 1);

                try {
                    if (fileName.split("_")[0].equalsIgnoreCase("Anexo")) {
                        String filePath = pdfsDIR + File.separator + fileName;

                        pdfPaths.add(filePath); // so e adicionado assim que o pdf ja estiver baixado
                        compactarPDFs(link,pdfPaths, zipFilePath);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }



        }


}



