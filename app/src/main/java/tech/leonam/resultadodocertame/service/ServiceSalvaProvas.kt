package tech.leonam.resultadodocertame.service;

import android.content.Context;

import tech.leonam.resultadodocertame.model.entidade.ConfigProva;
import tech.leonam.resultadodocertame.model.interfaces.InterfaceSalvaProvas;
import tech.leonam.resultadodocertame.model.repository.SalvaProvaDao;

public class ServiceSalvaProvas implements InterfaceSalvaProvas {
    @Override
    public boolean salvaProvas(Context context, ConfigProva configProva) throws Exception{
        return new SalvaProvaDao().salvaProvas(context,configProva);
    }
}
