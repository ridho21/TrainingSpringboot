package com.techno.technicaltestspringboot.dto.request

import com.fasterxml.jackson.annotation.JsonProperty

data class ReqBrandDetails(
    @JsonProperty("cd_brand")
    val cd_brand: String? = ""
)
