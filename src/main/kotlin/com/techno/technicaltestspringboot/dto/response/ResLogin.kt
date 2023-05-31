package com.techno.technicaltestspringboot.dto.response

data class ResLogin(
    val id: Int? = null,
    val type: String? = "",
    val access_token: String? = "",
    val token_type: String? = "",
    val expires_in: Long? = null
)
