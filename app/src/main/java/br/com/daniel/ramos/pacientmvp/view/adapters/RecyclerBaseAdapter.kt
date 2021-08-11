package br.com.daniel.ramos.pacientmvp.view.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView


class RecyclerBaseAdapter<VH : RecyclerView.ViewHolder?>(private val mAdapter: RecyclerView.Adapter<VH>) :
    BaseAdapter(), Filterable {
    override fun getItemViewType(position: Int): Int {
        return mAdapter.getItemViewType(position)
    }

    override fun getCount(): Int {
        return mAdapter.itemCount
    }

    override fun getItem(position: Int): Any? {
        // not supported
        return null
    }

    override fun getItemId(position: Int): Long {
        return mAdapter.getItemId(position)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var convertView: View? = convertView
        val holder: VH
        if (convertView == null) {
            holder = mAdapter.createViewHolder(parent!!, getItemViewType(position))
            convertView = holder!!.itemView
            convertView.setTag(holder)
        } else {
            holder = convertView.getTag() as VH
        }
        mAdapter.bindViewHolder(holder, position)
        return holder!!.itemView
    }

    override fun getFilter(): Filter {
        TODO("Not yet implemented")
    }

}