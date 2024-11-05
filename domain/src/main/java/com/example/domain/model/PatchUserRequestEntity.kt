package com.example.domain.model

data class PatchUserRequestEntity(
    val birth: String,
    val character: String,
    val introduction: String,
    val local_name: String,
    val username: String
)