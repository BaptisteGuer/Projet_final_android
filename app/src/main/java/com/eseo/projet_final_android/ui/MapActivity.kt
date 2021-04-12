package com.eseo.projet_final_android.ui

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible
import com.afollestad.materialdialogs.MaterialDialog
import com.eseo.projet_final_android.data.LocalPreferences
import com.eseo.projet_final_android.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.android.gms.tasks.CancellationTokenSource
import java.util.*


class MapActivity : AppCompatActivity(), OnMapReadyCallback {

    private val  PERMISSION_REQUEST_LOCATION: Int = 9999
    private lateinit var location:Location
    private lateinit var maMap:GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient


    companion object{
        fun getStartIntent(context: Context): Intent {
            return Intent(context, MapActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        supportActionBar?.apply {
            setTitle(R.string.localisation_title)
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        maMap = googleMap
        maMap.mapType = GoogleMap.MAP_TYPE_HYBRID
        findViewById<TextView>(R.id.locationText).text = getString(R.string.localisation_failure)
        requestPermission()
    }

    private fun requestPermission() {
        if (!hasPermission()) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                PERMISSION_REQUEST_LOCATION
            )
        } else {
            getLocation()
        }
    }
    private fun hasPermission(): Boolean {
        return ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            PERMISSION_REQUEST_LOCATION -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    // Permission obtenue, Nous continuons la suite de la logique.
                    getLocation()
                } else {
                    MaterialDialog(this).show {
                        title(R.string.localisation_title)
                        message(R.string.my_localisation_message)
                        positiveButton(R.string.oui) {
                            requestPermission()
                        }
                        negativeButton(R.string.non)
                    }
                }
                return
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLocation() {
        if (hasPermission()) {
            fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
            fusedLocationClient.getCurrentLocation(
                LocationRequest.PRIORITY_HIGH_ACCURACY,
                CancellationTokenSource().token
            )
                .addOnSuccessListener { location = it
                    geoCode(location)
                    findViewById<Button>(R.id.recentrer).isVisible = true;
                    findViewById<Button>(R.id.recentrer).setOnClickListener {
                        centrer(location)
                    }
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Localisation impossible", Toast.LENGTH_SHORT).show()
                }
        }
    }


    private fun geoCode(location: Location?){
        val eseolocation = Location("")
        eseolocation.latitude = 47.49315784402205
        eseolocation.longitude = -0.5513548306294191
        val distance = location?.distanceTo(eseolocation)?.div(1000)
        if (location != null){
            val geocoder = Geocoder(this, Locale.getDefault())
            val results = geocoder.getFromLocation(location.latitude, location.longitude, 1)
            val polylineOptions = PolylineOptions()
            polylineOptions.add(
                LatLng(location.latitude, location.longitude), LatLng(
                    eseolocation.latitude, eseolocation.longitude
                )
            );
            polylineOptions.color(Color.BLUE);
            polylineOptions.width(12f);
            polylineOptions.startCap(RoundCap())
            polylineOptions.endCap(RoundCap())
            polylineOptions.jointType(JointType.ROUND)

            if (results.isNotEmpty()) {
                maMap.clear()
                maMap.addMarker(
                    MarkerOptions()
                        .position(LatLng(location.latitude, location.longitude))
                        .title("Ma localisation")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                )
                maMap.addMarker(
                    MarkerOptions()
                        .position(LatLng( 47.49315784402205,
                            -0.5513548306294191))
                        .title("ESEO")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                )
                maMap.addPolyline(polylineOptions);
            }
            centrer(location)
            LocalPreferences.getInstance(this).saveStringValue(results[0].getAddressLine(0))
            findViewById<TextView>(R.id.locationText).text = getString(R.string.localisation_text) + results[0].getAddressLine(
                0
            )
            findViewById<TextView>(R.id.distance).text = getString(R.string.distance_text) + distance + getString(R.string.kilometres)
        }
    }

    private fun centrer(location: Location?){
        val zoomLevel = 0;
        if (location != null) {
            maMap.moveCamera(
                CameraUpdateFactory.newLatLngZoom(
                    LatLng(
                        location.latitude,
                        location.longitude
                    ), zoomLevel.toFloat()
                )
            )
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

}