package tech.leonam.resultadodocertame.modelView.util

import tech.leonam.resultadodocertame.modelView.entidade.AlunoEntidade
import tech.leonam.resultadodocertame.modelView.entidade.TurmaEntidade

class OrganizarAlunos {
    fun ordemAlfabetica(turma: TurmaEntidade): TurmaEntidade {
        val nomeDosAlunos = arrayListOf<String>()
        for (nome in turma.turma) {
            nomeDosAlunos.add(nome.nome!!)
        }
        nomeDosAlunos.sort()
        val listaDeCablocos = ArrayList<AlunoEntidade>()
        for (nome in nomeDosAlunos) {
            val entidade = AlunoEntidade()
            entidade.nome = nome
            listaDeCablocos.add(entidade)
        }
        val turmaOrganizada = TurmaEntidade()
        turmaOrganizada.nomeDaTurma = turma.nomeDaTurma
        turmaOrganizada.turma = listaDeCablocos
        return turmaOrganizada
    }
}