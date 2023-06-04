package tech.leonam.resultadodocertame.model.entidade;

import java.util.Map;

public class NotaEntidade{
    private final TurmaEntidade turma;
    private final short qntDeQuestao;
    private final short qntDeAlternativas;

    private final Map<String,Character> questoes;
    public NotaEntidade(TurmaEntidade turma,short qntDeQuestao,short qntDeAlternativas,Map<String,Character> questoes){
        this.turma = turma;
        this.qntDeQuestao = qntDeQuestao;
        this.qntDeAlternativas = qntDeAlternativas;
        this.questoes = questoes;
    }

}
