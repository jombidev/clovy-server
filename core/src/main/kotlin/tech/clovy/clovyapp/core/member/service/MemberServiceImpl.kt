package tech.clovy.clovyapp.core.member.service

import tech.clovy.clovyapp.core.member.MemberHolder
import tech.clovy.clovyapp.business.member.dto.MemberDto
import tech.clovy.clovyapp.business.member.service.MemberService
import org.springframework.stereotype.Service

@Service
class MemberServiceImpl(
    private val memberHolder: MemberHolder
) : MemberService {
    override fun me(): MemberDto {
        val member = memberHolder.get()
        return MemberDto(member.name)
    }
}
