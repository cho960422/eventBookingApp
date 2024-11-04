package com.example.data.remote_mediator

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.data.mapper.EventMapper
import com.example.data.model.AppDatabase
import com.example.data.model.dto.event.EventListRoomEntity
import com.example.data.model.remote.EventService
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class HomeEventRemoteMediator @Inject constructor(
    private val eventService: EventService,
    private val db: AppDatabase,
    private val query: String
): RemoteMediator<Int, EventListRoomEntity>() {
    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, EventListRoomEntity>
    ): MediatorResult {
        return try {
            val storage = db.eventDao()
            // 어디서부터 보내달라고 요청할지 정하는 변수
            val loadKey = when (loadType) {
                LoadType.REFRESH -> {
                    null
                }
                LoadType.PREPEND -> {
                    state.lastItemOrNull()?.id
                }
                // 앞에서 추가할 일은 없기 때문에 이 부분은 필요할 때 구현하면 됩니다.
                LoadType.APPEND -> {
                    state.lastItemOrNull()?.id
                }
            }

            val response = eventService.getEvents()
            val arr = response.body()?.data ?: listOf()
            val convertArr = arr.map {
                EventMapper.eventDetailDtoToListRoomEntity(it)
            }.toTypedArray()

            db.withTransaction {

                if (loadType == LoadType.REFRESH) {
                    storage.deleteAll()
                }

                storage.insertAll(*convertArr)
            }

            MediatorResult.Success(
                endOfPaginationReached = arr.isNotEmpty()
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}