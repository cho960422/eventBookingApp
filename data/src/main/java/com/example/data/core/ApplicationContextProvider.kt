package com.example.data.core

import android.content.Context

interface ApplicationContextProvider {
    fun getContext(): Context
}