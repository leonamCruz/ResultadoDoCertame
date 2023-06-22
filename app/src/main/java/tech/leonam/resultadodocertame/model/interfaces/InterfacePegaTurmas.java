package tech.leonam.resultadodocertame.model.interfaces;

import android.content.Context;

import java.util.ArrayList;

import tech.leonam.resultadodocertame.model.entidade.TurmaEntidade;

public interface InterfacePegaTurmas {
    ArrayList<TurmaEntidade> getTurmas(Context context);
}
