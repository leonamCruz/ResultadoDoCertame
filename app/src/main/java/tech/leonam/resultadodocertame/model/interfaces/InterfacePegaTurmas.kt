package tech.leonam.resultadodocertame.model.interfaces

import android.content.Context
import tech.leonam.resultadodocertame.model.entidade.TurmaEntidade

interface InterfacePegaTurmas {
    fun getTurmas(context: Context?): ArrayList<TurmaEntidade?>?
}