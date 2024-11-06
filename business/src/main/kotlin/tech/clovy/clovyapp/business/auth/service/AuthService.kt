package tech.clovy.clovyapp.business.auth.service

import tech.clovy.clovyapp.business.auth.dto.TokenDto

interface AuthService {
    fun authenticate(credential: String, password: String): TokenDto
    fun createNewMember(name: String, credential: String, password: String): Long
    fun getNewToken(refreshToken: String): TokenDto
}
