package com.berkerdgn.airpollutionapp.domain.use_case.saved_screen_use_case

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.berkerdgn.airpollutionapp.data.remote.dto.saved_dto.SavedStationModel
import com.berkerdgn.airpollutionapp.domain.repository.StationRepository
import com.berkerdgn.airpollutionapp.util.Resource
import javax.inject.Inject

class SavedStationUseCase @Inject constructor(
    private val  repository: StationRepository
) {

    suspend fun executeGetSavedStations () : LiveData<Resource<List<SavedStationModel>>> {
        val savedStationLiveData = MutableLiveData<Resource<List<SavedStationModel>>>()
        savedStationLiveData.value = Resource.Loading()

        try {
            val savedStationList = repository.getSavedStations()
            if (savedStationList.isNotEmpty()){
                savedStationLiveData.value = Resource.Success(savedStationList)
            } else{
                savedStationLiveData.value = Resource.Error("Error in usecase")
            }
        }catch (e:Exception){
            savedStationLiveData.value = Resource.Error("Error in usecase2")
        }
        return savedStationLiveData
    }



}