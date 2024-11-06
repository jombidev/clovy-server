package tech.clovy.clovyapp.core.member.details

import tech.clovy.clovyapp.core.member.entity.Member
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class MemberDetails(val member: Member) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = mutableSetOf()
    override fun getPassword(): String = member.password
    override fun getUsername(): String = member.credential
}
