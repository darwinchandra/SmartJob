package com.example.judes_darwinchandra.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.judes_darwinchandra.R

class postsAdapter3 (val numberOfRecyclerView3: ArrayList<String>): RecyclerView.Adapter<postsAdapter3.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val syarat: TextView= itemView.findViewById(R.id.syarat)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.syarat_pekerja,parent,false)
        return ViewHolder(
            view
        )
    }

    override fun getItemCount() = numberOfRecyclerView3.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (position ) {
            0 -> holder.syarat.text="Lulusan S1"
            1 -> holder.syarat.text="Pekerja Tetap"
            2 -> holder.syarat.text="Siap Ditempatkan di Luar Kota"
        }
    }

}