package tech.leonam.resultadodocertame.model.interfaces

import android.content.Context
import tech.leonam.resultadodocertame.modelView.entidade.TurmaEntidade

interface InterfaceCadastraTurma {
    fun cadastrar(turmaEntidade: TurmaEntidade?, context: Context?): Boolean
}