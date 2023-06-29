package com.berkerdgn.airpollutionapp.data.remote

import com.berkerdgn.airpollutionapp.data.remote.dto.AllStationsModel
import com.berkerdgn.airpollutionapp.util.Resource

import retrofit2.http.GET

interface StationsAPI {


    @GET("GetAQIStations")
    suspend  fun getAllStations(): AllStationsModel
}