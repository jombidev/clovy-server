package tech.clovy.clovyapp.api.auth.dto.request

import com.fasterxml.jackson.annotation.JsonCreator

data class NewTokenRequestDto @JsonCreator constructor(
    val refreshToken: String
)