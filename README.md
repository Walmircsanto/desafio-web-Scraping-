# desafio-web-Scraping-

# Web Scraping ANS + PDF to CSV (Java Edition) ☕

## Descrição
Solução Java para automação de coleta e processamento de dados do portal da ANS. Realiza:
- Download programático dos Anexos I e II em PDF
- Extração de tabelas do PDF para CSV
- Compactação em arquivo ZIP

Tecnologias utilizadas: Java 11+, Jsoup, Apache PDFBox e OpenCSV.

## 🛠️ Stack Tecnológica
<img src="https://github.com/tandpfun/skill-icons/blob/main/icons/Java-Dark.svg" width="50px"> <img src="https://storage.semalt.com/uploads/articles/e179d86b4554d4c5b238f86bcaf2f6342.png" width="50px">  <img src="https://github.com/tandpfun/skill-icons/blob/main/icons/Maven-Dark.svg" width="50px">

## ▶️ Como Executar

### Pré-requisitos
- JDK 11+
- Maven 3.8+
- Dependências no pom.xml:
```xml
<dependencies>
    <!-- Web Scraping -->
    <dependency>
        <groupId>org.jsoup</groupId>
        <artifactId>jsoup</artifactId>
        <version>1.16.1</version>
    </dependency>
    
    <!-- PDF Processing -->
    <dependency>
        <groupId>org.apache.pdfbox</groupId>
        <artifactId>pdfbox</artifactId>
        <version>3.0.0</version>
    </dependency>
    
    <!-- CSV Export -->
    <dependency>
        <groupId>com.opencsv</groupId>
        <artifactId>opencsv</artifactId>
        <version>5.7.1</version>
    </dependency>
</dependencies>
