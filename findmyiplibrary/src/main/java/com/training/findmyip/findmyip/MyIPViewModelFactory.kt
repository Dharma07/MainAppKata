package com.training.findmyip.findmyip

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.training.findmyip.api.RetrofitClient
import com.training.findmyip.data.MyIPDataSource
import com.training.findmyip.data.MyIPRepository

@Suppress("UNCHECKED_CAST")
class MyIPViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MyIPViewModel::class.java)) {
            return MyIPViewModel(
                myIpRepository = MyIPRepository(myIPDataSource = MyIPDataSource(api = RetrofitClient.myIpClient))
            ) as T
        }
        throw IllegalArgumentException("View model is not found")

    }
}