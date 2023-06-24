package tech.leonam.resultadodocertame.modelView.service

import android.content.Context
import tech.leonam.resultadodocertame.model.entidade.TurmaEntidade
import tech.leonam.resultadodocertame.model.interfaces.InterfacePegaTurmas
import tech.leonam.resultadodocertame.model.repository.PegaTurmasDao

class PegaTurmasService : ArrayList<TurmaEntidade?>(), InterfacePegaTurmas {
    override fun getTurmas(context: Context?): ArrayList<TurmaService?>? {
        return PegaTurmasDao().getTurmas(context)
    }
}