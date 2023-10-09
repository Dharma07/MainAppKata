package com.training.findmyip.data

import com.training.findmyip.api.MyIpApi
import com.training.findmyip.api.RetrofitClient
import com.training.findmyip.model.FindMyIPResponse
import retrofit2.Response

class MyIPDataSource(val api: MyIpApi) {
    suspend fun callFindMyIp(): Response<FindMyIPResponse> = api.findMyIP()
}