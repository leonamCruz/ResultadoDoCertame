package tech.leonam.resultadodocertame.model.entidade

class ConfigProva {
    lateinit var qntAlternativas: String
    lateinit var qntDeQuestoes: String
    lateinit var nomeDaTurma: String
    lateinit var alternativasCorretas: String
    var isIndividual = false
    lateinit var identificacaoProva: String
}