package tech.leonam.resultadodocertame.modelView.entidade

open class ConfigProva {
    lateinit var qntAlternativas: String
    lateinit var qntDeQuestoes: String
    lateinit var nomeDaTurma: String
    lateinit var alternativasCorretas: String
    var isIndividual = false
    lateinit var identificacaoProva: String
}