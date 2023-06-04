package tech.leonam.resultadodocertame.model.bd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class CreateDataBase extends SQLiteOpenHelper {
    private static final String NOME = "bd";
    private static final byte VERSAO = 1;
    private static final String CRIAR_BD = String.format("CREATE TABLE IF NOT EXISTS Aluno (id INTEGER PRIMARY KEY, nome TEXT, serie TEXT);CREATE TABLE IF NOT EXISTS Nota (id INTEGER PRIMARY KEY, aluno_id INTEGER, turma_id INTEGER, nota TEXT, FOREIGN KEY (aluno_id) REFERENCES Aluno(id), FOREIGN KEY (turma_id) REFERENCES Turma(id));CREATE TABLE IF NOT EXISTS Turma (id INTEGER PRIMARY KEY, nome TEXT);CREATE TABLE IF NOT EXISTS AlunoTurma (aluno_id INTEGER, turma_id INTEGER, FOREIGN KEY (aluno_id) REFERENCES Aluno(id), FOREIGN KEY (turma_id) REFERENCES Turma(id), PRIMARY KEY (aluno_id, turma_id));");


    public CreateDataBase(@Nullable Context context) {
        super(context,NOME,null,VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CRIAR_BD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
}
