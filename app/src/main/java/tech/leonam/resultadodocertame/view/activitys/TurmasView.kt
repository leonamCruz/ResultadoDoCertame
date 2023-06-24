package tech.leonam.resultadodocertame.view.activitys

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.initialization.InitializationStatus
import tech.leonam.resultadodocertame.R

class TurmasView : AppCompatActivity() {
    private var nomeTurma: EditText? = null
    private var criarTurma: Button? = null
    private var ads: AdView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.hide()
        window.navigationBarColor = Color.BLACK
        setContentView(R.layout.turmas)
        iniciarComponentes()
        //Habilitar somente quando for para produção
        //TODO iniciarAds();
        clickBotao()
    }

    fun iniciarComponentes() {
        nomeTurma = findViewById(R.id.nomeTurmaCriarTurma)
        criarTurma = findViewById(R.id.botaoCriarTurma)
        ads = findViewById(R.id.adCriarTurma)
    }

    fun iniciarAds() {
        MobileAds.initialize(this) { initializationStatus: InitializationStatus? -> }
        val request = AdRequest.Builder().build()
        ads!!.loadAd(request)
    }

    fun clickBotao() {
        criarTurma!!.setOnClickListener { e: View? ->
            val nomeDaClasse = nomeTurma!!.text.toString()
            if (nomeDaClasse == null || nomeDaClasse.isBlank()) {
                Toast.makeText(this, getString(R.string.insira_o_nome_da_turma), Toast.LENGTH_SHORT)
                    .show()
            } else {
                val intencao = Intent(this, AlunosView::class.java)
                intencao.putExtra("nomeDaClasse", nomeDaClasse)
                startActivity(intencao)
            }
        }
    }
}