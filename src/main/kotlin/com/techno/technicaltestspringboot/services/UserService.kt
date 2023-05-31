package com.techno.technicaltestspringboot.services

import com.techno.technicaltestspringboot.dto.response.ResLogin

interface UserService {
    fun findType(type: String?): ResLogin
//    fun loginUser(username: String?, password: String?): ResLogin
    fun validateUser(token: String?): Boolean
    fun saveTokenData(type: String, access_token: String, expires_in: Long, token_type: String)
    fun updateTokenData(type: String, access_token: String, expires_in: Long, token_type: String)
}