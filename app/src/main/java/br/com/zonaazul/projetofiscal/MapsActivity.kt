package br.com.zonaazul.projetofiscal

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.zonaazul.projetofiscal.databinding.ActivityMapsBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.firebase.firestore.FirebaseFirestore


class MapsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMapsBinding
    var bd: FirebaseFirestore? = null


    private val places = arrayListOf(
        Place("Rota 1 - Começo", LatLng(-22.885751, -47.069082), "Rua Frei Antônio de Pádua, 1286-1436 - Jardim Guanabara\nCampinas - SP, 13073-330"),
        Place("Rota 1 - Final", LatLng(-22.889942, -47.067472), "R. Dr. Albano de Almeida Lima, 164 - Jardim Chapadão\nCampinas - SP, 13073-131"),
        Place("Rota 2 - Começo", LatLng(-22.887115, -47.079188), "Praça Tiro de Guerra, 2655 - Jardim Chapadão\nCampinas - SP, 13070-167"),
        Place("Rota 2 - Final", LatLng(-22.893540, -47.079167), "R. Irmã Maria Inês, 52 - Jardim Chapadão\nCampinas - SP, 13070-030"),
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

     binding = ActivityMapsBinding.inflate(layoutInflater)
     setContentView(binding.root)

        bd = FirebaseFirestore.getInstance()

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync{ googleMap ->
            addMarkers(googleMap)


            googleMap.setOnMapLoadedCallback {
                val bounds = LatLngBounds.builder()

                places.forEach{
                    bounds.include(it.latLng)
                }
                googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds.build(), 100))
            }
        }

    }

    private fun addMarkers(googleMap: GoogleMap){
        places.forEach { place ->
            googleMap.addMarker(
                MarkerOptions()
                    .title(place.name)
                    .snippet(place.adress)
                    .position(place.latLng)
            )
        }
    }

    data class Place(
        val name: String,
        val latLng: LatLng,
        val adress: String
    )

}