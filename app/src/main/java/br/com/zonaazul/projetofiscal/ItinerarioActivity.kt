package br.com.zonaazul.projetofiscal

import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import br.com.zonaazul.projetofiscal.databinding.ActivityItinerarioBinding
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import java.security.AccessController.getContext

class ItinerarioActivity : AppCompatActivity() {

    var bd: FirebaseFirestore? = null

    private lateinit var btnBack: MaterialButton
    private lateinit var btnOpenMap: MaterialButton
    private lateinit var btnIniciarIt: MaterialButton
    private lateinit var etRota: EditText
    private lateinit var tvPeriodo: TextView
    private lateinit var Spinner1: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_itinerario)

        bd = FirebaseFirestore.getInstance()

        tvPeriodo = findViewById(R.id.tvPeriodo)

        etRota = findViewById(R.id.etRota)
        btnIniciarIt = findViewById(R.id.btnIniciarIt)

        btnBack = findViewById(R.id.btnBack)
        btnOpenMap = findViewById(R.id.btnOpenMap)

        btnBack.setOnClickListener{
            backPage()
        }
        btnOpenMap.setOnClickListener{
            openMap()
        }

        btnIniciarIt.setOnClickListener{
            iniciarItinerario(etRota.text.toString())
        }

        val spinner: Spinner = findViewById(R.id.Spinner1)
        ArrayAdapter.createFromResource(
            this,
            R.array.namesDropDown,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        class SpinnerActivity : Activity(), AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                val spinner: Spinner = findViewById(R.id.Spinner1)
                spinner.onItemSelectedListener = this
            }
        }


    }

    private fun backPage(){
        val back = Intent(this, HomePageActivity::class.java)
        startActivity(back)
    }

    private fun openMap(){
        val map = Intent(this, MapsActivity::class.java)
        startActivity(map)
    }


    fun iniciarItinerario(rotaDigitada : String){

        if(etRota.text.isNullOrEmpty()){
            Snackbar.make(btnBack, "Campo está vazio. Informe a rota para prosseguir.", Snackbar.LENGTH_LONG).show()

        }else{
            bd!!.collection("MapsItinerário")
                .whereEqualTo("Nome", rotaDigitada)
                .get()
                .addOnSuccessListener { result ->

                    for (document in result) {

                        val status = document.getLong("Status")!!.toInt()
                        if (status == 1){
                            Snackbar.make(btnBack, "Está rota ja está em processo.", Snackbar.LENGTH_LONG).show()
                        }else{
                            if (document.getString("Nome").equals(rotaDigitada)){
                                Snackbar.make(btnBack, "Itinerário Iniciado", Snackbar.LENGTH_LONG).show()

                                val atualiza = hashMapOf(
                                    "Status" to 1
                                )

                                bd!!.collection("MapsItinerário").document(document.id).set(atualiza, SetOptions.merge())


                                val periodo = document.getString("Periodo")

                                tvPeriodo.setText("$periodo")

                            }else{
                                Snackbar.make(btnBack, "Erro ao iniciar itinerário", Snackbar.LENGTH_LONG).show()
                            }
                        }
                    }
                }.addOnFailureListener { exception ->
                    Log.w(ContentValues.TAG, "Error", exception)
                    Snackbar.make(btnBack, "Erro ao iniciar itinerário", Snackbar.LENGTH_LONG).show()

                }
        }
    }


}
