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
import com.squareup.picasso.Picasso
import org.w3c.dom.Text


class postsAdapterInterview(val listInterview: ArrayList<objDetailLoker>): RecyclerView.Adapter<postsAdapterInterview.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val card2: LinearLayout =itemView.findViewById(R.id.card_interview)


        // menambahkan variabel yang dibutuhkan
        var namaCompany : TextView= itemView.findViewById(R.id.nama_perusahaan_interview)
        var posisi:TextView=itemView.findViewById(R.id.jabatan_pegawai_interview)
        var gaji:TextView=itemView.findViewById(R.id.gaji_interview)
        var lokasiPerusahaan:TextView=itemView.findViewById(R.id.loca_perusahaan_Interview)
        var jadwal : TextView=itemView.findViewById(R.id.time_interview)
        val buttonjoin: Button =itemView.findViewById(R.id.btn_join_interview)
        val img:ImageView=itemView.findViewById(R.id.icon_perusahaan_interview)
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
                // data perusahaan di isi sesuai dengan text awalnya yang ada di beranda kemudian di kirim ke DetailPekerjaanActivity.
                var dataPerusahaan=objDetailLoker(namaCompany.text.toString(),posisi.text.toString(),gaji.text.toString(),lokasiPerusahaan.text.toString(),jadwal.text.toString(),img.getTag().toString())
                intent.putExtra(EXTRA_DETAIL_LOKER,dataPerusahaan)
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

    override fun getItemCount() = listInterview.size

    @SuppressLint("WrongConstant")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.namaCompany.setText(listInterview.get(position).namaPerusahaan)
        holder.posisi.setText(listInterview.get(position).posisiLoker)
        holder.lokasiPerusahaan.setText(listInterview.get(position).alamatPerusahaan)
        Picasso.get().load(listInterview.get(position).imageUrl).into(holder.img)
        holder.gaji.setText(listInterview.get(position).gajiLoker)
        holder.jadwal.setText("Interview : "+listInterview.get(position).jadwal)
        holder.img.setTag(listInterview.get(position).imageUrl)

    }

}