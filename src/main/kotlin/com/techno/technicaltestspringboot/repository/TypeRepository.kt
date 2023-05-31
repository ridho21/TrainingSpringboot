package com.techno.technicaltestspringboot.repository

import com.techno.technicaltestspringboot.entity.TypeEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface TypeRepository: JpaRepository<TypeEntity, Int> {
    @Query(value = "SELECT * FROM tbl_type WHERE type = ?", nativeQuery = true)
    fun findByType(type: String?): TypeEntity?
}