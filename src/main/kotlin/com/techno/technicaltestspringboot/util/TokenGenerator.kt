package com.techno.technicaltestspringboot.util

import com.techno.technicaltestspringboot.exception.CustomException
import io.jsonwebtoken.Claims
import io.jsonwebtoken.JwtBuilder
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.slf4j.LoggerFactory
import java.lang.Exception
import java.util.*
import javax.crypto.spec.SecretKeySpec
import javax.xml.bind.DatatypeConverter

class TokenGenerator {
    companion object {
        private const val SECRET_KEY = "SUPER_SECRETE"
        private val instance: TokenGenerator = TokenGenerator()
    }

    val log = LoggerFactory.getLogger(this::class.java)

    fun createJWT(id : String?, subject: String?): String {
        val signatureAlgorithm: SignatureAlgorithm = SignatureAlgorithm.HS256
        val nowMills: Long = System.currentTimeMillis()
        val now = Date(nowMills)

        val apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY)
        val signingKey = SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.jcaName)

        val builder: JwtBuilder = Jwts.builder().setId(id)
            .setIssuedAt(now)
            .setSubject(subject)
            .setIssuer("technocenter")
            .setAudience("technocenter")
            .signWith(signatureAlgorithm, signingKey)

        val expMills = nowMills + 10000L
        val exp = Date(expMills)
        builder.setExpiration(exp)

        return builder.compact()
    }

    fun decodeJWT(jwt: String): Claims {
        try {
            val claims: Claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                .parseClaimsJws(jwt).body

            log.info("ID : ${claims.id}")
            log.info("Issuer : ${claims.issuer}")
            log.info("Subject : ${claims.subject}")
            return claims
        }
        catch (e: Exception){
            throw CustomException(e.message.toString())
        }

    }
}