package com.example.judes_darwinchandra.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.judes_darwinchandra.Data.myContact
import com.example.judes_darwinchandra.R
import kotlinx.android.synthetic.main.layout_recy_view.view.*


//membuat data class yg terdiri context dan mutablelist mycontact dan mengambil isi holder ke recycleview
class postsAdapterInvite (private val context : Context , private val contact : MutableList<myContact>): RecyclerView.Adapter<myHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myHolder {
        //mengembalikan hasil holder
        return myHolder(
            //mengambil layout dari parent dan mengisinya dengan layout dari recycle view
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_recy_view, parent,false)
        )
    }

    override fun onBindViewHolder(holder: myHolder, position: Int) {
        //holder nama mengambil dari contact
        holder.contactName.setText(contact[position].nama)
        //holder number mengambil nomor dari contact
        holder.contactNumber.setText(contact[position].nomorHp)
        //diisi dengan gambar yang sudah diberikan
        holder.contactImage.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.profilepic))

    }
    override fun getItemCount(): Int = contact.size


}
//membuat class holder untuk dapat membuat recycleview
class myHolder(view : View) : RecyclerView.ViewHolder(view){
    //init  contact yang isinya nama nomor dan gambar
    val contactName = view.itemName
    val contactNumber = view.itemNumber
    val contactImage = view.profilecontact

}