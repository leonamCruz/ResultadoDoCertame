package tech.leonam.resultadodocertame.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tech.leonam.resultadodocertame.R
import tech.leonam.resultadodocertame.modelView.service.TurmaService

class ReciclerViewAdapter(
    private val context: Context,
    private val turmas: ArrayList<TurmaService?>?
) : RecyclerView.Adapter<ReciclerView>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReciclerView {
        val view = LayoutInflater.from(context).inflate(R.layout.reciclavel_turma, parent, false)
        return ReciclerView(view)
    }

    override fun onBindViewHolder(holder: ReciclerView, position: Int) {
        if (turmas!!.size > 1){
            val turma = turmas[position]
            holder.binding.nomeTurmaReciclavel.text = turma!!.nomeDaTurma
            holder.binding.qntdDeAlunosReciclavel.text = turmas[position]!!.turma!!.size.toString()
        } else {
            holder.binding.nomeTurmaReciclavel.text = context.getString(R.string.n_o_h_turmas)
            holder.binding.qntdDeAlunosReciclavel.text =context.getString(R.string.insira_novas_turmas)
        }
    }

    override fun getItemCount(): Int {
        return if (turmas!!.size < 1) 1 else turmas.size
    }
}