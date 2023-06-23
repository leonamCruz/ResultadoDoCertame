package tech.leonam.resultadodocertame.modelView

import android.content.Context
import tech.leonam.resultadodocertame.model.entidade.ConfigProva
import tech.leonam.resultadodocertame.model.interfaces.InterfaceSalvaProvas
import tech.leonam.resultadodocertame.model.repository.SalvaProvaDao

class ServiceSalvaProvas : InterfaceSalvaProvas {
    @Throws(Exception::class)
    override fun salvaProvas(context: Context?, configProva: ConfigProva?): Boolean {
        return SalvaProvaDao().salvaProvas(context, configProva)
    }
}