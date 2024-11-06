package tech.clovy.clovyapp.business.auth.dto

data class TokenDto(
    val accessToken: String,
    val refreshToken: String
)
