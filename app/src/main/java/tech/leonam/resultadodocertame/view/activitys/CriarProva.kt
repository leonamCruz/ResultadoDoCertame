package tech.leonam.resultadodocertame.view.activitys

import android.graphics.Color
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import tech.leonam.resultadodocertame.R
import tech.leonam.resultadodocertame.databinding.ActivityCriarProvaBinding
import tech.leonam.resultadodocertame.modelView.service.ConfigProvaService
import tech.leonam.resultadodocertame.modelView.service.PegaTurmasService
import tech.leonam.resultadodocertame.modelView.util.CriacaoDePdf

class CriarProva : AppCompatActivity() {
    private lateinit var binding: ActivityCriarProvaBinding
    private lateinit var nomeDaTurma: Spinner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCriarProvaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()
        window.navigationBarColor = Color.BLACK
        povoarSpinner()
        botoesDeAdd()
        criarProva()
    }

    private fun botoesDeAdd() {
        binding.addAlternativas.setOnClickListener {
            if (binding.txtQntDeAlternativas.text.isBlank()) {
                binding.txtQntDeAlternativas.setText("0")
            }
            var qnt = binding.txtQntDeAlternativas.text.toString().toInt()
            qnt++
            binding.txtQntDeAlternativas.setText(qnt.toString())
        }
        binding.addQuestoes.setOnClickListener {
            if (binding.txtQntDeQuestao.text.isBlank()) {
                binding.txtQntDeQuestao.setText("0")
            }
            var qnt = binding.txtQntDeQuestao.text.toString().toInt()
            qnt++
            binding.txtQntDeQuestao.setText(qnt.toString())
        }
    }

    private fun criarProva() {
        binding.botaoCriarProva.setOnClickListener {
            try {
                val config = ConfigProvaService()
                config.qntDeQuestoes = binding.txtQntDeQuestao.text.toString()
                config.qntAlternativas = binding.txtQntDeAlternativas.text.toString()
                config.nomeDoProf = binding.seuNome.text.toString()
                config.identificacaoProva = binding.identificacaoCriarProva.text.toString()
                config.nomeDaTurma = binding.nomeDaTurma.selectedItem.toString()
                config.alternativasCorretas = binding.txtQuestoesCorretas.text.toString()

                val listaDeCorretas = config.alternativasCorretas.split("\n")
                val listaDeCaracteres: List<Char> =
                    listaDeCorretas.flatMap { it.toCharArray().toList() }


                if (config.qntDeQuestoes.toInt() != listaDeCaracteres.size) {
                    Toast.makeText(this,"Na quantidade",Toast.LENGTH_SHORT).show()
                    throw Exception()
                }


                for (letra in listaDeCaracteres) {
                    if(letra.code >= ('a' + config.qntAlternativas.toInt()).code || letra == ' '){
                        Toast.makeText(this,"Erro no FOR",Toast.LENGTH_SHORT).show()
                        throw Exception()
                    }
                }

                CriacaoDePdf.criaPdf(config, this)

            } catch (ex: Exception) {
                Toast.makeText(this, getString(R.string.verifique_os_campos), Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun povoarSpinner() {
        val list = PegaTurmasService().getTurmas(this)
        val listaDeTurmas = ArrayList<String>()

        if (list != null) {
            for (turma in list) {
                listaDeTurmas.add(turma!!.nomeDaTurma)
            }
        } else {
            listaDeTurmas.add("Ainda não existem turmas")
        }

        nomeDaTurma = binding.nomeDaTurma
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, listaDeTurmas)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        nomeDaTurma.adapter = adapter
    }


    private fun criarProvaa() {
        val config = ConfigProvaService()
        config.identificacaoProva = "Jota Jota"
        config.nomeDaTurma = "Leonam"
        config.qntDeQuestoes = "5"
        config.qntAlternativas = "5"
        CriacaoDePdf.criaPdf(config, this)
        Toast.makeText(this, "Prova Criada com sucesso", Toast.LENGTH_SHORT).show()
    }
}