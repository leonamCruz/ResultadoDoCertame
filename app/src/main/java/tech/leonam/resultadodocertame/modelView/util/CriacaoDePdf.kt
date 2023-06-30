package tech.leonam.resultadodocertame.modelView.util

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import android.os.Environment
import tech.leonam.resultadodocertame.R
import tech.leonam.resultadodocertame.modelView.service.ConfigProvaService
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

object CriacaoDePdf {
    private const val largura: Int = 596
    private const val altura: Int = 842
    private const val qntdDePaginas: Int = 1
    private const val posicaoX: Float = 85f
    private const val posicaoY: Float = 85f
    private const val imagemWidth: Int = 6
    private const val imagemHeight: Int = 6
    private const val espacamento: Float = 15f
    private const val espacamentoDasBolas: Float = 6f
    private const val tamanhoDaFont = 12f
    private val paintDasBolas: Paint = Paint().apply {
        color = Color.BLACK
        style = Paint.Style.STROKE
        strokeWidth = 0.5f
        strokeCap = Paint.Cap.ROUND
        strokeJoin = Paint.Join.ROUND
        isAntiAlias = true
        isDither = true
    }
    private val paintDoTexto: Paint = Paint().apply {
        color = Color.BLACK
        textSize = tamanhoDaFont
    }

    @SuppressLint("DiscouragedApi")
    fun criaPdf(configs: ConfigProvaService, context: Context) {
        val document = PdfDocument()
        val pageInfo = PdfDocument.PageInfo.Builder(largura, altura, qntdDePaginas).create()
        val page = document.startPage(pageInfo)
        val canvas = page.canvas
        desenharCabecalho(canvas, configs, context)
        desenhar(canvas, configs)

        document.finishPage(page)
        val directory = context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
        val nomeDoArquivo = configs.nomeDaTurma + ".pdf"
        val arquivo = File(directory, nomeDoArquivo)

        try {
            val output = FileOutputStream(arquivo)
            document.writeTo(output)
            document.close()
            output.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun desenharCabecalho(canvas: Canvas, configs: ConfigProvaService, context: Context) {
        paintDoTexto.textSize = 12f
        canvas.drawText("Professor(a): " + configs.nomeDoProf, 85f, 85f, paintDoTexto)
        canvas.drawText(configs.identificacaoProva, 85f, 85f + espacamento, paintDoTexto)
        canvas.drawText(
            context.getString(R.string.quest_es_com_mais_de_uma_alternativa_marcada_ser_o_anulada),
            85f,
            85f + (espacamento * 2),
            paintDoTexto
        )
    }

    private fun desenhar(canvas: Canvas, configs: ConfigProvaService) {
        var offsetX = posicaoX
        var offsetY = posicaoY + (espacamentoDasBolas * 12) + 0.75f

        for (k in 1..configs.qntDeQuestoes.toInt()) {
            paintDoTexto.textSize = 11f
            canvas.drawText(String.format("%02d - ", k), offsetX, offsetY, paintDoTexto)
            for (j in 1..configs.qntAlternativas.toInt()) {
                offsetX += imagemWidth + espacamentoDasBolas
                canvas.drawCircle(offsetX + 20f, offsetY - 5f, 5f, paintDasBolas)
                val letraDaVez = 'A'  + j - 1
                paintDoTexto.textSize = 5f
                canvas.drawText(letraDaVez.toString(),offsetX + 18.3f, offsetY - 3f , paintDoTexto)
            }
            offsetX = posicaoX
            offsetY += imagemHeight + espacamentoDasBolas
        }
    }
}

