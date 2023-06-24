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
import tech.leonam.resultadodocertame.model.entidade.AlunoEntidade
import tech.leonam.resultadodocertame.modelView.service.AlunoService
import tech.leonam.resultadodocertame.modelView.service.ControleDeTurmaService
import tech.leonam.resultadodocertame.modelView.service.TurmaService

class AlunosView : AppCompatActivity() {
    private var nomeDaClasse: String? = null
    private var nomesAlunos: EditText? = null
    private var criarAlunos: Button? = null
    private var ads: AdView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alunos_view)
        supportActionBar!!.hide()
        window.navigationBarColor = Color.BLACK
        nomeDaClasse = intent.getStringExtra("nomeDaClasse")
        iniciarComponentes()
        //TODO iniciar somente quando for para produção
        //iniciarAds();
        setarHint()
        clickBotao()
    }

    fun iniciarComponentes() {
        nomesAlunos = findViewById(R.id.alunosEditText)
        criarAlunos = findViewById(R.id.adicionarAlunos)
        ads = findViewById(R.id.adView)
    }

    fun iniciarAds() {
        MobileAds.initialize(this) { initializationStatus: InitializationStatus? -> }
        val request = AdRequest.Builder().build()
        if (request != null) {
            ads!!.loadAd(request)
        }
    }

    fun clickBotao() {
        criarAlunos!!.setOnClickListener { e: View? ->
            val nomeAlunos = nomesAlunos!!.text.toString()
            val alunos =
                nomeAlunos.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val lista = ArrayList<AlunoEntidade>()
            for (aluno in alunos) {
                val entidade = AlunoService()
                entidade.nome = aluno
                lista.add(entidade)
            }
            val turmaService = TurmaService()
            turmaService.turma = lista
            turmaService.nomeDaTurma = nomeDaClasse
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

    fun setarHint() {
        nomesAlunos!!.hint =
            getString(R.string.a_turma) + nomeDaClasse + getString(R.string.est_sendo_criada_por_favor_insira_cada_aluno_em_uma_linha_diferente)
    }
}