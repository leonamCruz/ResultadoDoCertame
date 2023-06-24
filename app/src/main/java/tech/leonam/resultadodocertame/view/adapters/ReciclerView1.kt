package tech.leonam.resultadodocertame.view.adapters

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import tech.leonam.resultadodocertame.R

class ReciclerView(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val nomeDaTurma: TextView
    val qntdDeAlunos: TextView

    init {
        nomeDaTurma = itemView.findViewById(R.id.nome_turma_reciclavel)
        qntdDeAlunos = itemView.findViewById(R.id.qntd_de_alunos_reciclavel)
    }
}