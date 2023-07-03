package com.berkerdgn.airpollutionapp.data.remote.dto

data class StationDetailModelItem(
    val AQI: AQI,
    val Concentration: Concentration,
    val ReadTime: String
)