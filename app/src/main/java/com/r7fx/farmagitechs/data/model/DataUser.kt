package com.r7fx.farmagitechs.data.model


import com.google.gson.annotations.SerializedName

data class DataUser(
    @SerializedName("user_id")
    val userId: Int,
    @SerializedName("username")
    val username: String,
    @SerializedName("fullname")
    val fullname: String,
    @SerializedName("group_id")
    val groupId: Int,
    @SerializedName("group_name")
    val groupName: String,
    @SerializedName("active")
    val active: String
)