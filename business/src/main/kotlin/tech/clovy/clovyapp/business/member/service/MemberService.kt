package tech.clovy.clovyapp.business.member.service

import tech.clovy.clovyapp.business.member.dto.MemberDto

interface MemberService {
    fun me(): MemberDto
}
