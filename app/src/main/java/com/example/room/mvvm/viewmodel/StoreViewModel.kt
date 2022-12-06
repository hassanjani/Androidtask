package com.example.room.mvvm.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.room.mvvm.model.StoreTableModel
import com.example.room.mvvm.repository.StoreRepository

class StoreViewModel : ViewModel() {




    var liveDataStore: List<StoreTableModel>? = null

    fun insertData(context: Context, name: String, address: String, phone: String, image: String, lat: String, lng: String) {
       StoreRepository.insertData(context, name, address,phone,image,lat,lng)
    }

    suspend fun getAllData(context: Context) : List<StoreTableModel>? {
        liveDataStore = StoreRepository.getStoreData(context)
        print(liveDataStore.toString())
        return liveDataStore
    }


     fun deletedata(context: Context, name: String)  {
         StoreRepository.deleteItem(context,name)
        print(liveDataStore.toString())
    }

}