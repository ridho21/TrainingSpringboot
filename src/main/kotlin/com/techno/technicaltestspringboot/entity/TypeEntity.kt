package com.techno.technicaltestspringboot.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "tbl_type")
data class TypeEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(unique = true)
    val id: Int? = null,
    val type: String = "",
    val access_token: String? = "",
    val token_type: String? = "",
    val expires_in: Long? = null
)