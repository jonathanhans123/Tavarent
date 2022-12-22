package com.example.taverent

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.taverent.databinding.FragmentChatSewaBinding
import com.example.taverent.databinding.FragmentPenginapChatBinding
import org.json.JSONArray

class ChatSewa : Fragment() {

    private lateinit var binding: FragmentChatSewaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentChatSewaBinding.inflate(layoutInflater)
        val view = binding.root
        return view
    }
    var WS_HOST = ""
    private lateinit var rvChatPenginap: RVChatPenginap
    var chats:ArrayList<Chat> = ArrayList()
    var chatsLatest:ArrayList<Chat> = ArrayList()
    private lateinit var pemilik: Pemilik

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        WS_HOST = resources.getString(R.string.WS_HOST)
        pemilik = arguments?.getParcelable<Pemilik>("pemilik") as Pemilik

        val byResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            refreshChat(view,pemilik.id)
            rvChatPenginap.notifyDataSetChanged()
        }
        rvChatPenginap = RVChatPenginap(chatsLatest,"pemilik"){view: View, idx: Int ->
            val intent = Intent(view.context,ChatActivity::class.java)
            intent.putExtra("tipe","pemilik")
            intent.putExtra("penginap",chats[idx].id_penginap)
            intent.putExtra("pemilik",chats[idx].id_pemilik)
            byResult.launch(intent)
        }

        refreshChat(view,pemilik.id)

        binding.rvChatPenginap.adapter = rvChatPenginap
        binding.rvChatPenginap.layoutManager = LinearLayoutManager(view.context,
            LinearLayoutManager.VERTICAL,false)

    }
    fun findlatest(){
        for(i in chats.size-1 downTo 0){
            var exist = false
            for (j in 0 until chatsLatest.size) {
                if (chatsLatest.size!=0) {
                    if (chats[i].id_penginap == chatsLatest[j].id_penginap &&
                        chats[i].id_pemilik == chatsLatest[j].id_pemilik
                    ) {
                        exist = true
                    }
                }
            }
            if (!exist) {
                chatsLatest.add(chats[i])
            }

        }
    }
    fun refreshChat(view:View,id_pemilik:Int) {
        val strReq = object : StringRequest(
            Method.POST, "$WS_HOST/chat/list/pemilik",
            Response.Listener {
                val obj: JSONArray = JSONArray(it)
                chats.clear()
                for (i in 0 until obj.length()) {
                    val o = obj.getJSONObject(i)
                    val id = o.getInt("id")
                    val pesan = o.getString("pesan")
                    val id_penginap = o.getInt("id_penginap")
                    val username_penginap = o.getString("penginapusername")
                    val id_pemilik = o.getInt("id_pemilik")
                    var username_pemilik = o.getString("pemilikusername")
                    var created_at = o.getString("created_at")
                    var status = o.getString("status")
                    var sender = o.getString("sender")
                    val p = Chat(id,
                        pesan,
                        id_penginap,
                        username_penginap,
                        id_pemilik,
                        username_pemilik,
                        created_at,
                        status,
                        sender
                    )
                    chats.add(p)
                    rvChatPenginap.notifyDataSetChanged()
                }
                findlatest()
            },
            Response.ErrorListener {
                Toast.makeText(view.context, "WS_ERROR1", Toast.LENGTH_SHORT).show()
            }
        ) {
            override fun getParams(): MutableMap<String, String>? {
                val params = HashMap<String, String>()
                params["id_pemilik"] = pemilik.id.toString()
                return params
            }
        }
        val queue: RequestQueue = Volley.newRequestQueue(view.context)
        queue.add(strReq)
    }



}