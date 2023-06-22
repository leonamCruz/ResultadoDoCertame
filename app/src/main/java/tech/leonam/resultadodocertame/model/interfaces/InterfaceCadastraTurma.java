package tech.leonam.resultadodocertame.model.interfaces;

import android.content.Context;

import tech.leonam.resultadodocertame.model.entidade.TurmaEntidade;

public interface InterfaceCadastraTurma {

    boolean cadastrar(TurmaEntidade turmaEntidade, Context context);
}
