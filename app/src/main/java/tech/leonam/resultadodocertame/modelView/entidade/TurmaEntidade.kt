package tech.leonam.resultadodocertame.modelView.entidade

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
open class TurmaEntidade(var nomeDaTurma: String = "", var turma: ArrayList<AlunoEntidade> = ArrayList()) : Parcelable