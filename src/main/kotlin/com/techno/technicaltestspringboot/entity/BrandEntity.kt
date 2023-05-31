package com.techno.technicaltestspringboot.entity

import javax.persistence.*

@Entity
@Table(name = "tbl_brand")
data class BrandEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(unique = true)
    val cd_brand: Int? = null,

    val desc_brand: String = "",
)
