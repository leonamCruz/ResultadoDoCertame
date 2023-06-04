package tech.leonam.resultadodocertame.model.entidade;

public class ConfigProva {
    private String qntAlternativas, qntDeQuestoes, nomeDaTurma, alternativasCorretas;
    private boolean isIndividual;

    public String getQntAlternativas() {
        return qntAlternativas;
    }

    public void setQntAlternativas(String qntAlternativas) {
        this.qntAlternativas = qntAlternativas;
    }

    public String getQntDeQuestoes() {
        return qntDeQuestoes;
    }

    public void setQntDeQuestoes(String qntDeQuestoes) {
        this.qntDeQuestoes = qntDeQuestoes;
    }

    public String getNomeDaTurma() {
        return nomeDaTurma;
    }

    public void setNomeDaTurma(String nomeDaTurma) {
        this.nomeDaTurma = nomeDaTurma;
    }

    public String getAlternativasCorretas() {
        return alternativasCorretas;
    }

    public void setAlternativasCorretas(String alternativasCorretas) {
        this.alternativasCorretas = alternativasCorretas;
    }

    public boolean isIndividual() {
        return isIndividual;
    }

    public void setIndividual(boolean individual) {
        isIndividual = individual;
    }
}