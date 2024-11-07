package tech.clovy.clovyapp.core.member.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import tech.clovy.clovyapp.core.common.entity.BaseTimeEntity
import tech.clovy.clovyapp.core.common.entity.id.WrappedUUID
import java.io.Serializable

@Entity(name = "tb_member")
data class Member(
    @Column(unique = true, nullable = false)
    val credential: String,

    @Column(nullable = false)
    val password: String, // bcrypt

    @Column(nullable = false)
    val name: String,

    @Id
    val id: WrappedUUID = WrappedUUID.NULL
) : BaseTimeEntity(), Serializable
