package com.isilon.beinconnect.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.isilon.beinconnect.data.api.ApiHelper
import com.isilon.beinconnect.data.repository.BeinConnectRepository
import com.isilon.beinconnect.ui.main.viewmodel.BeinConnectViewModel

class ViewModelFactory(private val apiHelper: ApiHelper): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(BeinConnectViewModel::class.java))
            return BeinConnectViewModel(BeinConnectRepository(apiHelper)) as T
        throw IllegalArgumentException("Undefined Class name !")
    }
}