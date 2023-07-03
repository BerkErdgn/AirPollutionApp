package com.berkerdgn.airpollutionapp.data.remote.dto

data class Concentration(
    val CO: Any,
    val NO2: Double,
    val O3: Double,
    val PM10: Double,
    val SO2: Double
)