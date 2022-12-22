package com.example.taverent

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.ConditionVariable
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.taverent.databinding.ActivitySewaMainBinding
import org.json.JSONArray

class SewaMain : AppCompatActivity() {
    private lateinit var binding: ActivitySewaMainBinding
    private lateinit var pemilik: Pemilik
    var id_pemilik = ""
    var username=""
    var nama_pemilik = ""
    var WS_HOST = ""
    var pemiliks: ArrayList<Pemilik> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WS_HOST = resources.getString(R.string.WS_HOST)
        binding = ActivitySewaMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        id_pemilik = intent.getStringExtra("id_pemilik").toString()
        nama_pemilik = intent.getStringExtra("nama_pemilik").toString()
        username= intent.getStringExtra("username").toString()

        pemilik = intent.getParcelableExtra<Pemilik>("pemilik") as Pemilik
        Log.e("tag",pemilik.toString())



        val fragreplace=HomeSewa()
        val bundle = Bundle()
        bundle.putParcelable("pemilik",pemilik)
        bundle.putString("id_pemilik",id_pemilik)
        bundle.putString("nama_pemilik",nama_pemilik)
        bundle.putString("username",username)
        fragreplace.arguments = bundle
        val transaction = supportFragmentManager.beginTransaction().replace(
            R.id.frag4,fragreplace
        ).setReorderingAllowed(true).commit()

        binding.bottomNavSewa.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.btnHomeSewa -> {
                    val fragreplace = HomeSewa()
                    val bundle = Bundle()
                    bundle.putParcelable("pemilik", pemilik)
                    bundle.putString("id_pemilik", id_pemilik)
                    bundle.putString("nama_pemilik", nama_pemilik)
                    bundle.putString("username", username)
                    fragreplace.arguments = bundle
                    val transaction = supportFragmentManager.beginTransaction().replace(
                        R.id.frag4, fragreplace
                    ).setReorderingAllowed(true).commit()
                }
                R.id.btnChatSewa -> {
                    val fragreplace = ChatSewa()
                    val bundle = Bundle()
                    bundle.putParcelable("pemilik", pemilik)
                    bundle.putString("id_pemilik", id_pemilik)
                    bundle.putString("nama_pemilik", nama_pemilik)
                    bundle.putString("username", username)
                    fragreplace.arguments = bundle
                    val transaction = supportFragmentManager.beginTransaction().replace(
                        R.id.frag4, fragreplace
                    ).setReorderingAllowed(true).commit()
                }
                R.id.btnKelolaSewa -> {
                    val fragreplace = KelolaSewa()
                    val bundle = Bundle()
                    bundle.putParcelable("pemilik", pemilik)
                    bundle.putString("id_pemilik", id_pemilik)
                    bundle.putString("nama_pemilik", nama_pemilik)
                    bundle.putString("username", username)
                    fragreplace.arguments = bundle
                    val transaction = supportFragmentManager.beginTransaction().replace(
                        R.id.frag4, fragreplace
                    ).setReorderingAllowed(true).commit()
                }
                R.id.btnStatistikSewa -> {
                    val fragreplace = StatistikSewa()
                    val bundle = Bundle()
                    bundle.putParcelable("pemilik", pemilik)
                    bundle.putString("id_pemilik", id_pemilik)
                    bundle.putString("nama_pemilik", nama_pemilik)
                    bundle.putString("username", username)
                    fragreplace.arguments = bundle
                    val transaction = supportFragmentManager.beginTransaction().replace(
                        R.id.frag4, fragreplace
                    ).setReorderingAllowed(true).commit()
                }
                R.id.btnAkunSewa -> {
                    val fragreplace = AkunSewa()
                    val bundle = Bundle()
                    bundle.putParcelable("pemilik", pemilik)
                    bundle.putString("id_pemilik", id_pemilik)
                    bundle.putString("nama_pemilik", nama_pemilik)
                    bundle.putString("username", username)
                    fragreplace.arguments = bundle
                    val transaction = supportFragmentManager.beginTransaction().replace(
                        R.id.frag4, fragreplace
                    ).setReorderingAllowed(true).commit()
                }
            }
            return@setOnItemSelectedListener true
        }


    }


}