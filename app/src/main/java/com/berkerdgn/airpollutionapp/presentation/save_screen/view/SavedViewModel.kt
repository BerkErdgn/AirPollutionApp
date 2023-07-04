package com.berkerdgn.airpollutionapp.presentation.save_screen.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.berkerdgn.airpollutionapp.data.remote.dto.saved_dto.SavedStationModel
import com.berkerdgn.airpollutionapp.domain.repository.StationRepository
import com.berkerdgn.airpollutionapp.domain.use_case.saved_screen_use_case.SavedStationUseCase
import com.berkerdgn.airpollutionapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SavedViewModel @Inject constructor(
    private val useCase: SavedStationUseCase,
    private val repository: StationRepository

) : ViewModel()  {

    private val _state = MutableLiveData<SavedState>(SavedState())
    var state : LiveData<SavedState> = _state

    init {
        getSavedStations()
    }

    private  fun getSavedStations(){

        viewModelScope.launch {
            val savedResource = useCase.executeGetSavedStations()

            when (val resource = savedResource.value){
                is Resource.Success ->{
                    _state.value = SavedState(savedStations = resource.data ?: emptyList())
                }
                is Resource.Error ->{
                    _state.value = SavedState(error = resource.message ?: "Error")
                }
                is Resource.Loading ->{
                    _state.value = SavedState(isLoading = true)
                }
                else->{
                    println("Unhandled resource type")
                }
            }
        }
    }

    fun deleteSavedStation(savedStation : SavedStationModel){
        viewModelScope.launch {
            repository.deleteSavedStation(savedStation)
        }
    }

    fun insertSavedStation ( savedStation: SavedStationModel){
        viewModelScope.launch {
            repository.insertSavedStation(savedStation)
        }
    }

    fun saveStation (
        stationID:String,
        stationName: String,
        startDate: String,
        endDate: String,
    ){
        val saveStation = SavedStationModel(stationID = stationID, stationName = stationName, startDate =startDate , endDate =endDate )
        insertSavedStation(saveStation)
    }

}