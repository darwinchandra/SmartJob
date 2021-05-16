package com.example.judes_darwinchandra.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.judes_darwinchandra.*

class postsAdapter2(val numberOfRecyclerView2: ArrayList<String>): RecyclerView.Adapter<postsAdapter2.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val card2: LinearLayout =itemView.findViewById(R.id.card_recent_loker)

        var namaCompany : TextView = itemView.findViewById(R.id.nama_perusahaan_col)
        var posisi: TextView =itemView.findViewById(R.id.jabatan_pegawai_col)
        var gaji: TextView =itemView.findViewById(R.id.string_salary_col)
        var lokasiPerusahaan: TextView =itemView.findViewById(R.id.lokasi_perusahaan_col)
        init{
            itemView.setOnClickListener{
                val position:Int=adapterPosition
                val intent = Intent(itemView.context, DetailPekerjaanActivity::class.java)
                var dataPerusahaan= objDetailLoker(namaCompany.text.toString(),posisi.text.toString(),gaji.text.toString(),lokasiPerusahaan.text.toString())
                intent.putExtra(EXTRA_DETAIL_LOKER,dataPerusahaan)
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View= LayoutInflater.from(parent.context).inflate(R.layout.column_post,parent,false)
        return ViewHolder(
            view
        )
    }

    override fun getItemCount() = numberOfRecyclerView2.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

}