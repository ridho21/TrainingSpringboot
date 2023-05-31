package com.techno.technicaltestspringboot.repository

import com.techno.technicaltestspringboot.entity.BrandEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface BrandRepository: JpaRepository<BrandEntity, Int> {
    @Query(value = "SELECT * FROM tbl_brand WHERE LOWER(desc_brand) LIKE %?%", nativeQuery = true)
    fun findByCodeBrand(desc_brand: String?): BrandEntity?
}