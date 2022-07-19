package br.com.dio.businesscard.data.remote

import br.com.dio.businesscard.data.domain.BackgroundCards
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET

interface BusinessCardApi {
    @GET("background.json")
    fun getBackgroundCards(): Call<List<BackgroundCards>>


}