package com.example.controledeacesso.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.controledeacesso.database.DatabaseHelper
import com.example.controledeacesso.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var banco: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        banco = DatabaseHelper(this)

        binding.btnEntrada.setOnClickListener {

            salvarLog("Entrada")
        }

        binding.btnSaida.setOnClickListener {

            salvarLog("Saída")
        }

        binding.btnVerLogs.setOnClickListener {

            startActivity(
                Intent(this, LogsActivity::class.java)
            )
        }
    }

    private fun salvarLog(tipo: String) {

        val nome = binding.edtNome.text.toString()

        if (nome.isEmpty()) {

            Toast.makeText(
                this,
                "Digite um nome",
                Toast.LENGTH_SHORT
            ).show()

            return
        }

        val data = SimpleDateFormat(
            "dd/MM/yyyy HH:mm",
            Locale.getDefault()
        ).format(Date())

        banco.inserirLog(nome, tipo, data)

        Toast.makeText(
            this,
            "$tipo registrada",
            Toast.LENGTH_SHORT
        ).show()

        binding.edtNome.text.clear()
    }
}