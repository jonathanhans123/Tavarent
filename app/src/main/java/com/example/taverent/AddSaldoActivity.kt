package com.example.taverent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.taverent.databinding.ActivityAddSaldoBinding

class AddSaldoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddSaldoBinding
    private lateinit var penginap: Penginap
    var WS_HOST = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddSaldoBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        WS_HOST = resources.getString(R.string.WS_HOST)

        penginap = intent.getParcelableExtra<Penginap>("penginap") as Penginap

        binding.button17.setOnClickListener {
            if (binding.editHarga.text.toString()!=""){
                if (binding.editHarga.text.toString().toInt()>0){
                    val strReg = object : StringRequest(
                        Method.POST,"$WS_HOST/penginap/tambahsaldo",
                        Response.Listener {
                            Toast.makeText(this,
                                "Saldo berhasil ditambah",
                                Toast.LENGTH_SHORT).show()
                            finish()
                        },
                        Response.ErrorListener {
                            Toast.makeText(this,
                                it.message.toString(),
                                Toast.LENGTH_SHORT).show()
                        }
                    ){
                        override fun getParams(): MutableMap<String, String>? {
                            val params = HashMap<String, String>()
                            params["id"] = penginap.id.toString()
                            params["saldo"] = (penginap.saldo+binding.editHarga.text.toString().toInt()).toString()
                            return params
                        }
                    }
                    val queue: RequestQueue = Volley.newRequestQueue(this)
                    queue.add(strReg)
                }
            }
        }
    }
}