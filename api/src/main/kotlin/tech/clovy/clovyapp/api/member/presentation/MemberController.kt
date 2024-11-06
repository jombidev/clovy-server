package tech.clovy.clovyapp.api.member.presentation

import tech.clovy.clovyapp.api.member.dto.response.MemberInfoResponseDto
import tech.clovy.clovyapp.business.member.service.MemberService
import tech.clovy.clovyapp.common.response.ResponseData
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/member")
class MemberController(
    private val memberService: MemberService
) {
    @GetMapping("/me")
    fun me(): ResponseEntity<ResponseData<MemberInfoResponseDto>> {
        val me = memberService.me()

        return ResponseData.ok(data = MemberInfoResponseDto(me.name))
    }
}
