package tech.clovy.clovyapp.core.member.entity

import tech.clovy.clovyapp.core.common.entity.BaseIdTimeEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import java.io.Serializable

@Entity(name = "tb_member")
data class Member(
    @Column(unique = true)
    val credential: String,

    @Column
    val password: String, // bcrypt

    @Column
    val name: String,
) : BaseIdTimeEntity(), Serializable
