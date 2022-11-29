package com.example.QuotesApp


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetroFitHelper {

    var url="https://type.fit/api/quotes"

    fun getInstance():Retrofit{
        return Retrofit.Builder().baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

}