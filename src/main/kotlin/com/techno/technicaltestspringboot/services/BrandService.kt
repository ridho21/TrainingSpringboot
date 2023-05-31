package com.techno.technicaltestspringboot.services

import com.techno.technicaltestspringboot.dto.request.ReqBrand
import com.techno.technicaltestspringboot.dto.response.ResBrand

interface BrandService {
    fun getAllBrand():List<ResBrand>
    fun getBrandByCodeBrand(request: ReqBrand): ResBrand
}