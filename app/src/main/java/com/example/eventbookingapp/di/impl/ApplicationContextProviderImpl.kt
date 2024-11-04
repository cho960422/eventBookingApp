package com.example.eventbookingapp.di.impl

import android.content.Context
import com.example.data.core.ApplicationContextProvider
import com.example.eventbookingapp.EventBookingApplication

class ApplicationContextProviderImpl: ApplicationContextProvider {
    override fun getContext(): Context {
        return EventBookingApplication.applicationContext()
    }
}