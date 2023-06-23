package tech.leonam.resultadodocertame.service;

import android.content.Context;

import java.util.ArrayList;

import tech.leonam.resultadodocertame.model.entidade.TurmaEntidade;
import tech.leonam.resultadodocertame.model.interfaces.InterfacePegaTurmas;
import tech.leonam.resultadodocertame.model.repository.PegaTurmasDao;

public class PegaTurmas extends ArrayList<TurmaEntidade>  implements InterfacePegaTurmas {
    @Override
    public ArrayList<TurmaEntidade> getTurmas(Context context) {
        return new PegaTurmasDao().getTurmas(context);
    }
}
