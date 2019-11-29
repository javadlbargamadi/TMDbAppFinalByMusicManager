package com.example.tmdbappfinalbymusicmanager

import android.app.Application
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger

class BaseApplicaiton : Application() {

    override fun onCreate() {
        super.onCreate()

        Logger.addLogAdapter(AndroidLogAdapter())

        component = DaggerDataProvidersComponent.builder().roomModule(RoomModule(this)).build()
    }

    companion object {
        private lateinit var component: DataProvidersComponent

        fun getDataProviderComponent(): DataProvidersComponent {
            return component
        }
    }

}
