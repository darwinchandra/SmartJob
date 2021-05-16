package com.example.judes_darwinchandra.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.judes_darwinchandra.*
import org.w3c.dom.Text

class postsAdapter1(val list: ArrayList<objDetailLoker>): RecyclerView.Adapter<postsAdapter1.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val card: RelativeLayout=itemView.findViewById(R.id.card_top_loker)
        val numberOfRecyclerView3: ArrayList<String> = ArrayList()
        val recyclerView3:RecyclerView=itemView.findViewById(R.id.recyclerView3)

        // menambahkan variabel yang dibutuhkan
        var namaCompany : TextView= itemView.findViewById(R.id.nama_perusahaan_row)
        var posisi:TextView=itemView.findViewById(R.id.jabatan_pegawai_row)
        var gaji:TextView=itemView.findViewById(R.id.string_salary_row)
        var lokasiPerusahaan:TextView=itemView.findViewById(R.id.lokasi_perusahaan_row)
        init{
            itemView.setOnClickListener{
                //ketika recyclerview di click, maka mengirimkan data parcel kepada DetailPekerjaanActivity.
                val position:Int=adapterPosition
                var intent = Intent(itemView.context, DetailPekerjaanActivity::class.java)
                // data perusahaan di isi sesuai dengan text awalnya yang ada di beranda kemudian di kirim ke DetailPekerjaanActivity.
                var dataPerusahaan=objDetailLoker(namaCompany.text.toString(),posisi.text.toString(),gaji.text.toString(),lokasiPerusahaan.text.toString())
                intent.putExtra(EXTRA_DETAIL_LOKER,dataPerusahaan)
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): postsAdapter1.ViewHolder {
        val view: View= LayoutInflater.from(parent.context).inflate(R.layout.row_post,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount() = list.size

    @SuppressLint("WrongConstant")
    override fun onBindViewHolder(holder: postsAdapter1.ViewHolder, position: Int) {
        for (i in 1..3){
            holder.numberOfRecyclerView3.add("Post# $i")
        }
        holder.namaCompany.setText(list.get(position).namaPerusahaan)
        holder.posisi.setText(list.get(position).posisiLoker)
        holder.lokasiPerusahaan.setText(list.get(position).alamatPerusahaan)
        holder.gaji.setText(list.get(position).gajiLoker)
        holder.recyclerView3.layoutManager= LinearLayoutManager(holder.recyclerView3.context, OrientationHelper.HORIZONTAL,false)
        holder.recyclerView3.adapter=
            postsAdapter3(holder.numberOfRecyclerView3)
    }



}