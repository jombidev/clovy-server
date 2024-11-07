package tech.clovy.clovyapp.common.response

sealed interface BaseResponse {
    val code: String
    val status: Int
}
