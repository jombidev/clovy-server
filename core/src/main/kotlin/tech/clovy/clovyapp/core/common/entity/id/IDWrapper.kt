package tech.clovy.clovyapp.core.common.entity.id

import org.springframework.data.jpa.repository.JpaRepository
import kotlin.jvm.optionals.getOrNull

sealed interface IDWrapper<ID : Any> {
    val get: ID
    fun <T : Any> fetch(repository: JpaRepository<T, ID>) = repository.findById(get).getOrNull()
}
