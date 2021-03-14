package com.example.judes_darwinchandra

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_forgotpassconfirm.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ForgotpassconfirmFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ForgotpassconfirmFragment : Fragment() {
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
    // init variable untuk isi data yang akan diterima
    var EmailUser:String?=""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //replace return dengan val view karena view mau digunakan untuk find id pada fragement
        val view = inflater.inflate(R.layout.fragment_forgotpassconfirm, container, false)
        //EmailUser diisi dengan argument dengan key "EmailFP"
        EmailUser=arguments?.getString("EmailFP")
        // inisialisasi TextView
        val pesanConfirm=view.findViewById<TextView>(R.id.confirmtextfp)
        //mengubah isi textview dengan data Email
        pesanConfirm.text="We have sent a recovery link to "+ EmailUser

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btnOkresetpassconfirm.setOnClickListener {
            activity?.finish()
        }
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ForgotpassconfirmFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ForgotpassconfirmFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}