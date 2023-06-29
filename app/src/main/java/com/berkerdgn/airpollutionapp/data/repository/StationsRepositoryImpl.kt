package com.berkerdgn.airpollutionapp.data.repository

import com.berkerdgn.airpollutionapp.data.remote.StationsAPI
import com.berkerdgn.airpollutionapp.data.remote.dto.AllStationsModel
import com.berkerdgn.airpollutionapp.domain.repository.StationRepository
import com.berkerdgn.airpollutionapp.util.Resource
import javax.inject.Inject

class StationsRepositoryImpl @Inject constructor(
    private val stationsApi: StationsAPI
) : StationRepository {

    override suspend  fun getStations(): AllStationsModel {
        return stationsApi.getAllStations()
    }
}



