package tech.clovy.clovyapp.infra.exception

import com.fasterxml.jackson.databind.ObjectMapper
import tech.clovy.clovyapp.common.exception.CustomException
import tech.clovy.clovyapp.common.exception.ExceptionDetail
import tech.clovy.clovyapp.common.exception.GlobalExceptionDetail
import tech.clovy.clovyapp.common.exception.response.ResponseError
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class AuthExceptionHandleFilter(private val mapper: ObjectMapper) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        chain: FilterChain
    ) {
        try {
            chain.doFilter(request, response)
        } catch (e: CustomException) {
            response.writeJson(e.detail, *e.formats)
        } catch (e: Exception) {
            e.printStackTrace()
            response.writeJson(GlobalExceptionDetail.INTERNAL_SERVER_ERROR)
        }
    }

    private fun HttpServletResponse.writeJson(detail: ExceptionDetail, vararg formats: Any?) {
        status = detail.status.value()
        val body = mapper.writeValueAsBytes(
            ResponseError(
                detail.code,
                detail.status.value(),
                detail.message.format(*formats)
            )
        )
        return outputStream.use {
            it.write(body)
            it.flush()
        }
    }
}
