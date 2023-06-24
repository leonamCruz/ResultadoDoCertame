package tech.leonam.resultadodocertame.view.activitys

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import tech.leonam.resultadodocertame.R
import tech.leonam.resultadodocertame.databinding.TurmasBinding

class TurmasView : AppCompatActivity() {
    private lateinit var binding: TurmasBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TurmasBinding.inflate(layoutInflater)
        supportActionBar!!.hide()
        window.navigationBarColor = Color.BLACK
        setContentView(binding.root)
        //TODO iniciarAds();
        clickBotao()
    }


    private fun iniciarAds() {
        MobileAds.initialize(this)
        binding.adCriarTurma.loadAd(AdRequest.Builder().build())
    }

    private fun clickBotao() {
        binding.botaoCriarTurma.setOnClickListener {
            val nomeDaClasse = binding.nomeTurmaCriarTurma.text.toString()
            if (nomeDaClasse.isBlank())
                Toast.makeText(this, getString(R.string.insira_o_nome_da_turma), Toast.LENGTH_SHORT).show()
            else {
                val intencao = Intent(this, AlunosView::class.java)
                intencao.putExtra("nomeDaClasse", nomeDaClasse)
                startActivity(intencao)
            }
        }
    }
}