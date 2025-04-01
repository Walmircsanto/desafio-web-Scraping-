package org.example.scraping.service;

import com.opencsv.CSVWriter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.tika.sax.BodyContentHandler;
import technology.tabula.ObjectExtractor;
import org.xml.sax.SAXException;


import technology.tabula.PageIterator;
import technology.tabula.RectangularTextContainer;
import technology.tabula.Table;
import technology.tabula.extractors.SpreadsheetExtractionAlgorithm;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


public class ExtractFileToCsv {

    public void parse(String systemId) throws SAXException, IOException {
        BodyContentHandler contentHandler = new BodyContentHandler();

        File file = new File("src/main/pdfs/Anexo_I_Rol_2021RN_465.2021_RN627L.2024.pdf");

        unzip("src/main/pdfs/pdfs-gov.zip", "src/main/pdfs");

        InputStream is = this.getClass().getResourceAsStream("src/main/pdfs/Anexo_I_Rol_2021RN_465.2021_RN627L.2024.pdf");
        PDDocument doc = PDDocument.load(is);

        PageIterator pages = new ObjectExtractor(doc).extract();


        // 2. Extrair tabelas (usando Tabula)
        SpreadsheetExtractionAlgorithm extractor = new SpreadsheetExtractionAlgorithm();
        List<Table> tables = extractor.extract(pages);

        // 3. Salvar em CSV (usando OpenCSV)
        CSVWriter csvWriter = new CSVWriter(new FileWriter("saida.csv"));
        for (Table table : tables) {
            for (List<RectangularTextContainer> row : table.getRows()) {
                String[] csvRow = row.stream()
                        .map(cell -> cell.getText())
                        .toArray(String[]::new);
                csvWriter.writeNext(csvRow);
            }
        }
        csvWriter.close();
        System.out.println("CSV gerado com sucesso!");
    }


}


private static void unzip(String zipFilePath, String destDir) {
    File dir = new File(destDir);
    // create output directory if it doesn't exist
    if (!dir.exists()) dir.mkdirs();
    FileInputStream fis;
    //buffer for read and write data to file
    byte[] buffer = new byte[1024];
    try {
        fis = new FileInputStream(zipFilePath);
        ZipInputStream zis = new ZipInputStream(fis);// indico que e um arquivo zip
        ZipEntry ze = zis.getNextEntry();
        while (ze != null) {
            String fileName = ze.getName();
            File newFile = new File(destDir + File.separator + fileName);
            System.out.println("Unzipping to " + newFile.getAbsolutePath());
            //create directories for sub directories in zip
            new File(newFile.getParent()).mkdirs();
            FileOutputStream fos = new FileOutputStream(newFile);
            int len;
            while ((len = zis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }
            fos.close();
            //close this ZipEntry
            zis.closeEntry();
            ze = zis.getNextEntry();
        }
        //close last ZipEntry
        zis.closeEntry();
        zis.close();
        fis.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
}
