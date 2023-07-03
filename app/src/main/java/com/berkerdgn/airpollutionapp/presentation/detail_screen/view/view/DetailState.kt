package com.berkerdgn.airpollutionapp.presentation.detail_screen.view.view

import com.berkerdgn.airpollutionapp.data.remote.dto.StationDetailModelItem

data class DetailState (
    val isLoading: Boolean = false,
    val stationDetails: ArrayList<StationDetailModelItem> = ArrayList<StationDetailModelItem>(),
    val error: String = "station error"
)