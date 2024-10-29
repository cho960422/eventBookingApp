package com.example.data.paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.AppDatabase
import com.example.data.dto.event.EventListRoomEntity
import javax.inject.Inject

class HomeEventPagingSource @Inject constructor(
    private val db: AppDatabase,
    private val query: String
): PagingSource<Int, EventListRoomEntity>() {
    val eventDao = db.eventDao()

    // 오류가 나거나 새로고침 시 어느 키를 pageKey로 설정할지 결정하는 부분
    override fun getRefreshKey(state: PagingState<Int, EventListRoomEntity>): Int? {
        return if (state.anchorPosition == null) null else state.anchorPosition!! + 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, EventListRoomEntity> {
        try {
            val lastItemKey = params.key ?: 0

            val list = eventDao.getEventList(query, lastItemKey)

            return LoadResult.Page(data = list, nextKey = lastItemKey + list.size, prevKey = null)
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}