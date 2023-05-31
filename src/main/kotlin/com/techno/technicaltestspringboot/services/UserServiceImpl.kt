package com.techno.technicaltestspringboot.services

import com.techno.technicaltestspringboot.dto.response.ResLogin
import com.techno.technicaltestspringboot.entity.TypeEntity
import com.techno.technicaltestspringboot.exception.CustomException
import com.techno.technicaltestspringboot.repository.TypeRepository
import com.techno.technicaltestspringboot.repository.UserRepository
import com.techno.technicaltestspringboot.util.TokenGenerator
import org.springframework.stereotype.Service
import javax.servlet.http.HttpServletResponse

@Service
class UserServiceImpl(
    val typeRepository: TypeRepository,
    val userRepository: UserRepository
) : UserService {
    override fun findType(type: String?): ResLogin {
        try {
            val type = typeRepository.findByType(type)
            return ResLogin(id = type!!.id, type = type.type, access_token = type.access_token, expires_in = type.expires_in, token_type = type.token_type)
        } catch (e: Exception) {
            throw CustomException(e.message.toString())
        }

    }

//    override fun loginUser(username: String?, password: String?): ResLogin {
//        try {
//            val user = userRepository.findByUserPass(username, password)
//            return ResLogin(id = user!!.id, username = user.username, password = user.password)
//        } catch (e: Exception) {
//            throw CustomException("User tidak ditemukan")
//        }
//
//    }

    override fun validateUser(token: String?): Boolean {
        try {
            val claim = TokenGenerator().decodeJWT(token!!)
            val typeEntities = typeRepository.findByType(claim.id.toString())
            return typeEntities != null
        } catch (e: Exception) {
            throw CustomException(e.message.toString())
        }

    }

    override fun saveTokenData(type: String, access_token: String, expires_in: Long, token_type: String) {
        val typeEntity =
            TypeEntity(type = type, access_token = access_token, expires_in = expires_in, token_type = token_type)
        typeRepository.save(typeEntity)
    }

    override fun updateTokenData(type: String, access_token: String, expires_in: Long, token_type: String) {
        val typeEntity = typeRepository.findByType(type)
        val newEntity = typeEntity!!.copy(
            type = type,
            access_token = access_token,
            expires_in = expires_in,
            token_type = token_type
        )
        typeRepository.save(newEntity)

    }

}