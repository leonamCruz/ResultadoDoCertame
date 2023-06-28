package tech.leonam.resultadodocertame.modelView.entidade

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
open class TurmaEntidade(var nomeDaTurma: String = "", var turma: ArrayList<AlunoEntidade> = ArrayList()) : Parcelable{
    override fun toString(): String {
        var retorno = ""
        for (aluno in turma) retorno = retorno + aluno.nome + "\n"

        return retorno
    }
}