package com.example.taverent

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.taverent.databinding.FragmentAdminAccountBinding
import com.example.taverent.databinding.FragmentAdminListBinding


class AdminAccountFragment : Fragment() {
    private lateinit var binding: FragmentAdminAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAdminAccountBinding.inflate(layoutInflater)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button8.setOnClickListener{

        }
        binding.button9.setOnClickListener {

        }
    }
}