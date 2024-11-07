package tech.clovy.clovyapp.common.response

import org.springframework.http.ResponseEntity
import tech.clovy.clovyapp.common.exception.ExceptionDetail

data class ResponseError(override val code: String, override val status: Int, val detail: String) : BaseResponse {
    companion object {
        fun of(message: ExceptionDetail, vararg args: Any?) =
            ResponseEntity
                .status(message.status)
                .body(ResponseError(message.code, message.status.value(), message.message.format(*args)))
    }
}
