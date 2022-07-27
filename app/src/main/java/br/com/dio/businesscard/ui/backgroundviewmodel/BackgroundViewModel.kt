package br.com.dio.businesscard.ui.backgroundviewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.dio.businesscard.data.BackgroundRepository
import br.com.dio.businesscard.data.api.BackgroundRestApiTask
import br.com.dio.businesscard.data.domain.BackgroundCards
import br.com.dio.businesscard.implementations.BackgroundDataSourceImplementation
import br.com.dio.businesscard.usecase.BackgroundListUseCase

class BackgroundViewModel: ViewModel()  {

    companion object{
        const val TAG = "Backgroud Repository"
    }

    private val backgroundRestApiTask = BackgroundRestApiTask()
    private val backgroundDataSource = BackgroundDataSourceImplementation(backgroundRestApiTask)
    private val backgroundRepository = BackgroundRepository(backgroundDataSource)
    private val backgroundListUseCase = BackgroundListUseCase(backgroundRepository)

    private var _backgroundList = MutableLiveData<List<BackgroundCards>>()
    val backgroundList:LiveData<List<BackgroundCards>>
    get()= _backgroundList

    fun init() {
        getBackground()
    }

    private fun getBackground() {
        Thread{
            try{
                _backgroundList.postValue(backgroundListUseCase.invoke())
            }catch (exception: Exception){
                Log.d (TAG, exception.message.toString())
            }
        }.start()
    }

}