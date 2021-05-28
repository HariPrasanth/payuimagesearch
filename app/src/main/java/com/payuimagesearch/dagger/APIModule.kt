package com.payuimagesearch.dagger

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.payuimagesearch.BuildConfig
import com.payuimagesearch.apilayer.APIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class APIModule {

    companion object{
        val RESPONSE_200 = 200

        val RESPONSE_400 = 400

        val RESPONSE_401 = 401

        val RESPONSE_404 = 404

        val RESPONSE_500 = 500
    }

    @Provides
    @Singleton
    fun provideClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        if(BuildConfig.DEBUG) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }else{
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
        }
        val httpClientBuilder = OkHttpClient.Builder()
        httpClientBuilder.connectTimeout(5, TimeUnit.MINUTES)
        httpClientBuilder.readTimeout(5, TimeUnit.MINUTES)
        httpClientBuilder.writeTimeout(5, TimeUnit.MINUTES)
        httpClientBuilder.addInterceptor { chain ->
            val newRequest = chain.request().newBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("app_version", BuildConfig.VERSION_CODE.toString())
                .build()
            chain.proceed(newRequest)
        }
        return httpClientBuilder.addInterceptor(httpLoggingInterceptor).build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().setLenient()
            .create()
    }

    @Provides
    @Singleton
    fun provideExpenseApiInterface(retrofit: Retrofit): APIService {
        return retrofit.create(APIService::class.java)
    }
}