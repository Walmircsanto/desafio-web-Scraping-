package org.example.scraping.utils;

import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipparPdf {
    static final int TAMANHO_BUFFER = 4096;

    public static void compactarPDFs(String url,List<String> pdfPaths, String zipFilePath) {
                                                        //criara um canal de fluxo de saida de bytes
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFilePath))) {
            for (String pdfPath : pdfPaths) {
               adicionarAoZip(url, pdfPath, zos);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void adicionarAoZip( String pdfUrl ,String pdfName, ZipOutputStream zos) throws IOException {

        URL urlPdf = new URL(pdfUrl); //inicia uma nova conexão TCP com o servidor para o qual a URL resolve                                          //Abre um fluxo de entrada para ler bytes do arquivo
        try (BufferedInputStream buf = new BufferedInputStream(urlPdf.openStream())){ //abrindo o pdf para poder ler o bytes

            File file = new File(pdfName);
            ZipEntry zipEntry = new ZipEntry(file.getName());
            zos.putNextEntry(zipEntry);

            byte[] buffer = new byte[TAMANHO_BUFFER];
            int bytesRead;
            while ((bytesRead = buf.read(buffer)) != -1) {
                zos.write(buffer, 0, bytesRead);
            }
            zos.closeEntry();


        }
    }
}
