package com.example.judes_darwinchandra

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.judes_darwinchandra.adapter.postsAdapter1
import com.example.judes_darwinchandra.adapter.postsAdapter2
import kotlinx.android.synthetic.main.fragment_beranda.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BerandaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BerandaFragment : Fragment() {

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }


    @SuppressLint("WrongConstant")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val recyclerView1:RecyclerView
        val recyclerView2:RecyclerView
        val view:View=inflater.inflate(R.layout.fragment_beranda, container, false)
        recyclerView1=view.findViewById(R.id.recyclerView1)
        recyclerView2=view.findViewById(R.id.recyclerView2)

        var listdata1=ArrayList<objDetailLoker>()

        listdata1.add(objDetailLoker("PT. Indah Kiat Tbk", "Financial Manager", "10 jt/Bulan",
            "Medan - Sumatera Utara","15-May-20  (10:30 AM)","https://ceowatermandate.org/wp-content/uploads/2021/01/indah-kiat-logo.jpg"))
        listdata1.add(objDetailLoker("PT. Bukit Asam Tbk", "CEO", "15 jt/Bulan",
            "Jakarta Utara","19-May-20  (9:30 AM)","https://tambang.itm.ac.id/wp-content/uploads/2019/03/logo-pt-bukit-asam.jpg"))
        listdata1.add(objDetailLoker("PT. Aneka Tambang Tbk", "IT Support", "5 jt/Bulan",
            "Bogor - Jawa Barat","15-July-20  (8:30 AM)","https://www.dreamcareerbuilder.com/uploads/employers/5754_ECLB.png"))


        val numberOfRecyclerView2: ArrayList<String> = ArrayList()
        for (i in 1..10){
            numberOfRecyclerView2.add("Post# $i")
        }
        recyclerView2.adapter=
            postsAdapter2(
                numberOfRecyclerView2
            )
        recyclerView2?.layoutManager= LinearLayoutManager(context, OrientationHelper.VERTICAL,false)


        val numberOfRecyclerView1: ArrayList<String> = ArrayList()
        for (i in 1..5){
            numberOfRecyclerView1.add("Post# $i")
        }
        recyclerView1.layoutManager= LinearLayoutManager(recyclerView1.context, OrientationHelper.HORIZONTAL,false)
        recyclerView1.adapter=
            postsAdapter1(
                listdata1
            )


        return view
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        topAppBar.setNavigationOnClickListener {

            val intent = Intent(requireContext(), LocationActivity::class.java)
            startActivity(intent)
            Toast.makeText(context, "location", Toast.LENGTH_SHORT).show()
            // Handle navigation icon press
        }
        topAppBar.setOnMenuItemClickListener {
            when (it.itemId){
                R.id.filter -> {
                    Toast.makeText(context, "hey", Toast.LENGTH_SHORT).show()
                    val intent = Intent(requireContext(), FilterActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.search -> {
                    Toast.makeText(context, "hello", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
    }

}