package tech.leonam.resultadodocertame.model.repository;

import android.content.ContentValues;
import android.content.Context;

import tech.leonam.resultadodocertame.modelView.entidade.TurmaEntidade;
import tech.leonam.resultadodocertame.model.interfaces.InterfaceCadastraTurma;

public class CadastraTurmaDao implements InterfaceCadastraTurma {
    @Override
    public boolean cadastrar(TurmaEntidade turmaEntidade, Context context) {
        try {
            var nomeTb = turmaEntidade.getNomeDaTurma().replace(" ","_");
            var bd = new CreateDataBase(context).getWritableDatabase();
            var query = "CREATE TABLE IF NOT EXISTS " + nomeTb + " (id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT)";
            bd.execSQL(query);

            var valores = new ContentValues();
            for(var i = 0 ; i < turmaEntidade.getTurma().size(); i++){
                valores.clear();
                valores.put("nome", turmaEntidade.getTurma().get(i).getNome());
                bd.insert(nomeTb,null,valores);
            }
            bd.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
