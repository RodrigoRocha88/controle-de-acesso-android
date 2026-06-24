package com.example.controledeacesso.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.controledeacesso.database.DatabaseHelper
import com.example.controledeacesso.databinding.ActivityLogsBinding

class LogsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLogsBinding
    private lateinit var banco: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLogsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        banco = DatabaseHelper(this)

        carregarLogs()

        binding.btnLimparLogs.setOnClickListener {

            banco.apagarTodosLogs()

            carregarLogs()
        }
    }

    private fun carregarLogs() {

        val lista = banco.listarLogs()

        binding.recyclerLogs.layoutManager =
            LinearLayoutManager(this)

        binding.recyclerLogs.adapter =
            LogAdapter(lista, banco)
    }
}
