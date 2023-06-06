package tech.leonam.resultadodocertame.controller;

import android.content.Context;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import tech.leonam.resultadodocertame.model.entidade.ConfigProva;

public class CriacaoDePdf {
    private final ConfigProva configProva;

    public CriacaoDePdf(ConfigProva configs) {
        this.configProva = configs;
    }

    public void criaPdf(Context context) throws IOException {
        var file = new File(context.getExternalFilesDir(null), "prova.pdf");

        OutputStream outputStream = new FileOutputStream(file);

        var writer = new PdfWriter(outputStream);
        var pdfDocument = new PdfDocument(writer);
        var document = new Document(pdfDocument);

        var paragraph = new Paragraph("Olá Mundooooooooo");
        document.add(paragraph);
        document.close();
        outputStream.close();
    }

}


