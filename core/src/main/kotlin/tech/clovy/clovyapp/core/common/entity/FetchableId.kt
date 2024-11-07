package tech.clovy.clovyapp.core.common.entity

import org.springframework.data.jpa.repository.JpaRepository
import kotlin.jvm.optionals.getOrNull

@JvmInline
value class FetchableId(private val _id: Long? = null) {
    val get get() = _id!!
    fun <T : Any> fetch(repository: JpaRepository<T, Long>) = repository.findById(get).getOrNull()

    companion object {
        val NULL = FetchableId(null)
    }
}
