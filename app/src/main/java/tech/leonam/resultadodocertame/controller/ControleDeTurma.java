package tech.leonam.resultadodocertame.controller;

import android.content.Context;

import tech.leonam.resultadodocertame.model.repository.CadastraTurmaDao;
import tech.leonam.resultadodocertame.model.entidade.TurmaEntidade;

public class ControleDeTurma {
    public static boolean cadastre(TurmaEntidade turmaEntidade, Context context) {
        return CadastraTurmaDao.cadastrar(turmaEntidade, context);
    }
}
