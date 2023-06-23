package tech.leonam.resultadodocertame.service

import android.content.Context
import tech.leonam.resultadodocertame.model.entidade.TurmaEntidade
import tech.leonam.resultadodocertame.model.interfaces.InterfacePegaTurmas
import tech.leonam.resultadodocertame.model.repository.PegaTurmasDao

class PegaTurmas : ArrayList<TurmaEntidade?>(), InterfacePegaTurmas {
    override fun getTurmas(context: Context): ArrayList<TurmaEntidade> {
        return PegaTurmasDao().getTurmas(context)
    }
}