package com.example.eventbookingapp.view.main.viewmodel

import android.location.Location
import androidx.activity.ComponentActivity
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventbookingapp.MainActivity
import com.example.eventbookingapp.module.LocationModuleInterface
import com.example.eventbookingapp.repository.repository_interface.EventRepository
import com.example.eventbookingapp.view.entities.event.SearchOptions
import com.google.android.gms.location.FusedLocationProviderClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val locationModule: LocationModuleInterface,
    private val eventRepository: EventRepository
): ViewModel() {
    val query = mutableStateOf<String>("")
    // SharingStarted.Eagerly 적용 시 구독자가 없어도 데이터를 가져옴
    val currentLocation: StateFlow<Location?> =
        locationModule.getCurrentLocation().stateIn(viewModelScope, SharingStarted.Eagerly, null)
    val searchOptions: StateFlow<SearchOptions> = eventRepository.getSearchOptions().stateIn(viewModelScope, SharingStarted.Eagerly, SearchOptions(null, null))

    init {
        getSearchOptions()
    }

    private fun getSearchOptions() {
        viewModelScope.launch {
            searchOptions.collectLatest {
                // TODO() API NEW CAll with Query
            }
        }
    }

    fun editCategory(category: Int) {
        viewModelScope.launch {
            eventRepository.editCategoryOptions(category)
        }
    }

    fun editSort(sort: Int) {
        viewModelScope.launch {
            eventRepository.editSortOptions(sort)
        }
    }
}