package com.techno.technicaltestspringboot.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class PathMatchingConfigurationAdapter (
    val requestInterceptor: RequestInterceptor,
    val loginInterceptor: LoginInterceptor,
    val getBrandInterceptor: GetBrandInterceptor
    ): WebMvcConfigurer {

        override fun addInterceptors(registry: InterceptorRegistry) {
            registry.addInterceptor(requestInterceptor).addPathPatterns("/apiservice/oauth/token", "/apiservice/unit/getbrand")
            registry.addInterceptor(loginInterceptor).excludePathPatterns("/apiservice/unit/getbrand")
            registry.addInterceptor(getBrandInterceptor).addPathPatterns("/apiservice/unit/getbrand")
        }
}