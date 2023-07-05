package com.berkerdgn.airpollutionapp.data.room_db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.berkerdgn.airpollutionapp.data.remote.dto.saved_dto.SavedStationModel


@Database(entities = [SavedStationModel::class], version = 1)
abstract class StationDataBase : RoomDatabase() {

    abstract fun stationDao(): StationDao

}