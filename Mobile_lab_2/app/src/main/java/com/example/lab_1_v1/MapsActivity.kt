package com.example.lab_1_v1

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions



class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private var dataBase: AppDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)


        dataBase = App.instance?.database
    }

    override fun onMapReady(googleMap: GoogleMap) {

        mMap = googleMap


        val mapMarkers: List<MapMarker>? = dataBase?.markerDao()?.getAll
        if (mapMarkers != null) {
            mapMarkers.forEach{
                val location = LatLng(it.lat, it.lng)
                mMap.addMarker(MarkerOptions().position(location))
            }
        }


        mMap.setOnMapClickListener { latlng ->

            val location = LatLng(latlng.latitude, latlng.longitude)
            mMap.addMarker(MarkerOptions().position(location))

            dataBase?.markerDao()?.insert(MapMarker(latlng.latitude, latlng.longitude))
        }


        mMap.setOnMarkerClickListener { p0 ->

            val location = p0.position

            val clickedMarker = dataBase?.markerDao()?.getByLatLng(location.latitude, location.longitude)

            val photoPath = clickedMarker?.photoPath
            val intent = Intent(this@MapsActivity, MarkerActivity::class.java)

            intent.putExtra("photoPath", photoPath.toString())
            intent.putExtra("coords", location.toString())
            intent.putExtra("id", clickedMarker?.id.toString())

            startActivityForResult(intent, 123)
            true
        }
    }

    protected override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 123 && resultCode == Activity.RESULT_OK) {
            val str = data
                ?.getStringExtra("coords")
                ?.substringAfter('(')
                ?.substringBefore(')')

            val lat = str?.substringBefore(',')?.toDouble()
            val lng = str?.substringAfter(',')?.toDouble()

            lateinit var clickedMarker: MapMarker
            if (lat != null && lng != null) {
                clickedMarker = dataBase?.markerDao()?.getByLatLng(lat, lng)!!
            }

            val photoPath = data?.getStringExtra("photoPath")
            clickedMarker.photoPath = photoPath

            dataBase?.markerDao()?.update(clickedMarker)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}
