package br.com.dio.businesscard.data

import br.com.dio.businesscard.data.domain.BackgroundCards

interface BackgroundDataSource {
    fun getBackgroundCards(): List<BackgroundCards>

}