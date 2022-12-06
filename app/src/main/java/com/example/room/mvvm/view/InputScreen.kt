package com.example.room.mvvm.view
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.room.mvvm.R
import com.example.room.mvvm.viewmodel.StoreViewModel
import kotlinx.android.synthetic.main.activity_input_screen.*

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices


class InputScreen : AppCompatActivity() {

    lateinit var storeViewModel: StoreViewModel
    lateinit var context: Context

    lateinit var strName: String
    lateinit var strAddress: String
    lateinit var strPhone: String
    lateinit var strImage: String
     var strLat: String="0"
     var strLng: String="0"



    private lateinit var fusedLocationClient: FusedLocationProviderClient

    lateinit var imageView: ImageView
    private val pickImage = 100
    private var imageUri: Uri? = null


    private lateinit var locationManager: LocationManager
    private val locationPermissionCode = 2



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_screen)
        context = this@InputScreen

        storeViewModel = ViewModelProvider(this).get(StoreViewModel::class.java)

        btnAddStoreData.setOnClickListener {

            strName = txtName.text.toString().trim()
            strAddress = txtAddress.text.toString().trim()
            strPhone = txtMobile.text.toString().trim()

            if (strName.isEmpty()) {
                txtName.error = "Please enter the Name"
            }
            else if (strAddress.isEmpty()) {
                txtAddress.error = "Please enter the Address"
            }  else if (strPhone.isEmpty()) {
                txtMobile.error = "Please enter the Phone"
            }
            else {
                storeViewModel.insertData(context, strName, strAddress, strPhone, imageUri.toString(), strLat, strLng)
                lblResponse.text = "Inserted Successfully"
            }
        }



        imageView = findViewById(R.id.imageView)
        buttonLoadPicture.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, pickImage)
        }


// in onCreate() initialize FusedLocationProviderClient
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context!!)

        getLastKnownLocation();

//        btnAddStoreData.setOnClickListener {
//
//            strName = txtName.text.toString().trim()
//            strAddress = txtAddress.text.toString().trim()
//            strPhone = txtMobile.text.toString().trim()
//
////            storeViewModel.getLoginDetails(context, strUsername)!!.observe(this, Observer {
////
////                if (it == null) {
////                    lblReadResponse.text = "Data Not Found"
////                    lblUseraname.text = "- - -"
////                    lblPassword.text = "- - -"
////                }
////                else {
////                    lblUseraname.text = it.Username
////                    lblPassword.text = it.Password
////
////                    lblReadResponse.text = "Data Found Successfully"
////                }
////            })
//        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == pickImage) {
            imageUri = data?.data
            imageView.setImageURI(imageUri)
            print(imageUri)
            Log.d("msgs", imageUri.toString())

        }
    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == locationPermissionCode) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
                getLastKnownLocation();
            }
            else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    @SuppressLint("MissingPermission")
    fun getLastKnownLocation() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), locationPermissionCode)

            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location->
                if (location != null) {
                        strLat=location.latitude.toString();
                        strLng=location.longitude.toString();
                    Log.d("msgs",location.latitude.toString())
                    Log.d("msgs",location.longitude.toString())
                    // use your location object
                    // get latitude , longitude and other info from this
                }

            }
    }

}