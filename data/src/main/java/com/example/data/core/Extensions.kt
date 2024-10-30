package com.example.data.core

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore

val Context.userDataStore by preferencesDataStore(
    name = USER_PREFERENCES_KEY
)
val Context.locationDataStore by preferencesDataStore(
    name = LOCATION_PREFERENCES_KEY
)
val Context.searchOptionsDataStore by preferencesDataStore(
    name = SEARCH_OPTIONS_PREFERENCES_KEY
)