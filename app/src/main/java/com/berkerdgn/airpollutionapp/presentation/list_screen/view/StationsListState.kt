package com.berkerdgn.airpollutionapp.presentation.list_screen.view

import androidx.lifecycle.MutableLiveData
import com.berkerdgn.airpollutionapp.data.remote.dto.AllStationsModel
import com.berkerdgn.airpollutionapp.data.remote.dto.AllStationsModelItem

data class StationsListState(
  val isLoading: Boolean = false,
  val stations : ArrayList<AllStationsModelItem> =ArrayList<AllStationsModelItem>() ,
  val error : String = "station error",

)