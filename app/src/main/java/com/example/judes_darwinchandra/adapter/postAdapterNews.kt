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

// adapter dengan parameter context dan juga list yang akan dijadikan data untuk isi dari masing" Recycler
class postsAdapterNews(val context: Context, val list: ArrayList<MyNewsData>): RecyclerView.Adapter<postsAdapterNews.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        // init 3 data yang akan diganti pada tampilan recycler
        val title=itemView.findViewById<TextView>(R.id.title_news)
        val img=itemView.findViewById<ImageView>(R.id.img_News)
        val desc_news=itemView.findViewById<TextView>(R.id.desc_news)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflater
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.postnews, parent, false)
        return ViewHolder(
            view
        )
    }
    // jumlah recyclerview tergantung jumlah ArrayList newsnya
    override fun getItemCount() =list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // menentukan title dengan limit 80 charakter
        if (list.get(position).title.toString().length>80){
            // mengambil sub string jika judul terlalu panjang, dan menambahkan ... di belakangnya
            holder.title.setText(list.get(position).title.toString().substring(0,76)+"...")
        }
        else{
            // kondisi ketika jumlh karakter judul <= dari 80 karakter
            holder.title.setText(list.get(position).title.toString())
        }
        // menentukan isi desc
        holder.desc_news.text=list.get(position).description.toString()
        // menentukan isi imgview dengan bantuan dependency picasso
        Picasso.get().load(list.get(position).urlToImage).into(holder.img)
        //handlee click pada tiap recyclerview
        holder.itemView.setOnClickListener{
            Toast.makeText(context, "goto link : "+list.get(position).url, Toast.LENGTH_SHORT).show()
        }


    }
}

