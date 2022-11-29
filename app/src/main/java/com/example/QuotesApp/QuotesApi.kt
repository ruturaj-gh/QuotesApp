package com.example.QuotesApp

import retrofit2.Response
import retrofit2.http.GET

interface QuotesApi {

    @GET("/Quotes")
    suspend fun getQuotes ():Response<QuoteList>


}