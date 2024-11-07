package tech.clovy.clovyapp.infra.security.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.web.context.HttpSessionSecurityContextRepository
import org.springframework.session.Session
import org.springframework.session.data.redis.RedisIndexedSessionRepository
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisIndexedHttpSession
import org.springframework.session.security.SpringSessionBackedSessionRegistry
import org.springframework.session.web.http.HeaderHttpSessionIdResolver
import org.springframework.session.web.http.HttpSessionIdResolver

@Configuration
@EnableRedisIndexedHttpSession
class SessionConfig {
    @Bean
    fun securityContextRepository() = HttpSessionSecurityContextRepository()

    /**
     * this is also mentioned in RedisSessionLogoutHandler#logout, They changed RedisSession's visibility at 3.4.0 (not stable released yet).
     * @see tech.clovy.clovyapp.infra.security.handler.RedisSessionLogoutHandler.logout
     **/
    @Bean
    fun sessionRegistry(redisIndexedSessionRepository: RedisIndexedSessionRepository): SpringSessionBackedSessionRegistry<out Session> =
        SpringSessionBackedSessionRegistry(redisIndexedSessionRepository)

    @Bean
    fun httpSessionIdResolver(): HttpSessionIdResolver = HeaderHttpSessionIdResolver.xAuthToken()
}
