package com.example.eventbookingapp.repository.paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.eventbookingapp.model.AppDatabase
import com.example.eventbookingapp.model.dto.event.EventListRoomEntity
import javax.inject.Inject

class HomeEventPagingSource @Inject constructor(
    private val db: AppDatabase,
    private val query: String
): PagingSource<String, EventListRoomEntity>() {
    val eventDao = db.eventDao()

    // 오류가 나거나 새로고침 시 어느 키를 pageKey로 설정할지 결정하는 부분
    override fun getRefreshKey(state: PagingState<String, EventListRoomEntity>): String? {
        return state.lastItemOrNull()?.id
    }

    override suspend fun load(params: LoadParams<String>): LoadResult<String, EventListRoomEntity> {
        try {
            val lastItemKey = params.key

            val list = if (lastItemKey == null) {
                
            } else {
                LoadResult
            }
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}