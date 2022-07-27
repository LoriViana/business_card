package br.com.dio.businesscard.data

class BackgroundRepository (private val backgroundDataSource: BackgroundDataSource){

    fun getBackgroundFromDataSource()= backgroundDataSource.getBackgroundCards()

}