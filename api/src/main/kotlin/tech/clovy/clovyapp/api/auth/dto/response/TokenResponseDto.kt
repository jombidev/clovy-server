package tech.clovy.clovyapp.api.auth.dto.response

data class TokenResponseDto(
    val accessToken: String,
    val refreshToken: String
)
