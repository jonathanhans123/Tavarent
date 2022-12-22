package com.example.taverent

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey
    var id: Int,
    var username: String,
    var password: String,
    var email: String,
    var nama_lengkap:  String
){

}
