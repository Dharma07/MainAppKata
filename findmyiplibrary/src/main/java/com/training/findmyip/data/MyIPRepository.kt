package com.training.findmyip.data

import com.training.findmyip.model.FindMyIPResponse

class MyIPRepository(private val myIPDataSource: MyIPDataSource) {

    suspend fun findMyIp(): Resource<FindMyIPResponse> {
        val response = myIPDataSource.callFindMyIp()

        return if (response.isSuccessful) {
            response.body()?.let { result ->
                Resource.Success(result)
            } ?: kotlin.run {
                Resource.Error("Error occurred")
            }
        } else {
            Resource.Error("Error occurred")
        }
    }
}