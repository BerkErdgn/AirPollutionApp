package com.berkerdgn.airpollutionapp.presentation.save_screen.view

import com.berkerdgn.airpollutionapp.data.remote.dto.saved_dto.SavedStationModel

data class SavedState (
    val isLoading : Boolean = false,
    val error : String = "",
    val savedStations : List<SavedStationModel> = emptyList()
        )