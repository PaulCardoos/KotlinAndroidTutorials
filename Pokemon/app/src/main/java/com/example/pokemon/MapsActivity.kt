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
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,14f))
    }

    //we need to check for permissions if a dangerous permission is needed in android such as fine
    //access location
    private fun getUserLocation(){
        Toast.makeText(applicationContext, "Location is on", Toast.LENGTH_SHORT).show()

          var listener = MyLocationListen()
          var locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
          locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3, 3f, listener)
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


    inner class MyThread : Thread {
        constructor():super(){ }
        override fun run() {
            while (true){
                try{
                    runOnUiThread {

                        val sydney = LatLng(location!!.latitude, location!!.longitude)
                        //edit the marker config
                        mMap.addMarker(MarkerOptions()
                            .position(sydney)
                            .title("Me")
                            .snippet("You are here now")
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.pokeball_icon)))
                        //adjust this to zoom in on map
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,14f))
                    }

                    Thread.sleep(1000)

                }catch (ex : Exception){ }
            }
        }

    }




}