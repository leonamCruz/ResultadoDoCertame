package tech.leonam.resultadodocertame.model.interfaces;

import android.content.Context;

import tech.leonam.resultadodocertame.model.entidade.ConfigProva;

public interface InterfaceSalvaProvas {
    boolean salvaProvas(Context context, ConfigProva configProva);
}
