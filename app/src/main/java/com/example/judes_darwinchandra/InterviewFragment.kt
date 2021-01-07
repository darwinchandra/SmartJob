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
import com.example.judes_darwinchandra.adapter.postsAdapterInterview
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
class InterviewFragment : Fragment() {


    @SuppressLint("WrongConstant")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val recyclerView_interview: RecyclerView
        val view:View=inflater.inflate(R.layout.fragment_interview, container, false)
        recyclerView_interview=view.findViewById(R.id.recyclerView_interview)

        val numberOfRecyclerView_Interview: ArrayList<String> = ArrayList()
        for (i in 1..10){
            numberOfRecyclerView_Interview.add("Post# $i")
        }
        recyclerView_interview.adapter=
            postsAdapterInterview(
                numberOfRecyclerView_Interview
            )
        recyclerView_interview?.layoutManager= LinearLayoutManager(requireContext(), OrientationHelper.VERTICAL,false)

        // Inflate the layout for this fragment
        return view
    }
}