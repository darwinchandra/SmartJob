package com.example.judes_darwinchandra

import android.annotation.SuppressLint
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.judes_darwinchandra.adapter.postsAdapterInterview
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_msg.*
import java.lang.reflect.Type

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

        var listdata1=ArrayList<objDetailLoker>()
        listdata1.add(objDetailLoker("PT. Indah Kiat Tbk", "Manager", "10 jt/Bulan",
            "Medan - Sumatera Utara","Interview : 15-May-20  (10:30 AM)","https://ceowatermandate.org/wp-content/uploads/2021/01/indah-kiat-logo.jpg"))
        listdata1.add(objDetailLoker("PT. Bukit Asam Tbk", "CEO", "15 jt/Bulan",
            "Jakarta Utara","Interview : 19-May-20  (9:30 AM)","https://tambang.itm.ac.id/wp-content/uploads/2019/03/logo-pt-bukit-asam.jpg"))
        listdata1.add(objDetailLoker("PT. Aneka Tambang Tbk", "IT Support", "5 jt/Bulan",
            "Bogor - Jawa Barat","Interview : 15-July-20  (8:30 AM)","https://www.dreamcareerbuilder.com/uploads/employers/5754_ECLB.png"))
        val listInterview: ArrayList<String> = ArrayList()
        for (i in 1..10){
            listInterview.add("Post# $i")
        }
        recyclerView_interview.adapter=
            postsAdapterInterview(
                listdata1
            )
        recyclerView_interview?.layoutManager= LinearLayoutManager(requireContext(), OrientationHelper.VERTICAL,false)

        // Inflate the layout for this fragment
        return view
    }

}