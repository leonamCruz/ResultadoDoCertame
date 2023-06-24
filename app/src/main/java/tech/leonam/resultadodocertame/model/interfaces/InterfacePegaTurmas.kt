package tech.leonam.resultadodocertame.model.interfaces

import android.content.Context
import tech.leonam.resultadodocertame.model.entidade.TurmaEntidade
import tech.leonam.resultadodocertame.modelView.service.TurmaService

interface InterfacePegaTurmas {
    fun getTurmas(context: Context?): ArrayList<TurmaService?>?
}