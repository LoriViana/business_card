package br.com.dio.businesscard.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BackgroundRestApiTask {

    companion object{
        const val BASE_URL = "https://loriviana.github.io/business-card-api/"
    }
    private fun backgroundProvider(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun retrofitApi(): BackgroundApi = backgroundProvider().create(BackgroundApi::class.java)
}