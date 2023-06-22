package tech.leonam.resultadodocertame.model.repository;

import android.annotation.SuppressLint;
import android.content.Context;

import java.util.ArrayList;

import tech.leonam.resultadodocertame.model.entidade.AlunoEntidade;
import tech.leonam.resultadodocertame.model.entidade.TurmaEntidade;
import tech.leonam.resultadodocertame.model.interfaces.InterfacePegaTurmas;

public class PegaTurmasDao implements InterfacePegaTurmas {
    public static final int POSICAO_NOME = 0;
    @Override
    public ArrayList<TurmaEntidade> getTurmas(Context context) {
        var bd = new CreateDataBase(context).getReadableDatabase();
        var listaDeNomesDasTurmas = new ArrayList<String>();
        String query = "SELECT name FROM sqlite_master WHERE type='table' AND name NOT LIKE 'sqlite_%' AND name NOT LIKE 'android_%'";

        try (var cursor = bd.rawQuery(query, null)) {
            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    String nomeDaTabelaDaVez = cursor.getString(POSICAO_NOME);
                    System.out.println(nomeDaTabelaDaVez);
                    listaDeNomesDasTurmas.add(nomeDaTabelaDaVez);
                    cursor.moveToNext();
                }
            }
        }

        var queryPegarAlunosDeTurmaEspecifica = "SELECT * FROM ";
        var listaDeTurmas = new ArrayList<TurmaEntidade>();

        if (listaDeNomesDasTurmas.size() > 0) {
            for (var i = 0; i < listaDeNomesDasTurmas.size(); i++) {
                var nomeDaTurmaDaVez = listaDeNomesDasTurmas.get(i);
                System.out.println(nomeDaTurmaDaVez);
                var listaDeAlunosDaTurma = new ArrayList<AlunoEntidade>();
                var turmaEntidade = new TurmaEntidade();
                var queryy = String.format("SELECT nome FROM %s",nomeDaTurmaDaVez);
                try (var cursorDeAlunos = bd.rawQuery(queryy, null)) {
                    cursorDeAlunos.moveToFirst();
                    while (!cursorDeAlunos.isAfterLast()) {
                        System.out.println(cursorDeAlunos.getPosition());
                        var entidadeDeAluno = new AlunoEntidade();
                        @SuppressLint("Range") var alunoDaVez = cursorDeAlunos.getString(cursorDeAlunos.getColumnIndex("nome"));
                        entidadeDeAluno.setNome(alunoDaVez);
                        listaDeAlunosDaTurma.add(entidadeDeAluno);
                        cursorDeAlunos.moveToNext();
                    }
                    turmaEntidade.setTurma(listaDeAlunosDaTurma);
                }
                turmaEntidade.setNomeDaTurma(nomeDaTurmaDaVez.replaceAll("_"," "));
                listaDeTurmas.add(turmaEntidade);
            }

            return listaDeTurmas;
        } else {
            return new ArrayList<>();
        }
    }
}
