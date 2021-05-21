package com.example.lab_1_v1

import androidx.room.Entity
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey



@Entity
class MapMarker(
    @field:ColumnInfo(name = "latitude")
    var lat: Double,

    @field:ColumnInfo(name = "longitude")
    var lng: Double) {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    @ColumnInfo(name = "photoPath")
    var photoPath: String? = null

}