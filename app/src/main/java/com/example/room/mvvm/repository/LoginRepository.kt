package com.example.room.mvvm.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.room.mvvm.model.LoginTableModel
import com.example.room.mvvm.room.StoreDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class LoginRepository {

    companion object {

        var storeDatabas: StoreDatabase? = null

        var loginTableModel: LiveData<LoginTableModel>? = null

        fun initializeDB(context: Context) : StoreDatabase {
            return StoreDatabase.getDataseClient(context)
        }

        fun insertData(context: Context, username: String, password: String) {

            storeDatabas = initializeDB(context)

            CoroutineScope(IO).launch {
                val loginDetails = LoginTableModel(username, password)
                storeDatabas!!.loginDao().InsertData(loginDetails)
            }

        }

        fun getLoginDetails(context: Context, username: String) : LiveData<LoginTableModel>? {

            storeDatabas = initializeDB(context)

            loginTableModel = storeDatabas!!.loginDao().getLoginDetails(username)

            return loginTableModel
        }

    }
}