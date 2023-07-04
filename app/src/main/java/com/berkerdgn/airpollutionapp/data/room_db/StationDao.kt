package com.berkerdgn.airpollutionapp.data.room_db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.berkerdgn.airpollutionapp.data.remote.dto.saved_dto.SavedStationModel

@Dao
interface StationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStation(station:SavedStationModel)

    @Delete
    suspend fun deleteStation(station: SavedStationModel)

    @Query(value = "SELECT * FROM savedStations")
    fun stationLiveData(): List<SavedStationModel>



}