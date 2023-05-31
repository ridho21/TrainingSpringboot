package com.techno.technicaltestspringboot.dto.request

import javax.validation.constraints.Pattern
import javax.validation.constraints.Size
import kotlin.math.max


data class ReqBrand(
    @field: Pattern(regexp = "^[a-zA-Z0-9]*\$", message = "Only Alphanumeric allowed")
    @field: Size(max= 10, message = "Text max 10 characters")
    val desc_brand: String? = ""
//    val getListFilterUnitBrand: ReqBrandDetails
)
