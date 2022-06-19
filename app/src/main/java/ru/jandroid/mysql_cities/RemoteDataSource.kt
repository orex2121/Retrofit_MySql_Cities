package ru.jandroid.mysql_cities

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RestCountriesApi {
    @GET(EndPoints.URL_GET_COUNTRIES)
    suspend fun getAllCountry(): CountryRequest
}

var restCountriesApi = Retrofit.Builder()
    .baseUrl(EndPoints.URL_ROOT)
    .addConverterFactory(GsonConverterFactory.create())
    .build()
    .create(RestCountriesApi::class.java)


