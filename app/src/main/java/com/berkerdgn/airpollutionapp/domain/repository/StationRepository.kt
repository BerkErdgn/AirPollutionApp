package com.berkerdgn.airpollutionapp.domain.repository

import com.berkerdgn.airpollutionapp.data.remote.dto.AllStationsModel
import com.berkerdgn.airpollutionapp.data.remote.dto.StationDetailModel
import com.berkerdgn.airpollutionapp.util.Resource

interface StationRepository {

     suspend  fun getStations (): AllStationsModel

     suspend fun getStationDetails(stationId : String,startDate : String,endDate : String): StationDetailModel
}