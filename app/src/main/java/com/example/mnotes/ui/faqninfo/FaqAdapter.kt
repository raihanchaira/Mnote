package com.example.mnotes.ui.faqninfo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mnotes.R
import com.example.mnotes.model.Faq

/** Raihan Chaira on 5/27/2023
 * raihanchaira21@gmail.com
 */
class FaqAdapter(private  val ListFaq : ArrayList<Faq>) : RecyclerView.Adapter<FaqAdapter.ListViewHolder> () {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FaqAdapter.ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_faq, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: FaqAdapter.ListViewHolder, position: Int) {
        val (title, field) = ListFaq[position]
        holder.tittle.text = title
        holder.field.text = field
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(ListFaq[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = ListFaq.size

    interface OnItemClickCallback {
        fun onItemClicked(data: Faq)
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tittle: TextView = itemView.findViewById(R.id.tv_tittle)
        val field: TextView = itemView.findViewById(R.id.tv_field)
    }

}