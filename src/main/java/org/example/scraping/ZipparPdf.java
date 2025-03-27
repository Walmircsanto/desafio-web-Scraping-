package org.example.scraping;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipparPdf {
    static final int TAMANHO_BUFFER = 4096;

    public static void add(String arquivo, ZipOutputStream saida) throws IOException {
        int cont;
        byte[] dados = new byte[TAMANHO_BUFFER];

        File file = new File(arquivo);
        BufferedInputStream origem = new BufferedInputStream(new FileInputStream(file), TAMANHO_BUFFER);
        ZipEntry entry = new ZipEntry(file.getName());
        saida.putNextEntry(entry);

        while ((cont = origem.read(dados, 0, TAMANHO_BUFFER)) != -1) {
            saida.write(dados, 0, cont);
        }

        origem.close();
    }
}
