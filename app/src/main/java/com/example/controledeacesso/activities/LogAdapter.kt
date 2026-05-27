package com.example.controledeacesso.activities

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.controledeacesso.databinding.ItemLogBinding
import com.example.controledeacesso.model.LogModel

class LogAdapter(
    private val lista: List<LogModel>
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
    }

    override fun getItemCount(): Int {
        return lista.size
    }
}