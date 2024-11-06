package tech.clovy.clovyapp.core.member.entity

import tech.clovy.clovyapp.core.member.repository.MemberJpaRepository
import kotlin.jvm.optionals.getOrNull

@JvmInline
value class MemberId(val id: Long) {
    fun fetch(repository: MemberJpaRepository) = repository.findById(id).getOrNull()
}
