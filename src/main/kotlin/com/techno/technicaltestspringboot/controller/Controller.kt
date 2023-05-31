package com.techno.technicaltestspringboot.controller

import com.techno.technicaltestspringboot.dto.request.ReqBrand
import com.techno.technicaltestspringboot.dto.request.ReqBrandDetails
import com.techno.technicaltestspringboot.dto.response.BaseResponse
import com.techno.technicaltestspringboot.dto.response.ResBrand
import com.techno.technicaltestspringboot.dto.response.ResLogin
import com.techno.technicaltestspringboot.exception.CustomException
import com.techno.technicaltestspringboot.services.BrandService
import com.techno.technicaltestspringboot.services.UserService
import com.techno.technicaltestspringboot.util.TokenGenerator
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.validation.Valid


@RestController
@RequestMapping("/apiservice")
class Controller(
    val userService: UserService,
    val brandService: BrandService
) {
    @PostMapping("/oauth/token")
    fun encodeData(
        @RequestParam data: Map<String, String>,
        request: HttpServletRequest
    ): ResponseEntity<BaseResponse<Any>> {
        //        val headerValue = request.getHeader("Authorization").split(" ")
//        val decodedBytes = Base64.getDecoder().decode(headerValue[1])
//        val userString = String(decodedBytes).split(":")
//        val resultUser = userService.loginUser(userString[0], userString[1])
//        if (resultUser.id.toString().isEmpty()) {
//            return ResponseEntity.ok(
//                BaseResponse(
//                    outStatus = "F",
//                    outMessage = "User Tidak Ditemukan"
//                )
//            )
//        } else {
        val resultType = userService.findType(data["grant_type"])
        if (data["grant_type"] == resultType.type) {
            val nowMills: Long = System.currentTimeMillis() / 1000
            if (resultType.expires_in == null){
                val token = TokenGenerator().createJWT(resultType.type, resultType.id.toString())
                val claim = TokenGenerator().decodeJWT(token)
                val exp = claim.expiration.time/1000
                userService.updateTokenData(type = resultType.type!!, access_token = token, expires_in = exp, token_type = "Bearer")
                return ResponseEntity.ok(
                    BaseResponse(
                        outStatus = "T",
                        outMessage = "Berhasil",
                        outData = ResLogin(
                            id = resultType.id,
                            type = resultType.type,
                            access_token = token,
                            expires_in = exp,
                            token_type = resultType.token_type
                        )
                    )
                )
            }
            else if(nowMills > resultType.expires_in!!){
                val token = TokenGenerator().createJWT(resultType.type, resultType.id.toString())
                val claim = TokenGenerator().decodeJWT(token)
                val exp = claim.expiration.time/1000
                userService.updateTokenData(type = resultType.type!!, access_token = token, expires_in = exp, token_type = "Bearer")
                return ResponseEntity.ok(
                    BaseResponse(
                        outStatus = "T",
                        outMessage = "Berhasil",
                        outData = ResLogin(
                            id = resultType.id,
                            type = resultType.type,
                            access_token = token,
                            expires_in = exp,
                            token_type = resultType.token_type
                        )
                    )
                )
            }else {
                return ResponseEntity.ok(
                    BaseResponse(
                        outStatus = "T",
                        outMessage = "Berhasil",
                        outData = ResLogin(
                            id = resultType.id,
                            type = resultType.type,
                            access_token = resultType.access_token,
                            expires_in = resultType.expires_in,
                            token_type = resultType.token_type
                        )
                    )
                )
            }

        } else {
            return ResponseEntity.ok(BaseResponse("F", "Request tidak sesuai"))
        }

    }

    @PostMapping("/unit/getbrand")
    fun getBrand(@Valid @RequestBody request: ReqBrand, http: HttpServletRequest): ResponseEntity<BaseResponse<Any>> {
        val resultAll = brandService.getAllBrand()
        try {
            val headerValue = http.getHeader("Authorization").split(" ")
            val user = userService.validateUser(headerValue[1])
            if (user && headerValue[0] == "Bearer") {
                if (request.desc_brand == "") {
                    return ResponseEntity.ok(
                        BaseResponse(
                            outStatus = "T",
                            outMessage = "Success Get All",
                            outData = resultAll
                        )
                    )
                } else {
                    val result = brandService.getBrandByCodeBrand(request)
                    return ResponseEntity.ok(
                        BaseResponse(
                            outStatus = "T",
                            outMessage = "Success Get Data By Id",
                            outData = ResBrand(result.cd_brand, result.desc_brand)
                        )
                    )

                }
            } else {
                return ResponseEntity.ok(
                    BaseResponse(
                        outStatus = "F",
                        outMessage = "User Not Found!!"
                    )
                )
            }
        } catch (e: Exception) {
            throw CustomException(e.message.toString())
        }

    }
}