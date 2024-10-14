package com.example.eventbookingapp.config

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import mu.KotlinLogging

val logger = KotlinLogging.logger {}
val Context.userDataStore by preferencesDataStore(
    name = USER_PREFERENCES_KEY
)
val Context.locationDataStore by preferencesDataStore(
    name = LOCATION_PREFERENCES_KEY
)
val Context.searchOptionsDataStore by preferencesDataStore(
    name = SEARCH_OPTIONS_PREFERENCES_KEY
)