package tech.clovy.clovyapp.infra.security.providers

import tech.clovy.clovyapp.common.exception.CustomException
import tech.clovy.clovyapp.common.exception.GlobalExceptionDetail
import tech.clovy.clovyapp.infra.security.jwt.JwtAuthToken
import tech.clovy.clovyapp.infra.security.jwt.TokenValidator
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component

@Component
class JwtProvider(
    private val validator: TokenValidator
) : AuthenticationProvider {
    override fun authenticate(authentication: Authentication?): Authentication {
        val jwt = authentication?.credentials?.toString()
            ?: throw CustomException(GlobalExceptionDetail.INTERNAL_SERVER_ERROR)

        return validator.validate(jwt)
    }

    override fun supports(authentication: Class<*>): Boolean {
        return JwtAuthToken::class.java == authentication
    }
}
