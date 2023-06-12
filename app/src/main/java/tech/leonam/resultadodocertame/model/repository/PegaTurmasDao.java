package tech.leonam.resultadodocertame.model.repository;

import android.content.Context;

import java.util.ArrayList;

import tech.leonam.resultadodocertame.model.entidade.AlunoEntidade;
import tech.leonam.resultadodocertame.model.entidade.TurmaEntidade;

public class PegaTurmasDao {
    public static final int POSICAO_NOME = 0;

    /*
    * Explição da lógica:
    * Os nomes das turmas são os nomes das tabelas
    * É necessário todos os alunos de uma turma em uma lista para inserir na entidade de turma.
    * É necessário retornar todas as turmas em um ArrayList
       BUG CONHECIDO: ESTOU A MEMÓRIA RAM POR ALGUM MOTIVO QUE EU NÃO SEI

    * */


    public static ArrayList<TurmaEntidade> getTurmas(Context context) {
        var bd = new CreateDataBase(context).getReadableDatabase();
        var listaDeNomesDasTurmas = new ArrayList<String>();

        try (var cursor = bd.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null)) {
            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    String nomeDaTabelaDaVez = cursor.getString(POSICAO_NOME);
                    listaDeNomesDasTurmas.add(nomeDaTabelaDaVez);
                }
            }
        }

        var queryPegarAlunosDeTurmaEspecifica = "SELECT nome FROM ";
        var listaDeTurmas = new ArrayList<TurmaEntidade>();

        if (listaDeNomesDasTurmas.size() > 0) {
            for (var i = 0; i < listaDeNomesDasTurmas.size(); i++) {
                var nomeDaTurmaDaVez = listaDeNomesDasTurmas.get(i);
                var listaDeAlunosDaTurma = new ArrayList<AlunoEntidade>();
                var turmaEntidade = new TurmaEntidade();

                turmaEntidade.setNomeDaTurma(nomeDaTurmaDaVez.replaceAll("_"," "));
                try (var cursorDeAlunos = bd.rawQuery(queryPegarAlunosDeTurmaEspecifica + nomeDaTurmaDaVez, null)) {

                    while (!cursorDeAlunos.isAfterLast()) {

                        var entidadeDeAluno = new AlunoEntidade();
                        var alunoDaVez = cursorDeAlunos.getString(POSICAO_NOME);
                        entidadeDeAluno.setNome(alunoDaVez);
                        listaDeAlunosDaTurma.add(entidadeDeAluno);
                        cursorDeAlunos.moveToNext();
                    }
                    turmaEntidade.setTurma(listaDeAlunosDaTurma);
                }
                listaDeTurmas.add(turmaEntidade);
            }

            return listaDeTurmas;
        } else {
            return new ArrayList<>();
        }
    }
}
