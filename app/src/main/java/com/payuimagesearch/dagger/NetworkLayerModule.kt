package com.payuimagesearch.dagger

import com.google.gson.Gson
import com.payuimagesearch.apilayer.APINetworkLayer
import com.payuimagesearch.apilayer.APIService
import com.payuimagesearch.apilayer.ErrorProcessor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class NetworkLayerModule {

    @Provides
    @Singleton
    fun provideNetworkLayer(apiService: APIService, errorProcessor : ErrorProcessor) : APINetworkLayer {
        return  APINetworkLayer(apiService, errorProcessor)
    }

    @Provides
    fun provideErrorProcessor(gson : Gson) : ErrorProcessor {
        return ErrorProcessor(gson)
    }
}