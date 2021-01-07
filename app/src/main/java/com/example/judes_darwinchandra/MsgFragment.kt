package com.example.judes_darwinchandra

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.judes_darwinchandra.adapter.postsAdapter2
import com.example.judes_darwinchandra.adapter.postsAdapterChat
import kotlinx.android.synthetic.main.fragment_msg.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BerandaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MsgFragment : Fragment() {


    @SuppressLint("WrongConstant")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val recyclerView_chat: RecyclerView
        val view:View=inflater.inflate(R.layout.fragment_msg, container, false)
        recyclerView_chat=view.findViewById(R.id.recyclerView_chat)

        val numberOfRecyclerView_chat: ArrayList<String> = ArrayList()
        for (i in 1..10){
            numberOfRecyclerView_chat.add("Post# $i")
        }
        recyclerView_chat.adapter=
            postsAdapterChat(
                numberOfRecyclerView_chat
            )
        recyclerView_chat?.layoutManager= LinearLayoutManager(context, OrientationHelper.VERTICAL,false)



        // Inflate the layout for this fragment
        return view
    }
}