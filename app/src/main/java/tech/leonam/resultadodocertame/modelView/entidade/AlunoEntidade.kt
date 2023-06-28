package tech.leonam.resultadodocertame.modelView.entidade

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
open class AlunoEntidade(var id: Long? = 0, var nome: String? = "") : Parcelable