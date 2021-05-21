package com.example.lab_1_v1

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import androidx.room.Query;

//  (DAO - Data Access Object)


@Dao
interface MarkerDao {
    // Получаем все маркеры
    @get:Query("SELECT * FROM MapMarker")
    val getAll: List<MapMarker>

    // Получаем маркер по Lat & lng
    @Query("SELECT * FROM MapMarker WHERE latitude = :lat AND longitude = :lng")
    fun getByLatLng(lat: Double, lng: Double): MapMarker

    @Insert
    fun insert(marker: MapMarker)

    @Update
    fun update(marker: MapMarker)
}