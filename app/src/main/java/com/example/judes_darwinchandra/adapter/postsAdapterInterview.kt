package com.example.judes_darwinchandra.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.judes_darwinchandra.*
import org.w3c.dom.Text


class postsAdapterInterview(val numberOfRecyclerView_Interview: ArrayList<objDetailLoker>): RecyclerView.Adapter<postsAdapterInterview.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val card2: RelativeLayout =itemView.findViewById(R.id.card_interview)
        val numberOfRecyclerView4: ArrayList<String> = ArrayList()


        // menambahkan variabel yang dibutuhkan
        var namaCompany : TextView= itemView.findViewById(R.id.nama_perusahaan_interview)
        var posisi:TextView=itemView.findViewById(R.id.jabatan_pegawai_interview)
        var gaji:TextView=itemView.findViewById(R.id.gaji_interview)
        var lokasiPerusahaan:TextView=itemView.findViewById(R.id.loca_perusahaan_Interview)
        var jadwal : TextView=itemView.findViewById(R.id.time_interview)
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

    @SuppressLint("WrongConstant")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        for (i in 1..3){
            holder.numberOfRecyclerView4.add("Post# $i")
        }
        holder.namaCompany.setText(numberOfRecyclerView_Interview.get(position).namaPerusahaan)
        holder.posisi.setText(numberOfRecyclerView_Interview.get(position).posisiLoker)
        holder.lokasiPerusahaan.setText(numberOfRecyclerView_Interview.get(position).alamatPerusahaan)
        holder.gaji.setText(numberOfRecyclerView_Interview.get(position).gajiLoker)
        holder.jadwal.setText(numberOfRecyclerView_Interview.get(position).jadwal)


    }

}