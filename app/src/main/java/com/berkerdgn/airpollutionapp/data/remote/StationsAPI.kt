package com.berkerdgn.airpollutionapp.data.remote

import com.berkerdgn.airpollutionapp.data.remote.dto.AllStationsModel
import com.berkerdgn.airpollutionapp.data.remote.dto.StationDetailModel
import retrofit2.http.GET
import retrofit2.http.Query

interface StationsAPI {


    @GET("GetAQIStations")
    suspend  fun getAllStations(): AllStationsModel

    //https://api.ibb.gov.tr/havakalitesi/OpenDataPortalHandler/GetAQIByStationId?StationId=377e1216-bcc7-42c0-aad8-4d5b3d602b78&StartDate=11.06.2020%2000:00:00&EndDate=12.06.2020%2000:00:00
    @GET("GetAQIByStationId")
    suspend fun getStationDetail(
        @Query("StationId") stationId : String,
        @Query("StartDate") startDate : String,
        @Query("EndDate") endDate : String,
    ): StationDetailModel
}

