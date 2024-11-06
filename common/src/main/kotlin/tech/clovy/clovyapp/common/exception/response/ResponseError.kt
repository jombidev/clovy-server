package tech.clovy.clovyapp.common.exception.response

import tech.clovy.clovyapp.common.exception.ExceptionDetail
import tech.clovy.clovyapp.common.response.ResponseEmpty
import org.springframework.http.ResponseEntity

class ResponseError(code: String, status: Int, val detail: String) : ResponseEmpty(code, status) {
    companion object {
        fun of(message: ExceptionDetail, vararg args: Any?) =
            ResponseEntity
                .status(message.status)
                .body(ResponseError(message.code, message.status.value(), message.message.format(*args)))
    }
}
