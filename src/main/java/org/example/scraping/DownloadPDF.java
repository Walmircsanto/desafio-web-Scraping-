package org.example.scraping;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class DownloadPDF {

    public  static void dowloadPdf(String url,  String file) throws IOException {
        //indico que a URL chegando  e um recurso
        URL urlPdf = new URL(url);            //inicia uma nova conexão TCP com o servidor para o qual a URL resolve
        ReadableByteChannel canal = Channels.newChannel(urlPdf.openStream());
        FileOutputStream fos = new FileOutputStream(file);
        fos.getChannel().transferFrom(canal,0, Long.MAX_VALUE); // abro um canal para receber esses bytes
        fos.close();
        canal.close();
    }
}
