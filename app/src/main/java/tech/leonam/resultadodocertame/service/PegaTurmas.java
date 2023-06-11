package tech.leonam.resultadodocertame.service;

import android.content.Context;

import java.util.ArrayList;

import tech.leonam.resultadodocertame.model.entidade.TurmaEntidade;
import tech.leonam.resultadodocertame.model.repository.PegaTurmasDao;

public class PegaTurmas extends ArrayList<TurmaEntidade> {
    public static ArrayList<TurmaEntidade> turmas(Context context) {
        return PegaTurmasDao.getTurmas(context);
    }
}
