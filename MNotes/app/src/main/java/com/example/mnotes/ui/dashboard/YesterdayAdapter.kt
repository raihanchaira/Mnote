package com.example.mnotes.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mnotes.R
import com.example.mnotes.model.Order

/** Raihan Chaira on 5/24/2023
 * raihanchaira21@gmail.com
 */
class YesterdayAdapter (var listOrder : List<Order>?) :
    RecyclerView.Adapter<YesterdayAdapter.ViewHolder>(){

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvOrderName: TextView = view.findViewById(R.id.tv_menu_name)
        val tvAmount: TextView = view.findViewById(R.id.tv_amount)
        val tvPrice: TextView = view.findViewById(R.id.tv_price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_based_on_time, parent, false)
        )
    }

    override fun getItemCount(): Int = listOrder?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val order = listOrder?.get(position)
        if (order != null) {
            holder.tvOrderName.text = order.name
        }
        if (order != null) {
            holder.tvAmount.text = order.amount.toString()
        }
        if (order != null) {
            holder.tvPrice.text = order.price.toString()
        }
    }

}