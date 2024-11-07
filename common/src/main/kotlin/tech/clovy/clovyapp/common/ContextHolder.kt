package tech.clovy.clovyapp.common

import jakarta.servlet.http.HttpSession
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import tech.clovy.clovyapp.common.exception.CustomException
import tech.clovy.clovyapp.common.exception.GlobalExceptionDetails

@Component
class ContextHolder {
    private fun getAttributes() = RequestContextHolder.currentRequestAttributes() as ServletRequestAttributes
    fun getRequest() = getAttributes().request

    fun getSession() = getSessionOrNull() ?: throw CustomException(GlobalExceptionDetails.INVALID_SESSION)
    fun getSessionOrNull(): HttpSession? = getAttributes().request.getSession(false)

}
