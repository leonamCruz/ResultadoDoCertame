package tech.leonam.resultadodocertame.model.entidade;

import java.util.List;

public class TurmaEntidade {
    private String nomeDaTurma;
    private List<AlunoEntidade> turma;

    public TurmaEntidade(List<AlunoEntidade> turma,String nomeDaTurma) {
        this.turma = turma;
        this.nomeDaTurma = nomeDaTurma;
    }

    public TurmaEntidade() {
    }

    public List<AlunoEntidade> getTurma() {
        return turma;
    }

    public void setTurma(List<AlunoEntidade> turma) {
        this.turma = turma;
    }

    public String getNomeDaTurma() {
        return nomeDaTurma;
    }

    public void setNomeDaTurma(String nomeDaTurma) {
        this.nomeDaTurma = nomeDaTurma;
    }
}
