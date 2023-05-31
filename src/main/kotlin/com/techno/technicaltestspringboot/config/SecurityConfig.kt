package com.techno.technicaltestspringboot.config


import com.techno.technicaltestspringboot.exception.CustomException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.User
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.AuthenticationEntryPoint
import java.util.*
import javax.servlet.http.HttpServletRequest

@Configuration
@EnableWebSecurity
class SecurityConfig: WebSecurityConfigurerAdapter() {

    @Autowired
    private lateinit var authenticationEntryPoint: AuthenticationEntryPoint
    override fun configure(auth: AuthenticationManagerBuilder) {
        // Configure authentication provider(s)
        auth.inMemoryAuthentication()
            .withUser("admin")
            .password("{noop}admin") // Use {noop} prefix for plaintext password (not recommended for production)
            .roles("ADMIN")

    }


    override fun configure(http: HttpSecurity) {

            http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .httpBasic().authenticationEntryPoint(authenticationEntryPoint)
                .and()
                .csrf().disable()



    }
}