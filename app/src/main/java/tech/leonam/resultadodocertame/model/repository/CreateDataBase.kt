package tech.leonam.resultadodocertame.model.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class CreateDataBase(context: Context?) : SQLiteOpenHelper(context, NOME, null, VERSAO.toInt()) {
    override fun onCreate(db: SQLiteDatabase) {}
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {}

    companion object {
        private const val NOME = "bd"
        private const val VERSAO: Byte = 1
    }
}