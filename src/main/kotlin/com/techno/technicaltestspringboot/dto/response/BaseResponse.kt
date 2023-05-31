package com.techno.technicaltestspringboot.dto.response

import com.fasterxml.jackson.annotation.JsonProperty

data class BaseResponse<T>(
    @field: JsonProperty("OUT_STAT")
    val outStatus: String?,
    @field: JsonProperty("OUT_MESS")
    val outMessage: String?,
    @field: JsonProperty("OUT_DATA")
    val outData: T? = null
)
