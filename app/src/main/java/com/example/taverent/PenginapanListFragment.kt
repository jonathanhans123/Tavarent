package com.example.taverent

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.taverent.databinding.FragmentAdminListBinding
import com.example.taverent.databinding.FragmentPenginapanListBinding
import com.example.taverent.databinding.FragmentUsersListBinding
import org.json.JSONArray


class PenginapanListFragment : Fragment() {
    private lateinit var binding: FragmentPenginapanListBinding
    private lateinit var rvPenginapanAdminHome: RVPenginapanAdminHome

    var WS_HOST = ""

    var penginapans: ArrayList<Penginapan> = ArrayList()
    var penginapansSearch: ArrayList<Penginapan> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPenginapanListBinding.inflate(layoutInflater)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        WS_HOST = resources.getString(R.string.WS_HOST)

        refreshPenginapan(view)

        rvPenginapanAdminHome = RVPenginapanAdminHome(penginapansSearch,R.layout.rv_penginapan_admin){view, idx ->
            val popup = PopupMenu(view.context,view)
            popup.inflate(R.menu.popupmenu1)

            popup.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener {
                when (it.itemId){
                    R.id.menudetail->{
//                        val intent = Intent(view.context,)
                    }
                    R.id.menuban->{
                        //ban user
                        activity?.runOnUiThread {
                            rvPenginapanAdminHome.notifyDataSetChanged()}
                    }
                }
                true
            })
            popup.show()
        }
        binding.rvPenginapan.adapter = rvPenginapanAdminHome
        binding.editTextTextPersonName.addTextChangedListener {object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                TODO("Not yet implemented")
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                search(binding.editTextTextPersonName.text.toString())
            }

            override fun afterTextChanged(p0: Editable?) {
                TODO("Not yet implemented")
            }
        }}

        binding.rvPenginapan.layoutManager = LinearLayoutManager(view.context,
            LinearLayoutManager.VERTICAL,false)

        search("")
    }

    fun search(s:String){
        penginapans.clear()
        for (i in 0 until penginapans.size){
            if (penginapans[i].nama!!.contains(s,true)){
                penginapansSearch.add(penginapans[i])
            }
        }
        rvPenginapanAdminHome.notifyDataSetChanged()
    }
    fun refreshPenginapan(view:View){
        val strReq = object : StringRequest(
            Method.GET,"$WS_HOST/penginapan/list",
            Response.Listener {
                val obj: JSONArray = JSONArray(it)
                penginapans.clear()
                for (i in 0 until obj.length()){
                    val o = obj.getJSONObject(i)
                    val id = o.getInt("id")
                    val nama = o.getString("nama")
                    val alamat = o.getString("alamat")
                    val deskripsi = o.getString("deskripsi")
                    val fasilitas = o.getString("fasilitas")
                    var jk_boleh = o.getString("jk_boleh")
                    var tipe = o.getString("tipe")
                    var harga = o.getString("harga").toInt()
                    var koordinat = o.getString("koordinat")
                    var id_pemilik = o.getInt("id_pemilik")
                    val p = Penginapan(id,nama,alamat,deskripsi,fasilitas,jk_boleh,tipe,harga,koordinat,id_pemilik)
                    penginapans.add(p)
                    rvPenginapanAdminHome.notifyDataSetChanged()
                }
            },
            Response.ErrorListener {
                Toast.makeText(view.context, "WS_ERROR1", Toast.LENGTH_SHORT).show()
            }
        ){}
        val queue: RequestQueue = Volley.newRequestQueue(view.context)
        queue.add(strReq)
    }
}