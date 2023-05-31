package com.techno.technicaltestspringboot.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class GetBrandInterceptor : HandlerInterceptor {
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val xContentTypeOptions = request.getHeader("X-Content-Type-Options")
        val xssProtection = request.getHeader("X-XSS-Protection")
        val strictTransportSec = request.getHeader("Strict-Transport-Security")
        val frameOptions = request.getHeader("X-Frame-Options")
        if (xContentTypeOptions != "nosniff" || xssProtection != "1; mode=block" || strictTransportSec != "max-age=31536000;includeSubdomains;preload" || frameOptions != "SAMEORIGIN") {
            val result = mapOf<String, String>("status" to "F", "message" to "You dont have access to API")
            response.writer.write(convertObjectToJson(result))
            response.contentType = "application/json"
            response.characterEncoding = "UTF-8"
            response.status = 401
            return false
        }
        return true
    }

    override fun postHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        modelAndView: ModelAndView?,
    ) {

    }

    fun convertObjectToJson(dto: Map<String, String>): String {
        return ObjectMapper().writeValueAsString(dto)
    }
}