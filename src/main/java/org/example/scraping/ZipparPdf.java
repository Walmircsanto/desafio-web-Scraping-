package org.example.scraping;

import java.io.*;
import java.nio.file.Files;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipparPdf {
    static final int TAMANHO_BUFFER = 4096;

    public static void compactarPDFs(List<String> pdfPaths, String zipFilePath) {
                                                        //criara um canal de fluxo de saida de bytes
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFilePath))) {
            for (String pdfPath : pdfPaths) {
                adicionarAoZip(pdfPath, zos);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void adicionarAoZip(String pdfPath, ZipOutputStream zos) throws IOException {
                                                               //Abre um fluxo de entrada para ler bytes do arquivo
        try (BufferedInputStream buf = new BufferedInputStream(new FileInputStream(pdfPath))) { //abrindo o pdf para poder ler o bytes
            File file = new File(pdfPath);
            ZipEntry zipEntry = new ZipEntry(file.getName());
            zos.putNextEntry(zipEntry);

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = buf.read(buffer)) != -1) {
                zos.write(buffer, 0, bytesRead);
            }


            if(file.exists()){
                file.delete();
                System.out.println("Arquivo apagado");
            }else{
                System.out.println("Não apagado");
            }
            zos.closeEntry();


        }
    }
}
