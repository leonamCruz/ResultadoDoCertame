package tech.leonam.resultadodocertame.model.entidade;

import java.util.List;

public class TurmaEntidade {
    private List<AlunoEntidade> turma;

    public TurmaEntidade(List<AlunoEntidade> turma) {
        this.turma = turma;
    }

    public List<AlunoEntidade> getTurma() {
        return turma;
    }

    public void setTurma(List<AlunoEntidade> turma) {
        this.turma = turma;
    }
}
