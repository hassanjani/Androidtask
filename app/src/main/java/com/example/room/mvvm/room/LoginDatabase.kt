package com.example.room.mvvm.room

import android.content.Context
import androidx.room.*
import com.example.room.mvvm.model.LoginTableModel
import com.example.room.mvvm.model.StoreTableModel

@Database(entities = arrayOf(LoginTableModel::class,StoreTableModel::class), version = 1, exportSchema = false)
abstract class StoreDatabase : RoomDatabase() {

    abstract fun loginDao() : DAOAccess

    companion object {

        @Volatile
        private var INSTANCE: StoreDatabase? = null

        fun getDataseClient(context: Context) : StoreDatabase {

            if (INSTANCE != null) return INSTANCE!!

            synchronized(this) {

                INSTANCE = Room
                    .databaseBuilder(context, StoreDatabase::class.java, "STORE_DATABASE")
                    .fallbackToDestructiveMigration()
                    .build()

                return INSTANCE!!

            }
        }

    }

}