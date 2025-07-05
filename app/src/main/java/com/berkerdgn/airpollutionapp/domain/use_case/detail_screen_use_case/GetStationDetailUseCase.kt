package com.berkerdgn.airpollutionapp.domain.use_case.detail_screen_use_case

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.berkerdgn.airpollutionapp.data.remote.dto.StationDetailModel
import com.berkerdgn.airpollutionapp.domain.repository.StationRepository
import com.berkerdgn.airpollutionapp.util.Resource
import javax.inject.Inject

class GetStationDetailUseCase @Inject constructor(
    private val repository: StationRepository
) {

    suspend fun executeGetStations(
        stationId : String,
        startDate : String,
        endDate : String
    ): LiveData<Resource<StationDetailModel>>{

        val stationDetailLiveData = MutableLiveData<Resource<StationDetailModel>>()
        stationDetailLiveData.value = Resource.Loading()

        val denme = "deneme"

        try {
            val stationDetail = repository.getStationDetails(stationId, startDate, endDate)
            if (stationDetail.isNotEmpty()){
                stationDetailLiveData.value = Resource.Success(stationDetail)
            }else{
                stationDetailLiveData.value = Resource.Error("Error in usecase")
            }
        }catch (e:Exception){
            stationDetailLiveData.value = Resource.Error(e.localizedMessage)
        }
    return stationDetailLiveData
    }



}