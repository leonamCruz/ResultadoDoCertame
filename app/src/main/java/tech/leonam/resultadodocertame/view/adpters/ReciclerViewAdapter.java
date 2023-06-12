package tech.leonam.resultadodocertame.view.adpters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import tech.leonam.resultadodocertame.R;
import tech.leonam.resultadodocertame.model.entidade.TurmaEntidade;

public class ReciclerViewAdapter extends RecyclerView.Adapter<ReciclerView> {

    private final Context context;
    private final ArrayList<TurmaEntidade> turmas;


    public ReciclerViewAdapter(Context context, ArrayList<TurmaEntidade> turmas) {
        this.context = context;
        this.turmas = turmas;

    }

    @NonNull
    @Override
    public ReciclerView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        var view = LayoutInflater.from(context).inflate(R.layout.reciclavel_turma, parent, false);
        return new ReciclerView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReciclerView holder, int position) {
        if (turmas.size() > 1) {
            var turma = turmas.get(position);
            holder.getNomeDaTurma().setText(turma.getNomeDaTurma());
            holder.getQntdDeAlunos().setText(String.valueOf(turmas.get(position).getTurma().size()));
        } else {
            holder.getNomeDaTurma().setText(R.string.n_o_h_turmas);
            holder.getQntdDeAlunos().setText(R.string.insira_novas_turmas);
        }
    }

    @Override
    public int getItemCount() {
        return turmas.size() < 1 ? 1 : turmas.size();
    }
}
