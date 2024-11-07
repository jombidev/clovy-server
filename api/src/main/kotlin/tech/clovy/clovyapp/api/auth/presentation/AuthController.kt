package tech.clovy.clovyapp.api.auth.presentation

import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import tech.clovy.clovyapp.api.auth.dto.request.AuthenticateRequestDto
import tech.clovy.clovyapp.api.auth.dto.request.CreateMemberRequestDto
import tech.clovy.clovyapp.business.auth.service.AuthService
import tech.clovy.clovyapp.common.response.ResponseEmpty

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService
) {
    @PostMapping("/login")
    fun authenticate(@RequestBody @Valid request: AuthenticateRequestDto): ResponseEntity<ResponseEmpty> {
        authService.authenticate(request.credential, request.password)
        return ResponseEmpty.ok()
    }

    @PostMapping("/register")
    fun createMember(@RequestBody @Valid request: CreateMemberRequestDto): ResponseEntity<ResponseEmpty> {
        authService.createNewMember(request.name, request.credential, request.password)
        return ResponseEmpty.created()
    }
}
