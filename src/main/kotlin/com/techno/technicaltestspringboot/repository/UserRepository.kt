package com.techno.technicaltestspringboot.repository

import com.techno.technicaltestspringboot.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface UserRepository: JpaRepository<UserEntity, Int> {
    @Query(value = "SELECT * FROM tbl_user WHERE username = ? AND password = ?", nativeQuery = true)
    fun findByUserPass(username: String?, password: String?): UserEntity?
}