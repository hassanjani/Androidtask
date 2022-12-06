package com.example.room.mvvm.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.room.mvvm.model.LoginTableModel
import com.example.room.mvvm.model.StoreTableModel

@Dao
interface DAOAccess {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertData(loginTableModel: LoginTableModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertStoreData(storeTableModel: StoreTableModel)

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun InsertStoreData(stortableModel: StoreTableModel)

    @Query("SELECT * FROM Login WHERE Username =:username")
    fun getLoginDetails(username: String?) : LiveData<LoginTableModel>

    @Query("SELECT * FROM Store")
    suspend fun getStoreList() : List<StoreTableModel>?


    @Query("DELETE FROM Store WHERE name =:name")
    fun DeleteItem(name:String)
}