package br.com.dio.businesscard.data.api

import br.com.dio.businesscard.data.domain.BackgroundCards
import retrofit2.Call
import retrofit2.http.GET

interface BackgroundApi {
    @GET("background.json")
    fun getBackgroundCards(): Call<List<BackgroundCards>>
}