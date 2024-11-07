package tech.clovy.clovyapp.core.auth.exception

import tech.clovy.clovyapp.common.exception.ExceptionDetail
import org.springframework.http.HttpStatus

enum class AuthExceptionDetails(override val message: String, override val status: HttpStatus) : ExceptionDetail {
    BAD_CREDENTIALS("아이디 또는 비밀번호가 잘못 되었음", HttpStatus.UNAUTHORIZED),
    USER_ALREADY_EXISTS("'%s' 사용자가 이미 존재함", HttpStatus.BAD_REQUEST),
    ;
    override val code = name
}
