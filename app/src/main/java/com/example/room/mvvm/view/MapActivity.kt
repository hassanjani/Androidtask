package com.example.room.mvvm.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.room.mvvm.R
import com.example.room.mvvm.model.StoreTableModel
import com.example.room.mvvm.viewmodel.StoreViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.launch


class MapActivity : AppCompatActivity(), OnMapReadyCallback {

    lateinit var storeViewModel: StoreViewModel
    lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {

        context = this@MapActivity

        storeViewModel = ViewModelProvider(this).get(StoreViewModel::class.java)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
        mapFragment?.getMapAsync(this)



    }

    override fun onMapReady(googleMap: GoogleMap) {
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(LatLng(33.700171618584555, 73.04050920148619)))

        lifecycleScope.launch{

            var mylist:List<StoreTableModel> =  storeViewModel.getAllData(context)!!
            Log.d("mssg",mylist.size.toString())


            mylist.forEach { element->

                val pos = LatLng( element.Lat.toDouble(), element.Lng.toDouble())
                googleMap.addMarker(
                    MarkerOptions()
                        .position(pos)
                        .title(element.Name)
                )

            }


        }



    }
}