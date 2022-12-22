package com.example.taverent

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.example.taverent.databinding.FragmentAdminAnnounceBinding
import com.example.taverent.databinding.FragmentPenginapAccountBinding
import com.example.taverent.databinding.FragmentPenginapCariBinding

class PenginapAccountFragment : Fragment() {
    private lateinit var binding: FragmentPenginapAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPenginapAccountBinding.inflate(layoutInflater)
        val view = binding.root
        return view
    }
    var id_penginap = ""
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var nama_pemilik = ""
        id_penginap = arguments?.getString("id_penginap").toString()
        val btneditprofilpenginap = view.findViewById<LinearLayout>(R.id.btneditprofilpenginap)
        btneditprofilpenginap.setOnClickListener {
            val intent = Intent(view.context,PenginapProfile::class.java)
            intent.putExtra("id_penginap",id_penginap)
            activity?.runOnUiThread { startActivity(intent) }
        }

    }
}