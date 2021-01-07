package com.example.judes_darwinchandra.adapter

import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.judes_darwinchandra.DetailPekerjaanActivity
import com.example.judes_darwinchandra.R

class postsAdapterApplied(val numberOfRecyclerView_Applied: ArrayList<String>): RecyclerView.Adapter<postsAdapterApplied.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val card2: LinearLayout =itemView.findViewById(R.id.card_applied)
        val icon_person: ImageView=itemView.findViewById(R.id.icon_perusahaan_applied)
        init{
            itemView.setOnClickListener{
                val position:Int=adapterPosition
                val intent = Intent(itemView.context, DetailPekerjaanActivity::class.java)
                itemView.context.startActivity(intent)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View= LayoutInflater.from(parent.context).inflate(R.layout.postapplied,parent,false)
        return ViewHolder(
            view
        )
    }

    override fun getItemCount() = numberOfRecyclerView_Applied.size

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    }

}