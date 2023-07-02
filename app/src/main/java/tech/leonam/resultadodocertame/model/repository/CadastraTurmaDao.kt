package tech.leonam.resultadodocertame.model.repository

import android.content.ContentValues
import android.content.Context
import tech.leonam.resultadodocertame.model.interfaces.InterfaceCadastraTurma
import tech.leonam.resultadodocertame.modelView.entidade.TurmaEntidade

class CadastraTurmaDao : InterfaceCadastraTurma {
    override fun cadastrar(turmaEntidade: TurmaEntidade?, context: Context?): Boolean {
        return try {
            val nomeTb = turmaEntidade!!.nomeDaTurma.replace(" ", "_")
            val bd = CreateDataBase(context).writableDatabase
            val query =
                "CREATE TABLE IF NOT EXISTS $nomeTb (id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT)"
            bd.execSQL(query)
            val valores = ContentValues()
            for (i in turmaEntidade.turma.indices) {
                valores.clear()
                valores.put("nome", turmaEntidade.turma[i].nome)
                bd.insert(nomeTb, null, valores)
            }
            bd.close()
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}