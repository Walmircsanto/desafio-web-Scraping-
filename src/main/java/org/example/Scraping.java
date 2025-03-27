package org.example;

import org.example.scraping.ZipparPdf;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.zip.ZipOutputStream;

public class Scraping {

  public void linksPDF() throws IOException {

      String pdfsDIR= "src/main/pdfs";

      Files.createDirectories(Paths.get(pdfsDIR));

      String url = "https://www.gov.br/ans/pt-br/acesso-a-informacao/participacao-da-sociedade/atualizacao-do-rol-de-procedimentos";

      Document doc = Jsoup.connect(url).get();

      //selecione todos os links presente na pagina que esteja na tag li a e que termine com .pdf
      Elements linksPdfs = doc.select("li a[href$=.pdf]");

      for(Element linkPdf: linksPdfs){
          String link = linkPdf.attr("abs:href");
          String fileName = link.substring(link.lastIndexOf("/") + 1);

          FileOutputStream destino;
          ZipOutputStream saida;

          if(fileName.split("_")[0].equalsIgnoreCase("Anexo")){
              String filePath = pdfsDIR + File.separator + fileName;
              dowloadPdf(link, filePath);

              destino = new FileOutputStream(new File(filePath));
              saida = new ZipOutputStream(new BufferedOutputStream(destino));

              ZipparPdf.add(filePath,saida);
          }
          continue;

      }



  }


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



