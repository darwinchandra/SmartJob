package com.example.judes_darwinchandra.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.judes_darwinchandra.ChatRoomActivity
import com.example.judes_darwinchandra.DetailPekerjaanActivity
import com.example.judes_darwinchandra.R
import com.example.judes_darwinchandra.VideoCallActivity


class postsAdapterInterview(val numberOfRecyclerView_Interview: ArrayList<String>): RecyclerView.Adapter<postsAdapterInterview.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val card2: RelativeLayout =itemView.findViewById(R.id.card_interview)
        val icon_person: ImageView =itemView.findViewById(R.id.icon_perusahaan_interview)
        val buttonjoin: Button =itemView.findViewById(R.id.btn_join_interview)
        init{
            buttonjoin.setOnClickListener{
                val position:Int=adapterPosition
                val intent = Intent(itemView.context, VideoCallActivity::class.java)
                itemView.context.startActivity(intent)
            }
        }
        init{
            itemView.setOnClickListener{
                val position:Int=adapterPosition
                val intent = Intent(itemView.context, DetailPekerjaanActivity::class.java)
                itemView.context.startActivity(intent)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View= LayoutInflater.from(parent.context).inflate(R.layout.postinterview,parent,false)
        return ViewHolder(
            view
        )
    }

    override fun getItemCount() = numberOfRecyclerView_Interview.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


    }

}