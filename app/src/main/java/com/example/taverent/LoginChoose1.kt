package com.example.taverent

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.taverent.databinding.ActivityLoginBinding
import com.example.taverent.databinding.FragmentLoginChoose1Binding
import org.json.JSONArray


class LoginChoose1 : Fragment() {

    private lateinit var binding: FragmentLoginChoose1Binding
    var WS_HOST = ""

    var pemiliks: ArrayList<Pemilik> = ArrayList()
    var penginaps: ArrayList<Penginap> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginChoose1Binding.inflate(layoutInflater)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        WS_HOST = resources.getString(R.string.WS_HOST)

        refreshPemilik(view)
        refreshPenginap(view)


        binding.imageButton.setOnClickListener {
            val fragment  = LoginChoose2()
            val bundle = Bundle()
            fragment.arguments = bundle
            parentFragmentManager.beginTransaction()
                .replace(R.id.frag1,fragment)
                .commit()
        }

        val tipe = arguments?.getString("tipe")
        binding.textView.setText("Selamat Kembali $tipe")
        if (tipe!=null) {
            binding.btnLogin.setOnClickListener {
                val email = binding.etEmail.text.toString().trim()
                val password = binding.etPassword.text.toString().trim()
                if (email != "" && password != "") {
                    if (email=="admin"&&password=="admin") {
                        val intent = Intent(view.context,AdminActivity::class.java)
                        activity?.runOnUiThread { startActivity(intent) }
                    }else{
                        var exist = false
                        if (tipe == "pemilik") {
                            for (i in 0 until pemiliks.size) {
                                if (pemiliks[i].email == email) {
                                    if (pemiliks[i].password == password) {
                                        Toast.makeText(view.context, "Success", Toast.LENGTH_SHORT).show()
                                        val intent = Intent(view.context,SewaMain::class.java)
                                        intent.putExtra("pemilik",pemiliks[i])
                                        intent.putExtra("id_pemilik",pemiliks[i].id.toString())
                                        intent.putExtra("nama_pemilik",pemiliks[i].nama_lengkap.toString())
                                        intent.putExtra("username",pemiliks[i].username.toString())
                                        activity?.runOnUiThread { startActivity(intent) }
                                    } else {
                                        Toast.makeText(view.context,
                                            "Password Incorrect",
                                            Toast.LENGTH_SHORT).show()
                                    }
                                    exist = true
                                    break
                                }
                            }
                            if (!exist) {
                                Toast.makeText(view.context,
                                    "Email not registered",
                                    Toast.LENGTH_SHORT).show()
                            }
                        } else {
                            for (i in 0 until penginaps.size) {
                                if (penginaps[i].email == email) {
                                    if (penginaps[i].password == password) {
                                        Toast.makeText(view.context, "Success", Toast.LENGTH_SHORT)
                                            .show()
                                        val intent = Intent(view.context,PenginapActivity::class.java)
                                        intent.putExtra("penginap",penginaps[i])
                                        intent.putExtra("id_penginap",penginaps[i].id.toString())
                                        Toast.makeText(view.context, penginaps[i].id.toString(), Toast.LENGTH_SHORT).show()
                                        activity?.runOnUiThread { startActivity(intent) }
                                    } else {
                                        Toast.makeText(view.context,
                                            "Paasword Incorrect",
                                            Toast.LENGTH_SHORT).show()
                                    }
                                    exist = true
                                    break
                                }
                            }
                            if (!exist) {
                                Toast.makeText(view.context,
                                    "Email not registered",
                                    Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                } else {
                    Toast.makeText(view.context,
                        "Fill all fields",
                        Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    fun refreshPemilik(view:View){
        val strReq = object : StringRequest(
            Method.GET,"$WS_HOST/pemilik/list",
            Response.Listener {
                val obj: JSONArray = JSONArray(it)
                pemiliks.clear()
                for (i in 0 until obj.length()){
                    val o = obj.getJSONObject(i)
                    val id = o.getInt("id")
                    val username = o.getString("username")
                    val password = o.getString("password")
                    val nama_lengkap = o.getString("nama_lengkap")
                    val email = o.getString("email")
                    val no_telp = o.getString("no_telp")
                    val saldo = o.getInt("saldo")
                    var deleted_at = ""
                    if (o.has("deleted_at")) {
                        deleted_at = o.getString("deleted_at")
                    }
                    val p = Pemilik(id,username,password,nama_lengkap,email,no_telp,deleted_at,saldo)
                    pemiliks.add(p)
                }
            },
            Response.ErrorListener {
                Toast.makeText(view.context, "WS_ERROR2", Toast.LENGTH_SHORT).show()
            }
        ){}
        val queue: RequestQueue = Volley.newRequestQueue(view.context)
        queue.add(strReq)
    }
    fun refreshPenginap(view:View){
        val strReq = object : StringRequest(
            Method.GET,"$WS_HOST/penginap/list",
            Response.Listener {
                val obj: JSONArray = JSONArray(it)
                penginaps.clear()
                for (i in 0 until obj.length()){
                    val o = obj.getJSONObject(i)
                    val id = o.getInt("id")
                    val username = o.getString("username")
                    val password = o.getString("password")
                    val nama_lengkap = o.getString("nama_lengkap")
                    val email = o.getString("email")
                    val no_telp = o.getString("no_telp")
                    val saldo = o.getInt("saldo")
                    var deleted_at = ""
                    if (o.has("deleted_at")) {
                        deleted_at = o.getString("deleted_at")
                    }
                    val p = Penginap(id,username,password,nama_lengkap,email,no_telp,deleted_at,saldo)
                    penginaps.add(p)
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