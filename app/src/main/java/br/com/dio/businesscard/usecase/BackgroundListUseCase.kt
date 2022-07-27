package br.com.dio.businesscard.usecase

import br.com.dio.businesscard.data.BackgroundRepository

class BackgroundListUseCase(private val backgroundRepository: BackgroundRepository) {
    operator fun invoke() = backgroundRepository.getBackgroundFromDataSource()
}