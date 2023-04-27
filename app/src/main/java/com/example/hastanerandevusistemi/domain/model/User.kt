package com.example.hastanerandevusistemi.domain.model

import com.example.hastanerandevusistemi.data.local.entities.UserEntity

data class User(
    var id: Int,
    var name: String,
    var surname: String,
    var tc: Int,
    var email: String,
    var password: String
) : java.io.Serializable {
    fun toUserEntity(): UserEntity {
        return UserEntity(
            name = name,
            surname = surname,
            tc = tc,
            email = email,
            password = password
        )
    }
}
