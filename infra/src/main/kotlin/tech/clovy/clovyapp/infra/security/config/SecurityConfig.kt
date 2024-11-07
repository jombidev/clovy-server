package tech.clovy.clovyapp.infra.security.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.session.SessionRegistry
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import tech.clovy.clovyapp.infra.exception.ExceptionHandleFilter
import tech.clovy.clovyapp.infra.security.handler.RedisSessionLogoutHandler

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val authExceptionFilter: ExceptionHandleFilter,
    private val sessionRegistry: SessionRegistry,
    private val redisSessionLogoutHandler: RedisSessionLogoutHandler
) {

    @Bean
    fun filterChain(http: HttpSecurity, authManager: AuthenticationManager): SecurityFilterChain {
        return http
            .httpBasic { it.disable() }
            .csrf { it.disable() }
            .cors { it.configurationSource(corsConfigurationSource()) }
            .authenticationManager(authManager)
            .sessionManagement {
                it
                    .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                    .sessionFixation { conf -> conf.newSession() }
                    .maximumSessions(1)
                    .sessionRegistry(sessionRegistry)
            }
            .authorizeHttpRequests {
                it
                    .requestMatchers("/auth/register", "/auth/login").anonymous()
                    .requestMatchers("/auth/logout").permitAll()
                    .anyRequest().authenticated()
            }
            .formLogin { it.disable() }
            .logout {
                it
                    .invalidateHttpSession(true)
                    .logoutUrl("/auth/logout")
                    .addLogoutHandler(redisSessionLogoutHandler)
                    .logoutSuccessHandler(redisSessionLogoutHandler)
            }
            .addFilterBefore(authExceptionFilter, UsernamePasswordAuthenticationFilter::class.java)
            .build()
    }

    @Bean
    fun corsConfigurationSource() = UrlBasedCorsConfigurationSource()
        .apply {
            registerCorsConfiguration("/**",
                CorsConfiguration()
                    .apply { // kotlin style builder
                        addAllowedOriginPattern("*")
                        addAllowedHeader("*")
                        addAllowedMethod("*")
                        allowCredentials = true
                    }
            )
        }
}
