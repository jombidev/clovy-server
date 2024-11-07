package tech.clovy.clovyapp.core.auth.service

import jakarta.servlet.ServletException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import tech.clovy.clovyapp.business.auth.service.AuthService
import tech.clovy.clovyapp.common.ContextHolder
import tech.clovy.clovyapp.common.exception.CustomException
import tech.clovy.clovyapp.core.auth.exception.AuthExceptionDetails
import tech.clovy.clovyapp.core.member.entity.Member
import tech.clovy.clovyapp.core.member.repository.MemberJpaRepository

@Service
class AuthServiceImpl(
    private val memberRepository: MemberJpaRepository,
    private val passwordEncoder: PasswordEncoder,
    private val holder: ContextHolder,
) : AuthService {
    override fun authenticate(credential: String, password: String) {
        try {
            holder.getRequest().login(credential, password)
        } catch (e: ServletException) {
            throw e.rootCause // unpack exception
        }
    }

    override fun createNewMember(name: String, credential: String, password: String) {
        if (memberRepository.existsByCredential(credential))
            throw CustomException(AuthExceptionDetails.USER_ALREADY_EXISTS, credential)

        val encodedPassword = passwordEncoder.encode(password)
        val unsavedMember = Member(
            name = name,
            credential = credential,
            password = encodedPassword,
        )

        memberRepository.save(unsavedMember)

        authenticate(credential, password)
    }
}
