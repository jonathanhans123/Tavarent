package com.example.taverent

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.taverent.databinding.FragmentAdminAnnounceBinding
import com.example.taverent.databinding.FragmentPenginapCariBinding
import com.example.taverent.databinding.FragmentPenginapChatBinding
import com.example.taverent.databinding.FragmentPenginapHomeBinding
import org.json.JSONArray

class PenginapHomeFragment : Fragment() {
    private lateinit var binding: FragmentPenginapHomeBinding
    var WS_HOST = ""
    private lateinit var penginap: Penginap
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPenginapHomeBinding.inflate(layoutInflater)
        val view = binding.root
        return view
    }

    var pembayarans: ArrayList<Pembayaran> = ArrayList()
    var penginapans: ArrayList<Penginapan> = ArrayList()

    private lateinit var rvPembayaran: RVPembayaran
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        WS_HOST = resources.getString(R.string.WS_HOST)

        penginap = arguments?.getParcelable<Penginap>("penginap") as Penginap
        refreshPenginapan(view)
        refreshPembayaran(view)

        rvPembayaran = RVPembayaran(pembayarans)
        binding.rvPembayaran.adapter = rvPembayaran
        binding.rvPembayaran.layoutManager = LinearLayoutManager(view.context,LinearLayoutManager.VERTICAL,false)

    }

    fun refreshPembayaran(view:View){
        val strReq = object : StringRequest(
            Method.POST,"$WS_HOST/pembayaran/list/penginapan",
            Response.Listener {
                val obj: JSONArray = JSONArray(it)
                pembayarans.clear()
                for (i in 0 until obj.length()){
                    val o = obj.getJSONObject(i)
                    val id = o.getInt("id")
                    val total = o.getInt("total")
                    val tanggal_mulai = o.getString("tanggal_mulai")
                    val tanggal_selesai = o.getString("tanggal_selesai")
                    val id_penginap = o.getInt("id_penginap")
                    val id_penginapan = o.getInt("id_penginapan")
                    var id_kupon = 0
                    if (!o.isNull("id_kupon")&&o.has("id_promo")) {
                        id_kupon = o.getInt("id_kupon")
                    }
                    var id_promo = 0
                    if (!o.isNull("id_promo")&&o.has("id_promo")) {
                        id_promo = o.getInt("id_promo")
                    }
                    val nama = o.getString("nama")
                    val p = Pembayaran(id,total,tanggal_mulai,tanggal_selesai,id_penginap,id_penginapan,id_kupon,id_promo,nama)
                    pembayarans.add(p)
                }
                if(pembayarans.size==0){
                    binding.linearbelumadakos.visibility = View.VISIBLE
                }else{
                    binding.rvPembayaran.visibility = View.VISIBLE

                }
            },
            Response.ErrorListener {
                Toast.makeText(view.context, "WS_ERROR1", Toast.LENGTH_SHORT).show()
            }
        ) {
            override fun getParams(): MutableMap<String, String>? {
                val params = HashMap<String, String>()
                params["id_penginap"] = penginap.id.toString()
                return params
            }
        }
        val queue: RequestQueue = Volley.newRequestQueue(view.context)
        queue.add(strReq)
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