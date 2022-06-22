package com.isilon.beinconnect.ui.main.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.isilon.beinconnect.data.model.Model
import com.isilon.beinconnect.data.repository.BeinConnectRepository
import com.isilon.beinconnect.utils.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class BeinConnectViewModel(private val beinConnectRepository: BeinConnectRepository): ViewModel() {
    val data = MutableLiveData<Resource<Model>>()
    private val compositeDisposable = CompositeDisposable()

    fun fetchMovies(api_key: String, page: Int){
        data.postValue(Resource.loading(null))
        Log.e("queryViewModel",page.toString())
        Log.e("queryViewModel",api_key)
        beinConnectRepository.searchMoviesFromQuery(api_key,page)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())?.let{
                compositeDisposable.add(
                    it.subscribe({model ->
                        data.value = Resource.success(model)

                        Log.e("movieList", model.toString())
                    }){
                        Log.e("ERR",data.value.toString())
                        data.value= Resource.error("Something went wrong", null)
                    })
            }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}