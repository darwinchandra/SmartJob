package com.example.judes_darwinchandra.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.judes_darwinchandra.Data.myContact
import com.example.judes_darwinchandra.R
import kotlinx.android.synthetic.main.layout_recy_view.view.*

class postsAdapterInvite (private val contact : MutableList<myContact>): RecyclerView.Adapter<myHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myHolder {
        return myHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_recy_view, parent,false)
        )
    }

    override fun onBindViewHolder(holder: myHolder, position: Int) {
        holder.contactName.setText(contact[position].nama)
        holder.contactNumber.setText(contact[position].nomorHp)

    }
    override fun getItemCount(): Int = contact.size


}
class myHolder(view : View) : RecyclerView.ViewHolder(view){
    val contactName = view.itemName
    val contactNumber = view.itemNumber

}