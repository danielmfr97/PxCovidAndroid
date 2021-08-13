package br.com.daniel.ramos.pacientmvp.view.adapters

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import br.com.daniel.ramos.pacientmvp.MainActivity
import br.com.daniel.ramos.pacientmvp.data.model.Referencia
import br.com.daniel.ramos.pacientmvp.databinding.ItemReferenciaBinding

class ReferenciaAdapter : RecyclerView.Adapter<ReferenciaAdapter.ViewHolder>() {
    private var _binding: ItemReferenciaBinding? = null
    private val binding get() = _binding!!

    var infoList = listOf<Referencia>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        _binding = ItemReferenciaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val referenciaSelecionada = infoList[position]
        holder.bind(referenciaSelecionada)
    }

    override fun getItemCount(): Int {
        return infoList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var refLink = binding.tvReferenciaLink
        var tvReferenciaNumber = binding.tvReferenciaNumber

        fun bind(item: Referencia) {
            if (item.ref.isNotEmpty()) {
                tvReferenciaNumber.visibility = View.VISIBLE
                tvReferenciaNumber.text = item.ref
            }
            refLink.text = item.link
            refLink.setOnClickListener {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(item.link))
                browserIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                ContextCompat.startActivity(
                    MainActivity.instance!!.applicationContext,
                    browserIntent,
                    null
                )
            }
        }
    }
}