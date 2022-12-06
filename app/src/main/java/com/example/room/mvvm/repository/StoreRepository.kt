package com.example.room.mvvm.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.room.mvvm.model.LoginTableModel
import com.example.room.mvvm.model.StoreTableModel
import com.example.room.mvvm.room.StoreDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class StoreRepository {

    companion object {

        var storeDatabase: StoreDatabase? = null

        var StoreDataList: List<StoreTableModel>? = null

        fun initializeDB(context: Context) : StoreDatabase {
            return StoreDatabase.getDataseClient(context)
        }

        fun insertData(context: Context, name: String, address: String, phone: String, image: String, lat: String, lng: String) {

            storeDatabase = initializeDB(context)

            CoroutineScope(IO).launch {
                val storedetails = StoreTableModel( name, address,phone,image,lat,lng)
                storeDatabase!!.loginDao().InsertStoreData(storedetails)
            }

        }

       suspend fun getStoreData(context: Context) : List<StoreTableModel>? {

            storeDatabase = initializeDB(context)

            StoreDataList = storeDatabase!!.loginDao().getStoreList()

            return StoreDataList
        }



         fun deleteItem(context: Context,name: String)  {
            storeDatabase = initializeDB(context)
            storeDatabase!!.loginDao().DeleteItem(name)

        }


    }
}