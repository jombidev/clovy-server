package tech.clovy.clovyapp.infra.security.handler

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.logout.LogoutHandler
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler
import org.springframework.session.data.redis.RedisIndexedSessionRepository
import org.springframework.stereotype.Component
import tech.clovy.clovyapp.common.response.ResponseEmpty

@Component
class RedisSessionLogoutHandler(
    private val redisIndexedSessionRepository: RedisIndexedSessionRepository,
    private val objectMapper: ObjectMapper
) : LogoutHandler, LogoutSuccessHandler {
    override fun logout(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication?,
    ) {
        request.session?.id
            ?.takeIf {
                redisIndexedSessionRepository.findById(it) != null
                // problem solved: https://github.com/spring-projects/spring-session/blob/84f4afcaf1ad7de28ccec676541f1f9d01e60e09/spring-session-data-redis/src/main/java/org/springframework/session/data/redis/RedisIndexedSessionRepository.java#L776
                // this class' visibility is changed at 3.4.0 (Not stable released yet). so bumping version to 3.4.0-RC1.
                // TODO: Bump version to 3.4.0 (stable) when it releases.
            }
            ?.let {
                redisIndexedSessionRepository.deleteById(it)
            }
    }

    override fun onLogoutSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication?,
    ) {
        SecurityContextHolder.clearContext()
        val responseBody = objectMapper.writeValueAsString(ResponseEmpty("OK", HttpStatus.OK.value()))
        response.status = 200
        response.writer.use {
            it.write(responseBody)
            it.flush()
        }
    }
}
