package tech.clovy.clovyapp.core.common.entity.id

@JvmInline
value class WrappedLong(val _id: Long?) : IDWrapper<Long> {
    override val get: Long get() = _id!!

    companion object {
        val NULL = WrappedLong(null)
    }
}
