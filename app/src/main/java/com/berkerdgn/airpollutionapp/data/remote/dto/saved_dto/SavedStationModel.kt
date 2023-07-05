package com.berkerdgn.airpollutionapp.data.remote.dto.saved_dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "savedStations")
data class SavedStationModel(
    @ColumnInfo(name = "stationName")
    val stationName : String,
    @ColumnInfo(name = "startDate")
    val startDate: String,
    @ColumnInfo(name = "endDate")
    val endDate: String,
    @ColumnInfo(name = "stationID")
    val stationID: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
)
