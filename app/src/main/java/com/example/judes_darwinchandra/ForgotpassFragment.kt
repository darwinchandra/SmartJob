package com.example.judes_darwinchandra

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.fragment_forgotpass.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ForgotpassFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ForgotpassFragment : Fragment() {
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

    //tambahan interfaceDataText
    private lateinit var interfaceDataText:InterfaceDataText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //replace return dengan val view karena view mau digunakan untuk find id pada fragement
        val view = inflater.inflate(R.layout.fragment_forgotpass, container, false)
        //tambahan interfaceDataText dengan aktifikas dari InterfaceDataText
        interfaceDataText=activity as InterfaceDataText
        // Inisialisasi komponen atau property dari fragment
        val btnreset= view.findViewById<Button>(R.id.btnResetPass)
        val Emailuser= view.findViewById<TextInputEditText>(R.id.inputEmailFP1)

        //penambahan action click btnreset
        btnreset.setOnClickListener {
            if(android.util.Patterns.EMAIL_ADDRESS.matcher(Emailuser.text.toString()).matches()){
                //mengisi kirimtext dengan emailuser
                interfaceDataText.kirimText(Emailuser.text.toString())
            }
            else if(inputEmailFP1.text.toString().trim().isEmpty()){
                Emailuser.setError("Email can't be empty")
            }
            else{
                Emailuser.setError("Invalid Email")
            }
        }
        return view
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btn_regis_forgotpass.setOnClickListener {
            val intent= Intent(context,RegisterActivity::class.java)
            startActivity(intent)
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
         * @return A new instance of fragment ForgotpassFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ForgotpassFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}