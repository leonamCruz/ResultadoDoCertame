package tech.leonam.resultadodocertame.model.entidade

class TurmaEntidade {
    var nomeDaTurma: String? = null
    var turma: List<AlunoEntidade>? = null

    constructor()

    constructor(turma: List<AlunoEntidade>?, nomeDaTurma: String?) {
        this.turma = turma
        this.nomeDaTurma = nomeDaTurma
    }
}