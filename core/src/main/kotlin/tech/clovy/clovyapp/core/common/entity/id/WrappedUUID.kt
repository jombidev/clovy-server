package tech.clovy.clovyapp.core.common.entity.id

import java.util.UUID

@JvmInline
value class WrappedUUID(val _id: UUID?) : IDWrapper<UUID> {
    override val get: UUID get() = _id!!

    companion object {
        val NULL = WrappedUUID(null)
    }
}
