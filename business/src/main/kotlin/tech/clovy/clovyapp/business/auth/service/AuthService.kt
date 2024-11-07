package tech.clovy.clovyapp.business.auth.service

interface AuthService {
    fun authenticate(credential: String, password: String)
    fun createNewMember(name: String, credential: String, password: String)
}
