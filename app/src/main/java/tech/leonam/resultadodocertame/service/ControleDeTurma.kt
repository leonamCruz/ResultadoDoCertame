package tech.leonam.resultadodocertame.service

import android.content.Context
import tech.leonam.resultadodocertame.model.entidade.TurmaEntidade
import tech.leonam.resultadodocertame.model.interfaces.InterfaceCadastraTurma
import tech.leonam.resultadodocertame.model.repository.CadastraTurmaDao

class ControleDeTurma : InterfaceCadastraTurma {
    override fun cadastrar(turmaEntidade: TurmaEntidade?, context: Context?): Boolean {
        return CadastraTurmaDao().cadastrar(turmaEntidade, context)
    }
}