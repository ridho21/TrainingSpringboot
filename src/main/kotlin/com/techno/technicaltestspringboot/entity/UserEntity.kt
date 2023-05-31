package com.techno.technicaltestspringboot.entity

import javax.persistence.*

@Entity
@Table(name = "tbl_user")
data class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(unique = true)
    val id: Int? = null,

    val username: String? = "",
    val password: String? = ""
)
