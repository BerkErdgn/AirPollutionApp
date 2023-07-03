package com.berkerdgn.airpollutionapp.presentation.list_screen.view

import androidx.lifecycle.*
import com.berkerdgn.airpollutionapp.data.remote.dto.AllStationsModel
import com.berkerdgn.airpollutionapp.data.remote.dto.AllStationsModelItem
import com.berkerdgn.airpollutionapp.domain.use_case.list_screen_use_case.GetStationsUseCase
import com.berkerdgn.airpollutionapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StationsListViewModel @Inject constructor(
    private val getStationsUseCase: GetStationsUseCase
): ViewModel(){

    private val _state = MutableLiveData<StationsListState>(StationsListState())
    val state: LiveData<StationsListState> = _state

    private var job: Job? = null



    init {
        getState()
    }

    private fun getState(){

        job?.cancel()

        job = viewModelScope.launch {
            val stationsResource = getStationsUseCase.executeGetStations()

            when (val resource = stationsResource.value) {
                is Resource.Success -> {
                    _state.value = StationsListState(stations = (resource.data ?: emptyList()) as ArrayList<AllStationsModelItem>)
                }
                is Resource.Error -> {
                    _state.value = StationsListState(error = resource.message ?: "Error")
                }
                is Resource.Loading -> {
                    _state.value = StationsListState(isLoading = true)
                }
                else -> {
                    println("Unhandled resource type")
                }
            }
        }

    }

}
