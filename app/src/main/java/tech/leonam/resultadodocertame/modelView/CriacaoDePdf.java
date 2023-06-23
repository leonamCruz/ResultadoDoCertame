package tech.leonam.resultadodocertame.modelView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;

import java.io.ByteArrayOutputStream;
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
        pdfDocument.setDefaultPageSize(PageSize.A4);

        var paragraph = new Paragraph(configProva.getNomeDaTurma())
                .setTextAlignment(TextAlignment.CENTER)
                .setFontSize(20f);

        var document = new Document(pdfDocument);
        document.add(paragraph);
        float[] qntdDeColunas;
        if(Integer.parseInt(configProva.getQntDeQuestoes()) > 30){
            qntdDeColunas = new float[]{75f,75f,75f};
        }else{
            qntdDeColunas = new float[]{60f,60f};
        }
        var tabela = new Table(qntdDeColunas);
        tabela.setHorizontalAlignment(HorizontalAlignment.CENTER);


        for (int i = 1; i <= Integer.parseInt(configProva.getQntDeQuestoes()); i++) {
            Table nestedTable = new Table(Integer.parseInt(configProva.getQntAlternativas() + 1));
            nestedTable.setFontSize(8f);
            nestedTable.setHorizontalAlignment(HorizontalAlignment.CENTER);
            nestedTable.addCell(String.valueOf(i));

            for (int j = 0; j < Integer.parseInt(configProva.getQntAlternativas()); j++) {
                nestedTable.addCell(createImage(context,j));
            }
            tabela.addCell(nestedTable);
        }
        document.add(tabela);

        document.close();
        outputStream.close();
    }
    private Image createImage(Context context,int j) {
        var letra = (char)('a' + j);
        @SuppressLint("DiscouragedApi") int resourceId = context.getResources().getIdentifier(String.valueOf(letra), "drawable",context.getPackageName());
        Log.d("DEBUG", "Resource ID: " + resourceId);
        @SuppressLint("UseCompatLoadingForDrawables") Drawable drawable = context.getResources().getDrawable(resourceId);
        var bitmapDrawable = (BitmapDrawable) drawable;
        var bitmap = bitmapDrawable.getBitmap();
        var byteArrayOutputStream = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);

        byte[] bytesDaimagem = byteArrayOutputStream.toByteArray();

        ImageData imageData = ImageDataFactory.create(bytesDaimagem);
        try {
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        var image = new Image(imageData);
        image.scaleAbsolute(15f,15f);
        return image;
    }

}


