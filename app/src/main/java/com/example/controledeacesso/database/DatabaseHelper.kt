package com.example.controledeacesso.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.controledeacesso.model.LogModel

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, "controle.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {

        db.execSQL(
            """
            CREATE TABLE logs(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nome TEXT,
                tipo TEXT,
                data TEXT
            )
            """
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS logs")
        onCreate(db)
    }

    fun inserirLog(nome: String, tipo: String, data: String) {

        val db = writableDatabase

        val values = ContentValues()
        values.put("nome", nome)
        values.put("tipo", tipo)
        values.put("data", data)

        db.insert("logs", null, values)
    }

    fun listarLogs(): MutableList<LogModel> {

        val lista = mutableListOf<LogModel>()

        val db = readableDatabase

        val cursor = db.rawQuery(
            "SELECT * FROM logs ORDER BY id DESC",
            null
        )

        while (cursor.moveToNext()) {

            val log = LogModel(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3)
            )

            lista.add(log)
        }

        cursor.close()

        return lista
    }
}