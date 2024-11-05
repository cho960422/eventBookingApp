package com.example.data.repository

import com.example.data.core.ApplicationContextProvider
import com.example.data.model.AppDatabase
import com.example.data.model.dto.base.BaseResponse
import com.example.data.model.dto.event.EventDetailDto
import com.example.data.model.remote.EventService
import com.example.domain.repository.EventRepository
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import retrofit2.Response


class EventRepositoryImplTest {
    lateinit var repository: EventRepository
    lateinit var service: EventService
    lateinit var db: AppDatabase
    lateinit var applicationContextProvider: ApplicationContextProvider

    @Before
    fun setup() = runBlocking {
        service = mock()
        db = mock()
        applicationContextProvider = mock()


        repository = EventRepositoryImpl(
            service = service,
            db = db,
            applicationContextProvider = applicationContextProvider
        )
    }

    @After
    fun shutDown() {

    }

    @Test
    fun writeEvent() {

    }

    @Test
    fun getEvent() {
    }

    @Test
    fun deleteEvent() {
    }

    @Test
    fun patchEvent() {
    }

    @Test
    fun editCategoryOptions() {
    }

    @Test
    fun editSortOptions() {
    }

    @Test
    fun getSearchOptions() {
    }

    @Test
    fun getEventList() {
    }
}