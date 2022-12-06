package com.example.room.mvvm.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Store")
data class StoreTableModel (

    @ColumnInfo(name = "name")
    var Name: String,

    @ColumnInfo(name = "address")
    var Address: String,

    @ColumnInfo(name = "phone")
    var Phone: String,

    @ColumnInfo(name = "image")
    var Image: String,

    @ColumnInfo(name = "lat")
    var Lat: String,

    @ColumnInfo(name = "lng")
    var Lng: String

) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var Id: Int? = null

}