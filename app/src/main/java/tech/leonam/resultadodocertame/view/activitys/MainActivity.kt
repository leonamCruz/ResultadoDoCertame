package tech.leonam.resultadodocertame.view.activitys

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import tech.leonam.resultadodocertame.databinding.ActivityMainBinding
import tech.leonam.resultadodocertame.modelView.service.ConfigProvaService
import tech.leonam.resultadodocertame.modelView.util.CriacaoDePdf

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()
        window.navigationBarColor = Color.BLACK
        //TODO INICIAR SOMENTE QUANDO FOR PARA PRODUÇÃO
        //iniciarAnuncio();
        clickCriar()
        clickCriarProva()
        clickTurma()
        criarProva()
    }

    fun iniciarAnuncio() {
        MobileAds.initialize(this)
        val pedeAnuncio = AdRequest.Builder().build()
        binding.adPrincipal.loadAd(pedeAnuncio)
    }

    private fun clickCriar() {
        binding.criarPessoasETurmasMain.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    TurmasView::class.java
                )
            )
        }
    }

    private fun clickCriarProva() {
        binding.criarProvaMain.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    CriarProva::class.java
                )
            )
        }
    }

    private fun clickTurma() {
        binding.turmaMain.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    InfoTurmaView::class.java
                )
            )
        }
    }
    private fun criarProva() {
        val config = ConfigProvaService()
        config.identificacaoProva = "Jota Jota"
        config.nomeDaTurma = "Leonam"
        config.qntDeQuestoes = "40"
        config.qntAlternativas = "10"
        config.nomeDoProf = "Leonam Cruz do Nascimento"
        config.identificacaoProva = "Primeira Prova de Matemática"
        CriacaoDePdf.criaPdf(config,this)
        Toast.makeText(this, "Prova Criada com sucesso", Toast.LENGTH_SHORT).show()
    }
}