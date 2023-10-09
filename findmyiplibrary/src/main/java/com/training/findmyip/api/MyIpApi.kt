package com.training.findmyip.api

import com.training.findmyip.model.FindMyIPResponse
import retrofit2.Response
import retrofit2.http.GET

interface MyIpApi {
    @GET("json/")
    suspend fun findMyIP() : Response<FindMyIPResponse>
}