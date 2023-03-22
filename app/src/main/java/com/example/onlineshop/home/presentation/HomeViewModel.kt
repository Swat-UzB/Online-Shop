package com.example.onlineshop.home.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshop.common.presentation.Event
import com.example.onlineshop.common.presentation.model.mappers.UiFlashSaleProductMapper
import com.example.onlineshop.common.presentation.model.mappers.UiLatestProductMapper
import com.example.onlineshop.common.utils.createExceptionHandler
import com.example.onlineshop.home.domain.GetFlashSaleProducts
import com.example.onlineshop.home.domain.GetLatestProducts
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Zayniddinov Ilyosjon on 20/03/2023.
 *  email zayniddinovilyos@gmail.com.
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getFlashSaleProducts: GetFlashSaleProducts,
    private val getLatestProducts: GetLatestProducts,
    private val uiFlashSaleProductMapper: UiFlashSaleProductMapper,
    private val uiLatestProductMapper: UiLatestProductMapper
) : ViewModel() {

    private val _state = MutableStateFlow(HomeViewState())
    val state: StateFlow<HomeViewState> get() = _state.asStateFlow()

    init {
        Log.d("TTT","init HomeVM")
        loadInitialData()
    }

  private fun loadInitialData() {
        val errorMessage = "Failed to load data"
        val exceptionHandler = viewModelScope.createExceptionHandler(errorMessage) {
            onFailure(it)
        }
        viewModelScope.launch(exceptionHandler) {
            Log.d("TTT","load initial data")
            val latest = async { getLatestProducts() }
            val flashSale = async { getFlashSaleProducts() }
            val latestList = latest.await()
            val flashSaleList = flashSale.await()
//            val latestList = getLatestProducts()
            if (latestList.isNotEmpty() && flashSaleList.isNotEmpty()) {
                _state.update { oldState ->
                    oldState.copy(latestList = latestList.map { uiLatestProductMapper.mapToUi(it) },
                        flashSaleList = flashSaleList.map { uiFlashSaleProductMapper.mapToUi(it) }
                    )
                }
            }
        }
    }

    private fun onFailure(failure: Throwable) {
        Log.d("TTT","failed load initial data")
        _state.update { oldState ->
            oldState.copy(
                failure = Event(failure)
            )
        }
    }
}