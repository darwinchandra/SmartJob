package com.example.judes_darwinchandra.adapter

import android.content.Intent
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.judes_darwinchandra.BookmarkedActivity
import com.example.judes_darwinchandra.Data.MyBookmarkedData
import com.example.judes_darwinchandra.DetailPekerjaanActivity
import com.example.judes_darwinchandra.R
import com.example.judes_darwinchandra.VideoCallActivity
import com.squareup.picasso.Picasso
import org.w3c.dom.Text
// pembuatran adapter  recyclerview yang akan dipakai pada bookmarkedActivity nantinya dengan parameter Arraylist
class postsAdapterBookmark(val list: ArrayList<MyBookmarkedData>): RecyclerView.Adapter<postsAdapterBookmark.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // init val agar holder dapat mengakses item berikut untuk diubah nantinya
        val img=itemView.findViewById<ImageView>(R.id.icon_perusahaan_bookmark)
        val posisi=itemView.findViewById<TextView>(R.id.jabatan_pegawai_bookmark)
        val nama = itemView.findViewById<TextView>(R.id.nama_perusahaan_bookmark)
        val lokasi = itemView.findViewById<TextView>(R.id.loca_perusahaan_bookmark)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        ///inflate layout
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.postbookmark, parent, false)
        return ViewHolder(
            view
        )
    }
    // banyaknnya recylerview tergantung list data yang dikirim
    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // merubah isi dari posisi, nama , lokasi , dan image dengan isi dari list yang dikirim sebagai parameter adapter nntinya
        holder.posisi.setText(list.get(position).posisi.toString())
        holder.nama.setText(list.get(position).namaPerusahaan.toString())
        holder.lokasi.setText(list.get(position).lokasiPerusahaan.toString())
        Picasso.get().load(list.get(position).imgurl).into(holder.img)

    }
}

