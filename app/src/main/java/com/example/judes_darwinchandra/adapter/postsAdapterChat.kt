package com.example.judes_darwinchandra.adapter

import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.judes_darwinchandra.*


class postsAdapterChat(val numberOfRecyclerView_chat: ArrayList<String>): RecyclerView.Adapter<postsAdapterChat.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val card2: LinearLayout =itemView.findViewById(R.id.card_chat)
        val icon_person: ImageView=itemView.findViewById(R.id.icon_person)
        val person_name: TextView=itemView.findViewById(R.id.person_name)
        val chat_desc: TextView=itemView.findViewById(R.id.chat_desc)
        val chat_time: TextView=itemView.findViewById(R.id.chat_time)
        val chat_notif: TextView=itemView.findViewById(R.id.chat_notif)

        init{
            itemView.setOnClickListener{
                val position:Int=adapterPosition
                val intent = Intent(itemView.context, ChatRoomActivity::class.java)
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.postchat,parent,false)
        return ViewHolder(
            view
        )
    }

    override fun getItemCount() = numberOfRecyclerView_chat.size


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.icon_person.clipToOutline = true
        when (position){
            0 -> {
                holder.icon_person.setImageResource(R.drawable.person1)
                holder.person_name.setText("Darwin")
                holder.chat_desc.setText("pada hari senin boleh datang untuk interview")
                holder.chat_time.setText("8:10 AM")
                holder.chat_notif.setText("12")
                true
            }
            1 -> {
                holder.icon_person.setImageResource(R.drawable.person2)
                holder.person_name.setText("Julian Fellyco")
                holder.chat_desc.setText("Lamaran segera dikirim")
                holder.chat_time.setText("Yesterday")
                holder.chat_notif.setText("2")
                true
            }
            2 -> {
                holder.icon_person.setImageResource(R.drawable.person3)
                holder.person_name.setText("Seteven Ang")
                holder.chat_desc.setText("Selamat and diterima dalam perusahaan kami")
                holder.chat_time.setText("20 Jan 19 ")
                holder.chat_notif.setText("100")
                true
            }
        }
    }

}