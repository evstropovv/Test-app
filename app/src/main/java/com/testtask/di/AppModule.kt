package com.testtask.di

import android.app.Application
import android.content.Context
import com.testtask.data.ApiRetrofit
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class, DataModule::class])
public class AppModule(val application: Application) {

    @Provides
    @Singleton
    public fun provideContext(): Context {
        return application
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor).build()
        return client
    }

    @Provides
    @Singleton
    fun provideRetrofit(interceptor: OkHttpClient): ApiRetrofit {
        val retrofit = Retrofit.Builder()
            .client(interceptor)
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(ApiRetrofit::class.java)
    }
}
