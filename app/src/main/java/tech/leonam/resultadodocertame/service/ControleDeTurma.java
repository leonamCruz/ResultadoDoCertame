package tech.leonam.resultadodocertame.service;

import android.content.Context;

import tech.leonam.resultadodocertame.model.interfaces.InterfaceCadastraTurma;
import tech.leonam.resultadodocertame.model.repository.CadastraTurmaDao;
import tech.leonam.resultadodocertame.model.entidade.TurmaEntidade;

public class ControleDeTurma implements InterfaceCadastraTurma {

    @Override
    public boolean cadastrar(TurmaEntidade turmaEntidade, Context context) {
        return new CadastraTurmaDao().cadastrar(turmaEntidade, context);
    }
}
