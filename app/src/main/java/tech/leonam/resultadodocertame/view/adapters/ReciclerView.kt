package tech.leonam.resultadodocertame.view.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import tech.leonam.resultadodocertame.databinding.ReciclavelTurmaBinding

class ReciclerView(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var binding : ReciclavelTurmaBinding
    init {
        binding = ReciclavelTurmaBinding.bind(itemView)
    }
}