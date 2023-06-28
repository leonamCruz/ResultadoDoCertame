package tech.leonam.resultadodocertame.modelView.service

import android.content.Context
import tech.leonam.resultadodocertame.modelView.entidade.TurmaEntidade
import tech.leonam.resultadodocertame.model.interfaces.InterfaceCadastraTurma
import tech.leonam.resultadodocertame.model.repository.CadastraTurmaDao

class ControleDeTurmaService : InterfaceCadastraTurma {
    override fun cadastrar(turmaEntidade: TurmaEntidade?, context: Context?): Boolean {
        return CadastraTurmaDao().cadastrar(turmaEntidade, context)
    }
}