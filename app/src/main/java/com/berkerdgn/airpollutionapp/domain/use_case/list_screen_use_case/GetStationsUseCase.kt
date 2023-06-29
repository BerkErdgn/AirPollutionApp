package com.berkerdgn.airpollutionapp.domain.use_case.list_screen_use_case

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.berkerdgn.airpollutionapp.data.remote.dto.AllStationsModel
import com.berkerdgn.airpollutionapp.domain.repository.StationRepository

import com.berkerdgn.airpollutionapp.util.Resource
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.supervisorScope
import javax.inject.Inject

class GetStationsUseCase @Inject constructor(
    private val repository: StationRepository
) {

    suspend fun executeGetStations() : LiveData<Resource<AllStationsModel>> {

        val stationsLiveData = MutableLiveData<Resource<AllStationsModel>>()
        stationsLiveData.value = Resource.Loading()
        try {
            val stationList = repository.getStations()
            if (stationList.isNotEmpty()) {
                stationsLiveData.value = Resource.Success(stationList)
            } else {
                stationsLiveData.value = Resource.Error("Error in usecase")
            }
        } catch (e: Exception) {
            stationsLiveData.value = Resource.Error("Error in usecase2")
        }
        return stationsLiveData
    }
}






/*
fun executeGetStations() : LiveData<Resource<AllStationsModel>> = liveData {

        val stationsLiveData = MutableLiveData<Resource<AllStationsModel>>()

        stationsLiveData.value = Resource.Loading()

        try {
            val stationList = repository.getStations()
            if (stationList.isNotEmpty()) {
                stationsLiveData.value = Resource.Success(stationList)
            } else {
                stationsLiveData.value = Resource.Error("Error in usecase")
            }
        } catch (e: Exception) {
            stationsLiveData.value = Resource.Error("Error in usecase2")
        }


    }
 */