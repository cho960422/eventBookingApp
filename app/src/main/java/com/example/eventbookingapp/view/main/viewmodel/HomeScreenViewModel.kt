package com.example.eventbookingapp.view.main.viewmodel

import android.location.Location
import androidx.activity.ComponentActivity
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.eventbookingapp.MainActivity
import com.example.eventbookingapp.config.uistate.UiAction
import com.example.eventbookingapp.model.dto.event.EventListRoomEntity
import com.example.eventbookingapp.module.LocationModuleInterface
import com.example.eventbookingapp.repository.remote_mediator.HomeEventRemoteMediator
import com.example.eventbookingapp.repository.repository_interface.EventRepository
import com.example.eventbookingapp.view.entities.event.SearchOptions
import com.google.android.gms.location.FusedLocationProviderClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@kotlinx.coroutines.ExperimentalCoroutinesApi
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
    val pagingDataFlow: Flow<PagingData<EventListRoomEntity>>; // Screen 이벤트 목록이 참조하는 flow
    val uiActionFlow = MutableSharedFlow<UiAction>()
    val accept: (UiAction) -> Unit

    init {
        getSearchOptions()

        val searches = uiActionFlow
            .filterIsInstance(UiAction.Search::class) // Search 객체가 Action 으로 주어질 경우 이 Flow 에서 데이터 생산
            .distinctUntilChanged() // 객체가 변하기 전까지 유지
            .onStart { uiActionFlow.emit(UiAction.Search(query = "")) }

        pagingDataFlow = searches.flatMapLatest { // Search 객체로 인해 생성된 Flow 를 변환하는 Flow
            eventRepository.getEventList(it.query) // repository의 Flow<PagingData> 를 반환하는 함수를 실행시켜서 Flow로 내보낸다.
        }.cachedIn(viewModelScope)

        accept = { uiAction ->
            viewModelScope.launch { uiActionFlow.emit(uiAction) }
        }
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