package tech.leonam.resultadodocertame.model.interfaces

import android.content.Context
import tech.leonam.resultadodocertame.modelView.entidade.TurmaEntidade

interface InterfacePegaTurmas {
    fun getTurmas(context: Context?): ArrayList<TurmaEntidade?>?
}