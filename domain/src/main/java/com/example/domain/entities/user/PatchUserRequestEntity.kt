package com.example.domain.entities.user

data class PatchUserRequestEntity(
    val birth: String,
    val character: String,
    val introduction: String,
    val localName: String,
    val username: String
)