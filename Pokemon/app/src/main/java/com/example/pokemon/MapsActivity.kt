package com.example.pokemon

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.pokemon.databinding.ActivityMapsBinding
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import java.lang.Exception
import kotlin.concurrent.thread

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    private var LOCATIONREQUESTCODE = 1339
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        checkPermission()
        loadPokemon()
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        //edit the marker config
        mMap.addMarker(MarkerOptions()
            .position(sydney)
            .title("Me")
            .snippet("You are here now")
            .icon(BitmapDescriptorFactory.fromResource(R.drawable.pokeball_icon)))
        //adjust this to zoom in on map
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,7f))
    }

    //we need to check for permissions if a dangerous permission is needed in android such as fine
    //access location
    private fun getUserLocation(){
        Toast.makeText(applicationContext, "Location is on", Toast.LENGTH_SHORT).show()

          var listener = MyLocationListen()
          var locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
          locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 3f, listener)
          val t = MyThread()
          t.start()
    }

    private fun checkPermission(){
        if(Build.VERSION.SDK_INT >= 23){
            if(ActivityCompat
                    .checkSelfPermission(applicationContext,
                    android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                //first param : permission second param : request code
                requestPermissions(
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                    LOCATIONREQUESTCODE)
                return;
            }
        }

        getUserLocation()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            LOCATIONREQUESTCODE -> {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    getUserLocation()
                }else{
                    Toast.makeText(this,
                        "We cannot access your location",
                        Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    var location:Location?=null
    inner class MyLocationListen : LocationListener {
        constructor(){
            location = Location("start")
            location!!.latitude = 0.0
            location!!.longitude = 0.0
        }
        override fun onLocationChanged(loc: Location) {
            location = loc
        }


    }

    var oldLocation:Location?=null
    inner class MyThread : Thread {
        constructor():super(){
            oldLocation = Location("start")
            oldLocation!!.longitude = 0.0
            oldLocation!!.latitude = 0.0

        }
        override fun run() {
            while (true){
                try{
                    if(oldLocation!!.distanceTo(location) == 0f){
                        continue
                    } else{
                        runOnUiThread {
                            mMap.clear()
                            //show my location
                            val sydney = LatLng(location!!.latitude, location!!.longitude)
                            //edit the marker config
                            mMap.addMarker(MarkerOptions()
                                .position(sydney)
                                .title("Me")
                                .snippet("You are here now")
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.pokeball_icon)))
                            //adjust this to zoom in on map
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,7f))

                            //show pokemon location
                            for(i in 0 until Pokedex.size){

                                val pokemonLocation = LatLng(Pokedex[i].lat, Pokedex[i].lon)
                                //edit the marker config
                                mMap.addMarker(MarkerOptions()
                                    .position(pokemonLocation)
                                    .title(Pokedex[0].name)
                                    .snippet("You are here now")
                                    .icon(BitmapDescriptorFactory.fromResource(Pokedex[i].image)))
                                //adjust this to zoom in on map
                            }

                            oldLocation = location

                        }
                    }

                    Thread.sleep(1000)

                }catch (ex : Exception){ }
            }
        }
    }

    var Pokedex = ArrayList<Pokemon>()

    private fun loadPokemon(){
        Pokedex.add(Pokemon("Charamander",
            R.drawable.charamander_icon,
            "Charmander is a small, bipedal, dinosaur like Pokémon. Most of its body is "+
                    "colored orange, while its underbelly is a light yellow color. Charmander," +
                    " along with all of its evolved forms, has a flame that is constantly burning " +
                    "on the end of its tail.", 50.0, 42.12, -70.96)
            )
        Pokedex.add(Pokemon("Charazard",
            R.drawable.charazard,
            "CCharizard is a draconic, bipedal Pokémon. It is primarily orange with a " +
                    "cream underside from the chest to the tip of its tail. It has a long neck, " +
                    "small blue eyes, slightly raised nostrils, and two horn-like structures " +
                    "protruding from the back of its rectangular head.",
            50.0, 42.24, -70.80)
            )
        Pokedex.add(Pokemon("Charamander",
            R.drawable.ninetails_icon,
            "Nine-tails is a legendary pokemon...lol", 50.0, 42.19, -70.72)
            )
        Pokedex.add(Pokemon("Charamander",
            R.drawable.vileplume_icon,
            "Vileplume is a cool pokemon", 50.0, 42.30, -70.90)
            )
        Pokedex.add(Pokemon("Charamander",
            R.drawable.ivysaur_icon,
            "Ivysaur lives in wompatuck state park", 50.0, 42.20, -70.84)
            )


    }




}