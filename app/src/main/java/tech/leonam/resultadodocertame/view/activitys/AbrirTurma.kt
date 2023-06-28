package tech.leonam.resultadodocertame.view.activitys

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import tech.leonam.resultadodocertame.databinding.ActivityAbrirTurmaBinding
import tech.leonam.resultadodocertame.modelView.entidade.TurmaEntidade

class AbrirTurma : AppCompatActivity() {
    private lateinit var binding: ActivityAbrirTurmaBinding
    private lateinit var lista: TurmaEntidade
    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAbrirTurmaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lista = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            intent.extras!!.getParcelable("turma", TurmaEntidade::class.java)!! else {
            intent.extras!!.getParcelable("CARALHO")!!
        }

        binding.nomes.setText(lista.nomeDaTurma)
        Toast.makeText(this, lista.nomeDaTurma, Toast.LENGTH_SHORT).show()
        binding.nomeTurmaAbrirTurma.text = lista.nomeDaTurma
    }
}