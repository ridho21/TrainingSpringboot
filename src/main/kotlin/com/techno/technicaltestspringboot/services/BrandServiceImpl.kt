package com.techno.technicaltestspringboot.services

import com.techno.technicaltestspringboot.dto.request.ReqBrand
import com.techno.technicaltestspringboot.dto.response.ResBrand
import com.techno.technicaltestspringboot.entity.BrandEntity
import com.techno.technicaltestspringboot.repository.BrandRepository
import com.techno.technicaltestspringboot.util.TokenGenerator
import org.springframework.stereotype.Service

@Service
class BrandServiceImpl(
    val brandRepository: BrandRepository
) : BrandService{
    override fun getAllBrand(): List<ResBrand> {
        val brandEntities = brandRepository.findAll()
        val result = mutableListOf<ResBrand>()
        for(r in brandEntities){
            result.add(ResBrand(r.cd_brand, r.desc_brand))
        }
        return result
    }

    override fun getBrandByCodeBrand(request: ReqBrand): ResBrand {
        val brandEntities = brandRepository.findByCodeBrand(request.desc_brand)
       return ResBrand(
            brandEntities!!.cd_brand,
           brandEntities.desc_brand
       )
    }
}