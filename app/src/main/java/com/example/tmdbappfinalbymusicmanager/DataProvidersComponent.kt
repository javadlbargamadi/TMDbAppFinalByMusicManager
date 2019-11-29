package com.example.tmdbappfinalbymusicmanager

import com.example.tmdbappfinalbymusicmanager.retrofit.RetrofitMainInterface
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = [RetrofitModule::class, RoomModule::class])
interface DataProvidersComponent {

    fun getRetrofit(): Retrofit

    fun getRetrofitMainInterface(): RetrofitMainInterface

    fun getAppDataBase(): AppDatabase

    fun getMoviesDAO(): MoviesDAO
}
