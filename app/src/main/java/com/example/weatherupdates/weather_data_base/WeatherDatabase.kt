package com.example.weatherupdates.weather_data_base

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.weatherupdates.ui.activities.viewmodels.WeatherVIewModel
import com.example.weatherupdates.weathermodel.WheatherResponse

@Database(entities = [WeatherEntities::class], version = 1)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherDao(): UserDataAccessOpject

    companion object {
        private const val DB_NAME = "UserDB.db"
        private var INSTANCE: WeatherDatabase? = null

        fun getInstance(application: Application): WeatherDatabase {

            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(application, WeatherDatabase::class.java, DB_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE!!
        }
    }

}
