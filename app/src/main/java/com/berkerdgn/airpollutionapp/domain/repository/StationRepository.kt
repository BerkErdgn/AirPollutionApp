package com.berkerdgn.airpollutionapp.domain.repository

import com.berkerdgn.airpollutionapp.data.remote.dto.AllStationsModel
import com.berkerdgn.airpollutionapp.util.Resource

interface StationRepository {

     suspend  fun getStations (): AllStationsModel


}