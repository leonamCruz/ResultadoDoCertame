package tech.leonam.resultadodocertame.view.activitys

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import tech.leonam.resultadodocertame.databinding.ActivityCriarProvaBinding
import tech.leonam.resultadodocertame.modelView.service.ConfigProvaService
import tech.leonam.resultadodocertame.modelView.util.CriacaoDePdf

class CriarProva : AppCompatActivity() {
    private lateinit var binding: ActivityCriarProvaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCriarProvaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()
        window.navigationBarColor = Color.BLACK
        criarProva()
    }

    private fun criarProva() {
        val config = ConfigProvaService()
        config.identificacaoProva = "Jota Jota"
        config.nomeDaTurma = "Leonam"
        config.qntDeQuestoes = "5"
        config.qntAlternativas = "5"
        CriacaoDePdf.criaPdf(config,this)
        Toast.makeText(this, "Prova Criada com sucesso", Toast.LENGTH_SHORT).show()
    }

    /*
    private fun criarProva() {
        binding.botaoCriarProva.setOnClickListener {
            val qntdDeCorretasDigitadas =
                binding.txtQntDeAlternativas.text.toString().split("\n".toRegex())
                    .dropLastWhile { it.isEmpty() }
                    .toTypedArray().size
            if (binding.txtQntDeAlternativas.text.toString().toInt() != qntdDeCorretasDigitadas) {
                val dialog = AlertDialog.Builder(this)
                dialog.setTitle("Erro")
                dialog.setMessage("A quantidade de alternativas está diferente da quantidade de alternativas corretas que você forneceu")
                dialog.setCancelable(true)
                dialog.create().show()
            } else {
                val configs = ConfigProvaService()
                configs.isIndividual = binding.checkIndividual.isChecked
                configs.alternativasCorretas = binding.txtQuestoesCorretas.text.toString()
                configs.qntDeQuestoes =binding.txtQntDeQuestao.text.toString()
                configs.qntAlternativas = binding.txtQntDeAlternativas.text.toString()
                configs.nomeDaTurma = binding.txtTurma.text.toString()
                configs.identificacaoProva = binding.identificacaoCriarProva.text.toString()
                try {
                    CriacaoDePdf.criaPdf(configs)
                    SalvaProvasService().salvaProvas(this, configs)
                    Toast.makeText(this, "Prova Criada com sucesso", Toast.LENGTH_SHORT).show()
                } catch (ex: Exception) {
                    ex.printStackTrace()
                    Toast.makeText(this, "Deu Muita MERDA!!!!!", Toast.LENGTH_SHORT).show()
                }
            }

     */
        }