package com.training.findmyip.findmyip

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.training.findmyip.data.MyIPRepository
import com.training.findmyip.data.Resource
import com.training.findmyip.model.FindMyIPResponse
import kotlinx.coroutines.launch

class MyIPViewModel(private val myIpRepository: MyIPRepository) : ViewModel() {


    private var _findMyIpResponse: MutableLiveData<Resource<FindMyIPResponse>> = MutableLiveData()
    val findMyIPResponse: LiveData<Resource<FindMyIPResponse>> = _findMyIpResponse

    fun findMyIp() {
        viewModelScope.launch {
            callFindMyIPApi()
        }
    }

    private suspend fun callFindMyIPApi() {
        _findMyIpResponse.postValue(Resource.Loading())
        val response = myIpRepository.findMyIp()
        _findMyIpResponse.postValue(response)
    }
}