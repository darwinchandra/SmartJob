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
import com.example.judes_darwinchandra.*

class postsAdapterProfile(val numberOfRecyclerView_profile: ArrayList<String>): RecyclerView.Adapter<postsAdapterProfile.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ic_listview: ImageView=itemView.findViewById(R.id.icon_profile_listview)
        val list_name: TextView=itemView.findViewById(R.id.profile_listview_text)
        val card : LinearLayout=itemView.findViewById(R.id.card_Layout_profile)
        init{
            card.setOnClickListener{
                val position:Int=adapterPosition
                if (position==0){
                    val intent = Intent(itemView.context, SettingProfileActivity::class.java)
                    itemView.context.startActivity(intent)
                }
                if (position==1){
                    val intent = Intent(itemView.context, BookmarkedActivity::class.java)
                    itemView.context.startActivity(intent)
                }
                if (position==2){
                    val intent = Intent(itemView.context, HelpActivity::class.java)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.postprofile,parent,false)
        return ViewHolder(
            view
        )
    }

    override fun getItemCount() = numberOfRecyclerView_profile.size


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (position){
            0 -> {

                true
            }
            1 -> {
                holder.ic_listview.setImageResource(R.drawable.ic_baseline_bookmark_24)
                holder.list_name.setText("Bookmark")
                true
            }
            2 -> {
                holder.ic_listview.setImageResource(R.drawable.ic_baseline_help_24)
                holder.list_name.setText("Help")
                true
            }
        }
    }

}