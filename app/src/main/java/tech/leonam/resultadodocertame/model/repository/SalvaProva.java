package tech.leonam.resultadodocertame.model.repository;

import android.content.ContentValues;
import android.content.Context;

import tech.leonam.resultadodocertame.model.entidade.ConfigProva;
import tech.leonam.resultadodocertame.model.interfaces.InterfaceSalvaProvas;

public class SalvaProva implements InterfaceSalvaProvas {
    @Override
    public boolean salvaProvas(Context context, ConfigProva configProva) {

        try {
            final var sql = String.format("CREATE TABLE IF NOT EXISTS %s " +
                    "(id INTEGER PRIMARY KEY AUTOINCREMENT" +
                    "turma_table INTEGER, " +
                    "prova TEXT" +
                    "questoes_corretas TEXT" +
                    "FOREGIN KEY(turma_table) REFERENCES %s)", configProva.getNomeDaTurma() + "Prova", configProva.getNomeDaTurma());

            var bd = new CreateDataBase(context).getWritableDatabase();
            bd.execSQL(sql);
            var content = new ContentValues();
            content.put("prova", configProva.getIdentificacaoProva());
            content.put("questoes_corretas", configProva.getAlternativasCorretas());
            bd.insert(configProva.getNomeDaTurma() + "Prova", null, content);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
