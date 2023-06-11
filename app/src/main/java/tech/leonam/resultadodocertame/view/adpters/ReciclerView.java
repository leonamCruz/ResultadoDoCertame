package tech.leonam.resultadodocertame.view.adpters;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import tech.leonam.resultadodocertame.R;

public class ReciclerView extends RecyclerView.ViewHolder {
    private final TextView nomeDaTurma, qntdDeAlunos;
    public ReciclerView(@NonNull View itemView) {
        super(itemView);
        nomeDaTurma = itemView.findViewById(R.id.nome_turma_reciclavel);
        qntdDeAlunos = itemView.findViewById(R.id.qntd_de_alunos_reciclavel);
    }

    public TextView getNomeDaTurma() {
        return nomeDaTurma;
    }

    public TextView getQntdDeAlunos() {
        return qntdDeAlunos;
    }
}
