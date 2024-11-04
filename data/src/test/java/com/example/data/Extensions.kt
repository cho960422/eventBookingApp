package com.example.data

import okhttp3.mockwebserver.MockResponse
import okio.buffer
import okio.source

fun enqueueMockResponse(classLoader: ClassLoader, fileName: String): MockResponse {
    val inputStream = classLoader.getResourceAsStream(fileName)
    val source = inputStream.source().buffer()
    val mockResponse = MockResponse()

    mockResponse.setBody(source.readString(Charsets.UTF_8))
    return mockResponse
}