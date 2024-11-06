package tech.clovy.clovyapp.core.member

import tech.clovy.clovyapp.core.member.details.MemberDetails
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class MemberHolder {
    fun get() = (SecurityContextHolder.getContext().authentication.principal as MemberDetails).member
}
