package com.berkerdgn.airpollutionapp.data.repository

import com.berkerdgn.airpollutionapp.data.remote.StationsAPI
import com.berkerdgn.airpollutionapp.data.remote.dto.AllStationsModel
import com.berkerdgn.airpollutionapp.data.remote.dto.StationDetailModel
import com.berkerdgn.airpollutionapp.data.remote.dto.saved_dto.SavedStationModel
import com.berkerdgn.airpollutionapp.data.room_db.StationDao
import com.berkerdgn.airpollutionapp.domain.repository.StationRepository
import javax.inject.Inject

class StationsRepositoryImpl @Inject constructor(
    private val stationsApi: StationsAPI,
    private val stationDao: StationDao
) : StationRepository {

    override suspend  fun getStations(): AllStationsModel {
        return stationsApi.getAllStations()
    }

    override suspend fun getStationDetails(
        stationId: String,
        startDate: String,
        endDate: String
    ): StationDetailModel {
        return  stationsApi.getStationDetail(stationId, startDate, endDate)
    }

    override suspend fun insertSavedStation(savedStation: SavedStationModel) {
        stationDao.insertStation(savedStation)
    }

    override suspend fun deleteSavedStation(savedStation: SavedStationModel) {
        stationDao.deleteStation(savedStation)
    }

    override fun getSavedStations(): List<SavedStationModel> {
        return stationDao.stationLiveData()
    }


}




