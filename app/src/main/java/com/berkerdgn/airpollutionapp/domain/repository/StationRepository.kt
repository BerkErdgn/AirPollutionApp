package com.berkerdgn.airpollutionapp.domain.repository

import androidx.lifecycle.LiveData
import com.berkerdgn.airpollutionapp.data.remote.dto.AllStationsModel
import com.berkerdgn.airpollutionapp.data.remote.dto.StationDetailModel
import com.berkerdgn.airpollutionapp.data.remote.dto.saved_dto.SavedStationModel
import com.berkerdgn.airpollutionapp.util.Resource

interface StationRepository {

     suspend  fun getStations (): AllStationsModel

     suspend fun getStationDetails(stationId : String,startDate : String,endDate : String): StationDetailModel

     suspend fun insertSavedStation (savedStation: SavedStationModel)

     suspend fun deleteSavedStation (savedStation: SavedStationModel)

     fun getSavedStations ():  List<SavedStationModel>


}
