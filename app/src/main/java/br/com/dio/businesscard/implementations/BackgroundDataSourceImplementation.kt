package br.com.dio.businesscard.implementations

import android.util.Log
import br.com.dio.businesscard.data.BackgroundDataSource
import br.com.dio.businesscard.data.api.BackgroundRestApiTask
import br.com.dio.businesscard.data.domain.BackgroundCards

class BackgroundDataSourceImplementation(private val backgroundRestApiTask: BackgroundRestApiTask): BackgroundDataSource{

    companion object{
        const val TAG = "Background Repository"
    }

private val backgroundList= arrayListOf<BackgroundCards>()

 override fun getBackgroundCards(): List<BackgroundCards> {
         val request = backgroundRestApiTask.retrofitApi().getBackgroundCards().execute()

             if (request.isSuccessful)  {
                 request.body()?.let {
                     backgroundList.addAll(it)
                 }
             } else
             {
                 request.errorBody()?.let {
                     Log.d(TAG,it.toString())
                 }

             }
        return backgroundList
    }
}