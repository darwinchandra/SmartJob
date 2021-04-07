package com.example.judes_darwinchandra.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.judes_darwinchandra.Data.MyNewsData
import com.example.judes_darwinchandra.R
import com.squareup.picasso.Picasso
import java.util.ArrayList

class postsAdapterNews(
    val context: Context,
    val list: ArrayList<MyNewsData>
): RecyclerView.Adapter<postsAdapterNews.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title=itemView.findViewById<TextView>(R.id.title_news)
        val img=itemView.findViewById<ImageView>(R.id.img_News)
        val desc_news=itemView.findViewById<TextView>(R.id.desc_news)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.postnews, parent, false)



        return ViewHolder(
            view
        )
    }

    override fun getItemCount() =list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (list.get(position).title.toString().length>=80){
            holder.title.setText(list.get(position).title.toString().substring(0,76)+"...")
        }
        else{
            holder.title.setText(list.get(position).title.toString())
        }

        holder.desc_news.text=list.get(position).description.toString()
        Picasso.get().load(list.get(position).urlToImage).into(holder.img)
        holder.itemView.setOnClickListener{
            Toast.makeText(context, "goto link : "+list.get(position).url, Toast.LENGTH_SHORT).show()
        }


    }
}

