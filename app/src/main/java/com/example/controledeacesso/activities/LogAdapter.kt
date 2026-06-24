package com.example.controledeacesso.activities

import android.app.AlertDialog
import android.widget.EditText
import android.widget.LinearLayout
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.controledeacesso.database.DatabaseHelper
import com.example.controledeacesso.databinding.ItemLogBinding
import com.example.controledeacesso.model.LogModel

class LogAdapter(
    private val lista: MutableList<LogModel>,
    private val banco: DatabaseHelper
) : RecyclerView.Adapter<LogAdapter.LogViewHolder>() {

    inner class LogViewHolder(
        val binding: ItemLogBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LogViewHolder {

        val binding = ItemLogBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return LogViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: LogViewHolder,
        position: Int
    ) {

        val log = lista[position]

        holder.binding.txtLog.text =
            "${log.nome} - ${log.tipo} - ${log.dataHora}"

        holder.binding.btnExcluir.setOnClickListener {

            AlertDialog.Builder(holder.itemView.context)
                .setTitle("Excluir")
                .setMessage("Deseja excluir este log?")
                .setPositiveButton("Sim") { _, _ ->

                    banco.deletarLog(log.id)

                    lista.removeAt(position)

                    notifyItemRemoved(position)
                }
                .setNegativeButton("Não", null)
                .show()
        }

        holder.binding.btnEditar.setOnClickListener {

            val context = holder.itemView.context

            val edtNome = EditText(context)
            edtNome.setText(log.nome)

            val edtTipo = EditText(context)
            edtTipo.setText(log.tipo)

            val edtData = EditText(context)
            edtData.setText(log.dataHora)

            val layout = LinearLayout(context)
            layout.orientation = LinearLayout.VERTICAL

            layout.addView(edtNome)
            layout.addView(edtTipo)
            layout.addView(edtData)

            AlertDialog.Builder(context)
                .setTitle("Editar Log")
                .setView(layout)
                .setPositiveButton("Salvar") { _, _ ->

                    banco.atualizarLog(
                        log.id,
                        edtNome.text.toString(),
                        edtTipo.text.toString(),
                        edtData.text.toString()
                    )

                    lista[position] = LogModel(
                        log.id,
                        edtNome.text.toString(),
                        edtTipo.text.toString(),
                        edtData.text.toString()
                    )

                    notifyItemChanged(position)
                }
                .setNegativeButton("Cancelar", null)
                .show()
        }
    }

    override fun getItemCount(): Int {
        return lista.size
    }
}