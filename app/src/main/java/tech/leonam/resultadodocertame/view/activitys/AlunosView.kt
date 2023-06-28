package tech.leonam.resultadodocertame.view.activitys

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import tech.leonam.resultadodocertame.R
import tech.leonam.resultadodocertame.databinding.ActivityAlunosViewBinding
import tech.leonam.resultadodocertame.modelView.entidade.AlunoEntidade
import tech.leonam.resultadodocertame.modelView.entidade.TurmaEntidade
import tech.leonam.resultadodocertame.modelView.service.ControleDeTurmaService

class AlunosView : AppCompatActivity() {
    private lateinit var binding: ActivityAlunosViewBinding
    private lateinit var nomeDaClasse: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlunosViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()
        window.navigationBarColor = Color.BLACK
        nomeDaClasse = intent.getStringExtra("nomeDaClasse").toString()
        binding.alunosEditText.hint = getString(R.string.a_turma) + nomeDaClasse + getString(R.string.est_sendo_criada_por_favor_insira_cada_aluno_em_uma_linha_diferente)
        clickBotao()
    }
    private fun iniciarAds() {
        MobileAds.initialize(this)
        binding.adView.loadAd( AdRequest.Builder().build())
    }

    private fun clickBotao() {
        binding.adicionarAlunos.isClickable = true
        binding.adicionarAlunos.setOnClickListener {
            val nomeAlunos = binding.alunosEditText.text.toString()
            val alunos = nomeAlunos.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val lista = ArrayList<AlunoEntidade>()
            for (aluno in alunos) {
                val entidade = AlunoEntidade()
                entidade.nome = aluno
                lista.add(entidade)
            }
            val turmaService = TurmaEntidade()
            turmaService.nomeDaTurma = nomeDaClasse
            turmaService.turma = lista
            if (ControleDeTurmaService().cadastrar(turmaService, this)) {
                val intencao = Intent(this, MainActivity::class.java)
                Toast.makeText(this, R.string.classe_criada_com_sucesso, Toast.LENGTH_SHORT).show()
                intencao.putExtra("nomeDaClasse", nomeDaClasse)
                startActivity(intencao)
            } else {
                Toast.makeText(
                    this,
                    R.string.infelizmente_a_classe_n_o_foi_criada,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}