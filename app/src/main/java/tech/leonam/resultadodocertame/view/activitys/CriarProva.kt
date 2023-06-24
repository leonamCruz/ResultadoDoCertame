package tech.leonam.resultadodocertame.view.activitys

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.initialization.InitializationStatus
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import tech.leonam.resultadodocertame.R
import tech.leonam.resultadodocertame.modelView.service.ConfigProvaService
import tech.leonam.resultadodocertame.modelView.service.SalvaProvasService
import tech.leonam.resultadodocertame.modelView.util.CriacaoDePdf

class CriarProva : AppCompatActivity() {
    private var qntAlternativas: EditText? = null
    private var qntDeQuestoes: EditText? = null
    private var nomeDaTurma: EditText? = null
    private var alternativasCorretas: EditText? = null
    private var identificacaoProva: EditText? = null
    private var isIndividual: CheckBox? = null
    private var criarProva: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MobileAds.initialize(this) { initializationStatus: InitializationStatus? -> }
        setContentView(R.layout.activity_criar_prova)
        supportActionBar!!.hide()
        window.navigationBarColor = Color.BLACK
        iniciarComponentes()
        //HABILITAR SOMENTE QUANDO FOR PARA PRODUCAO
        // TODO iniciarAnuncio(new AdRequest.Builder().build());
        criarProva()
    }

    fun iniciarComponentes() {
        qntAlternativas = findViewById(R.id.txtQntDeAlternativas)
        qntDeQuestoes = findViewById(R.id.txtQntDeQuestao)
        nomeDaTurma = findViewById(R.id.txtTurma)
        alternativasCorretas = findViewById(R.id.txtQuestoesCorretas)
        isIndividual = findViewById(R.id.checkIndividual)
        criarProva = findViewById(R.id.botaoCriarProva)
        identificacaoProva = findViewById(R.id.identificacaoCriarProva)
    }

    fun iniciarAnuncio(request: AdRequest?) {
        try {
            InterstitialAd.load(this, "ca-app-pub-3940256099942544/1033173712", request!!,
                object : InterstitialAdLoadCallback() {
                    override fun onAdLoaded(interstitialAd: InterstitialAd) {
                        interstitialAd.show(this@CriarProva)
                    }
                })
        } catch (ignored: Exception) {
        }
    }

    fun criarProva() {
        criarProva!!.setOnClickListener { e: View? ->
            val qntdDeCorretasDigitadas =
                alternativasCorretas!!.text.toString().split("\n".toRegex())
                    .dropLastWhile { it.isEmpty() }
                    .toTypedArray().size
            if (qntAlternativas!!.text.toString().toInt() != qntdDeCorretasDigitadas) {
                val dialog = AlertDialog.Builder(this)
                dialog.setTitle("Erro")
                dialog.setMessage("A quantidade de alternativas está diferente da quantidade de alternativas corretas que você forneceu")
                dialog.setCancelable(true)
                dialog.create().show()
            } else {
                val configs = ConfigProvaService()
                configs.isIndividual = isIndividual!!.isChecked
                configs.alternativasCorretas = alternativasCorretas!!.text.toString()
                configs.qntDeQuestoes = qntDeQuestoes!!.text.toString()
                configs.qntAlternativas = qntAlternativas!!.text.toString()
                configs.nomeDaTurma = nomeDaTurma!!.text.toString()
                configs.identificacaoProva = identificacaoProva!!.text.toString()
                println(configs.identificacaoProva)
                try {
                    CriacaoDePdf(configs).criaPdf(this)
                    SalvaProvasService().salvaProvas(this, configs)
                    Toast.makeText(this, "Prova Criada com sucesso", Toast.LENGTH_SHORT).show()
                } catch (ex: Exception) {
                    ex.printStackTrace()
                    Toast.makeText(this, "Deu Muita MERDA!!!!!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}