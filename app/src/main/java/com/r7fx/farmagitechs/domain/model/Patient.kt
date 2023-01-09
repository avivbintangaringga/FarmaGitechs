package com.r7fx.farmagitechs.domain.model

import java.util.Date

data class Patient(
    val id: String,
    val name: String,
    val gender: Gender,
    val dateOfBirth: Date,
    val address: String,

)
