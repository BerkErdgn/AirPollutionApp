package com.berkerdgn.airpollutionapp.data.dependincyinjection

import com.berkerdgn.airpollutionapp.data.remote.StationsAPI
import com.berkerdgn.airpollutionapp.data.repository.StationsRepositoryImpl
import com.berkerdgn.airpollutionapp.domain.repository.StationRepository
import com.berkerdgn.airpollutionapp.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /*
     @Singleton
    @Provides
    fun injectNormalRepo(stationsApi: StationsAPI) = StationsRepositoryImpl(stationsApi) as StationRepository

     */

    @Singleton
    @Provides
    fun provideStationsRepository(stationsAPI: StationsAPI): StationRepository{
        return StationsRepositoryImpl(stationsAPI)
    }


    @Singleton
    @Provides
    fun injectStationsApi() : StationsAPI{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(StationsAPI::class.java)
    }




}