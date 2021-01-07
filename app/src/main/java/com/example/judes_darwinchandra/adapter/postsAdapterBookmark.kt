package com.example.judes_darwinchandra.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.judes_darwinchandra.BookmarkedActivity
import com.example.judes_darwinchandra.DetailPekerjaanActivity
import com.example.judes_darwinchandra.R
import com.example.judes_darwinchandra.VideoCallActivity

class postsAdapterBookmark(val numberOfRecyclerView_bookmarked: ArrayList<String>): RecyclerView.Adapter<postsAdapterBookmark.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener {
                val position: Int = adapterPosition
                val intent = Intent(itemView.context, DetailPekerjaanActivity::class.java)
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.postbookmark, parent, false)
        return ViewHolder(
            view
        )
    }

    override fun getItemCount() = numberOfRecyclerView_bookmarked.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


    }
}

