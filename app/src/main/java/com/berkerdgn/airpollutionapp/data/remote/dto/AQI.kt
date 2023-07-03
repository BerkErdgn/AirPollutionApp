package com.berkerdgn.airpollutionapp.data.remote.dto

data class AQI(
    val AQIIndex: Double,
    val CO: Any,
    val Color: String,
    val ContaminantParameter: String,
    val NO2: Double,
    val O3: Double,
    val PM10: Double,
    val SO2: Double,
    val State: String
)