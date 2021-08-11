package br.com.daniel.ramos.pacientmvp.view.adapters

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import br.com.daniel.ramos.pacientmvp.MainActivity
import br.com.daniel.ramos.pacientmvp.data.model.trabalho.Trabalho
import br.com.daniel.ramos.pacientmvp.databinding.ItemTrabalhoBinding


class TrabalhosAdapter :
    RecyclerView.Adapter<TrabalhosAdapter.TrabalhoViewHolder>() {
    var trabalhosList = listOf<Trabalho>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private var _binding: ItemTrabalhoBinding? = null
    private val binding get() = _binding!!

    inner class TrabalhoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var data = binding.tvData
        var titulo = binding.tvTitulo
        var revista = binding.tvRevista
        var resumo = binding.tvResumo
        var linkUrl = binding.tvLink

        fun bind(item: Trabalho) {
            data.text = item.dataArtigo
            titulo.text = item.tituloArtigo
            revista.text = item.revistaArtigo
            resumo.text = item.resumoArtigo
            linkUrl.setOnClickListener {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(item.linkArtigo))
                browserIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(MainActivity.instance!!.applicationContext, browserIntent, null)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrabalhoViewHolder {
        _binding =
            ItemTrabalhoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TrabalhoViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: TrabalhoViewHolder, position: Int) {
        val trabalhoSelecionado = trabalhosList[position]
        holder.bind(trabalhoSelecionado)
    }

    override fun getItemCount(): Int {
        return trabalhosList.size
    }
}

