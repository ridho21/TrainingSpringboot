package com.techno.technicaltestspringboot.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.techno.technicaltestspringboot.exception.CustomException
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class CustomAuthenticationEntryPoint: AuthenticationEntryPoint {
    override fun commence(request: HttpServletRequest, response: HttpServletResponse,
                          authException: AuthenticationException
    ) {

            val result = mapOf<String, String>("OUT_STAT" to "F", "OUT_MESS" to "Client Invalid")
            response.writer.write(convertObjectToJson(result))
            response.contentType = "application/json"
            response.characterEncoding = "UTF-8"
            response.status = 401
//        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized")


    }
    fun convertObjectToJson(dto: Map<String, String>): String {
        return ObjectMapper().writeValueAsString(dto)
    }
}