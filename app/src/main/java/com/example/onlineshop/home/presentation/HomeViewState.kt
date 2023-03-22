package com.example.onlineshop.home.presentation

import com.example.onlineshop.common.presentation.Event
import com.example.onlineshop.common.presentation.model.UiFlashSaleProduct
import com.example.onlineshop.common.presentation.model.UiLatestProduct

/**
 * Created by Zayniddinov Ilyosjon on 20/03/2023.
 *  email zayniddinovilyos@gmail.com.
 */
data class HomeViewState(
    val latestList: List<UiLatestProduct> = emptyList(),
    val flashSaleList: List<UiFlashSaleProduct> = emptyList(),
    val isLoading: Boolean = false,
    val failure: Event<Throwable>? = null
)
