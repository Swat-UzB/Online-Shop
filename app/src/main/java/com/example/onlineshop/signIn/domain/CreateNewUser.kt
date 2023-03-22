package com.example.onlineshop.signIn.domain

import com.example.onlineshop.common.data.cache.model.User
import com.example.onlineshop.common.domain.repositories.ShopRepository
import com.example.onlineshop.common.utils.DispatchersProvider
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by Zayniddinov Ilyosjon on 20/03/2023.
 *  email zayniddinovilyos@gmail.com.
 */
class CreateNewUser @Inject constructor(
    private val animalRepository: ShopRepository,
    private val dispatchersProvider: DispatchersProvider
) {
    suspend operator fun invoke(user: User):Long {
        return withContext(dispatchersProvider.io()) {
            animalRepository.insertUser(user)
        }
    }
}