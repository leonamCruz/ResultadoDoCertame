package tech.leonam.resultadodocertame.model.repository;

import android.content.ContentValues;
import android.content.Context;

import tech.leonam.resultadodocertame.modelView.entidade.ConfigProva;
import tech.leonam.resultadodocertame.model.interfaces.InterfaceSalvaProvas;

public class SalvaProvaDao implements InterfaceSalvaProvas {
    @Override
    public boolean salvaProvas(Context context, ConfigProva configProva) throws Exception{
        var sql = "CREATE TABLE IF NOT EXISTS " + configProva.getNomeDaTurma() + "Prova " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "turma_table INTEGER, " +
                "prova TEXT, " +
                "questoes_corretas TEXT, " +
                "FOREIGN KEY(turma_table) REFERENCES " + configProva.getNomeDaTurma() + ")";


        var bd = new CreateDataBase(context).getWritableDatabase();
            bd.execSQL(sql);
            var content = new ContentValues();
            content.put("prova", configProva.getIdentificacaoProva());
            content.put("questoes_corretas", configProva.getAlternativasCorretas());
            bd.insert(configProva.getNomeDaTurma() + "Prova", null, content);
            return true;
    }
}
