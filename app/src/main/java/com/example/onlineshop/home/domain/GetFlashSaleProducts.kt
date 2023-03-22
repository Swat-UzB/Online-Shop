package com.example.onlineshop.home.domain

import com.example.onlineshop.common.domain.repositories.ShopRepository
import com.example.onlineshop.common.utils.DispatchersProvider
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by Zayniddinov Ilyosjon on 21/03/2023.
 *  email zayniddinovilyos@gmail.com.
 */
class GetFlashSaleProducts @Inject constructor(
    private val animalRepository: ShopRepository,
    private val dispatchersProvider: DispatchersProvider
) {
    suspend operator fun invoke() =
        withContext(dispatchersProvider.io()) { animalRepository.getFlashSaleProducts() }
}