package com.example.taverent

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AkunSewa.newInstance] factory method to
 * create an instance of this fragment.
 */
class AkunSewa : Fragment() {
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_akun_sewa, container, false)
    }
    var id_pemilik = ""
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var nama_pemilik = ""
        id_pemilik = arguments?.getString("id_pemilik").toString()
        nama_pemilik = arguments?.getString("nama_pemilik").toString()
        val btneditprofilSewa = view.findViewById<LinearLayout>(R.id.btneditprofilSewa)
        val txOwnerUsername1 = view.findViewById<TextView>(R.id.txOwnerUsername)
        txOwnerUsername1.setText(nama_pemilik)
        btneditprofilSewa.setOnClickListener {
            val intent = Intent(view.context,ProfileSewa::class.java)
            intent.putExtra("id_pemilik",id_pemilik)
            activity?.runOnUiThread { startActivity(intent) }
        }

    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AkunSewa.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AkunSewa().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}