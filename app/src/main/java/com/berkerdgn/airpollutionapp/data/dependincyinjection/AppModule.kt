package com.berkerdgn.airpollutionapp.data.dependincyinjection

import android.content.Context
import androidx.room.Room
import com.berkerdgn.airpollutionapp.data.remote.StationsAPI
import com.berkerdgn.airpollutionapp.data.repository.StationsRepositoryImpl
import com.berkerdgn.airpollutionapp.data.room_db.StationDao
import com.berkerdgn.airpollutionapp.data.room_db.StationDataBase
import com.berkerdgn.airpollutionapp.domain.repository.StationRepository
import com.berkerdgn.airpollutionapp.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {



    @Singleton
    @Provides
    fun provideStationsRepository(stationsAPI: StationsAPI, stationDao: StationDao): StationRepository{
        return StationsRepositoryImpl(stationsAPI, stationDao)
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


    @Singleton
    @Provides
    fun injectStationDataBase(
        @ApplicationContext context: Context) = Room.databaseBuilder(
        context = context, StationDataBase::class.java,"stationdb"
        ).build()

    @Provides
    @Singleton
    fun injectDao(dataBase: StationDataBase)= dataBase.StationDao()



}