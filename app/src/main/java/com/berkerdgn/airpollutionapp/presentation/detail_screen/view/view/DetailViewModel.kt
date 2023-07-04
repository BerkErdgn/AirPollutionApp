package com.berkerdgn.airpollutionapp.presentation.detail_screen.view.view



import androidx.lifecycle.*
import com.berkerdgn.airpollutionapp.data.remote.dto.StationDetailModelItem
import com.berkerdgn.airpollutionapp.domain.use_case.detail_screen_use_case.GetStationDetailUseCase
import com.berkerdgn.airpollutionapp.util.Constants.END_DATE
import com.berkerdgn.airpollutionapp.util.Constants.START_DATE
import com.berkerdgn.airpollutionapp.util.Constants.STATION_ID
import com.berkerdgn.airpollutionapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getStationDetailUseCase: GetStationDetailUseCase,
    ) : ViewModel() {

    private var _state = MutableLiveData<DetailState>(DetailState())
    var state: LiveData<DetailState> = _state


    fun getDetailAccessibleAll(stationId: String, startDate: String,endDate: String){
        getDetail(stationId, startDate, endDate)
    }

    private fun getDetail(
        stationId: String,
        startDate: String,
        endDate: String
    ) {

        viewModelScope.launch {
            val detailResource = getStationDetailUseCase.executeGetStations(stationId = stationId, startDate = startDate, endDate = endDate)

            when (val resource = detailResource.value){
                is Resource.Success ->{
                    _state.value = DetailState(stationDetails = (resource.data?: emptyList()) as ArrayList<StationDetailModelItem>)
                }
                is Resource.Loading ->{
                    _state.value = DetailState(isLoading = true)
                }
                is Resource.Error ->{
                    _state.value = DetailState(error = resource.message ?: "Error in ViewModel")
                }
                else ->{
                    println("Unhandled resource type")
                }
            }

        }

    }

}