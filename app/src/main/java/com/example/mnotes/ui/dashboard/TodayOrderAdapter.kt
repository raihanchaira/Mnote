package com.example.mnotes.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.mnotes.R
import com.example.mnotes.model.Order

/** Raihan Chaira on 5/24/2023
 * raihanchaira21@gmail.com
 */
class TodayOrderAdapter (var listOrder : List<Order>?) :
    RecyclerView.Adapter<TodayOrderAdapter.ViewHolder>(){

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvOrderName: TextView = view.findViewById(R.id.tv_menu_name_today)
        val tvAmount: TextView = view.findViewById(R.id.tv_amount_today)
        val tvPrice: TextView = view.findViewById(R.id.tv_price_today)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_today_order, parent, false)
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