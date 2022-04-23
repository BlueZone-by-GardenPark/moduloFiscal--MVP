package br.com.zonaazul.projetofiscal

import android.app.Service
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.button.MaterialButton

class HomePageActivity : AppCompatActivity() {

    private lateinit var btnOpenStats: MaterialButton
    private lateinit var btnItinerario: MaterialButton

    var connectivity: ConnectivityManager? = null
    var info: NetworkInfo? = null
    var context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        btnOpenStats = findViewById(R.id.btnOpenStats)

        connectivity = context.getSystemService(Service.CONNECTIVITY_SERVICE) 
                as ConnectivityManager

        btnOpenStats.setOnClickListener{
            if (connectivity != null){
                info = connectivity!!.activeNetworkInfo

                if (info != null){
                    if (info!!.state == NetworkInfo.State.CONNECTED){
                        Toast.makeText(this, "Conectado á Internet", Toast.LENGTH_SHORT).show()
                        openStats()
                    }
                }else{
                    Toast.makeText(this, "Falha na conexão com a Internet", Toast.LENGTH_SHORT).show()
                }
            }
            
        }

        btnItinerario = findViewById(R.id.btnItinerario)

        btnItinerario.setOnClickListener{
            if (connectivity != null){
                info = connectivity!!.activeNetworkInfo

                if (info != null){
                    if (info!!.state == NetworkInfo.State.CONNECTED){
                        Toast.makeText(this, "Conectado á Internet", Toast.LENGTH_SHORT).show()
                        openItinerario()
                    }
                }else{
                    Toast.makeText(this, "Falha na conexão com a Internet", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    private fun openStats(){
        val pass = Intent(this, StatusVeiculoActivity::class.java)
        startActivity(pass)
    }

    private fun openItinerario(){
        val pass = Intent(this, ItinerarioActivity::class.java)
        startActivity(pass)
    }

    /*
    private fun checkNet(){
        connectivity = context.getSystemService(Service.CONNECTIVITY_SERVICE)
        as ConnectivityManager

        if (connectivity != null){
            info = connectivity!!.activeNetworkInfo

            if (info != null){
                if (info!!.state == NetworkInfo.State.CONNECTED){
                    Toast.makeText(this, "Conectado á Internet", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this, "Falha na conexão com a Internet", Toast.LENGTH_SHORT).show()
            }
        }
    }
    */

}