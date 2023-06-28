package tech.leonam.resultadodocertame.model.interfaces

import android.content.Context
import tech.leonam.resultadodocertame.modelView.entidade.ConfigProva

interface InterfaceSalvaProvas {
    @Throws(Exception::class)
    fun salvaProvas(context: Context?, configProva: ConfigProva?): Boolean
}