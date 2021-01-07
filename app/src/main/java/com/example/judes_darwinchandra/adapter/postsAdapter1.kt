package com.example.judes_darwinchandra.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.judes_darwinchandra.ChatRoomActivity
import com.example.judes_darwinchandra.DetailPekerjaanActivity
import com.example.judes_darwinchandra.R

class postsAdapter1(val numberOfRecyclerView1: ArrayList<String>): RecyclerView.Adapter<postsAdapter1.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val card: RelativeLayout=itemView.findViewById(R.id.card_top_loker)
        val numberOfRecyclerView3: ArrayList<String> = ArrayList()
        val recyclerView3:RecyclerView=itemView.findViewById(R.id.recyclerView3)

        init{
            itemView.setOnClickListener{
                val position:Int=adapterPosition
                val intent = Intent(itemView.context, DetailPekerjaanActivity::class.java)
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): postsAdapter1.ViewHolder {
        val view: View= LayoutInflater.from(parent.context).inflate(R.layout.row_post,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount() = numberOfRecyclerView1.size

    @SuppressLint("WrongConstant")
    override fun onBindViewHolder(holder: postsAdapter1.ViewHolder, position: Int) {
        for (i in 1..3){
            holder.numberOfRecyclerView3.add("Post# $i")
        }
        holder.recyclerView3.layoutManager= LinearLayoutManager(holder.recyclerView3.context, OrientationHelper.HORIZONTAL,false)
        holder.recyclerView3.adapter=
            postsAdapter3(holder.numberOfRecyclerView3)
    }



}