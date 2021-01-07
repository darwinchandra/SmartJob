package com.example.judes_darwinchandra

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.contentValuesOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.judes_darwinchandra.adapter.postsAdapterChat
import com.example.judes_darwinchandra.adapter.postsAdapterProfile
import kotlinx.android.synthetic.main.fragment_profile.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("WrongConstant")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val recyclerView_profile: RecyclerView
        val view:View=inflater.inflate(R.layout.fragment_profile, container, false)
        recyclerView_profile=view.findViewById(R.id.recyclerView_profile)

        val numberOfRecyclerView_profile: ArrayList<String> = ArrayList()
        for (i in 1..3){
            numberOfRecyclerView_profile.add("Post# $i")
        }
        recyclerView_profile.adapter=
            postsAdapterProfile(
                numberOfRecyclerView_profile
            )
        recyclerView_profile?.layoutManager= LinearLayoutManager(context, OrientationHelper.VERTICAL,false)




        // Inflate the layout for this fragment
        return view
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        icon_person_profile.clipToOutline = true
        btn_logout.setOnClickListener{
            activity?.finish();
        }
        btn_manage_profil.setOnClickListener{
            val intent= Intent(context,ManageProfilActivity::class.java)
            startActivity(intent)
        }
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProfileFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}