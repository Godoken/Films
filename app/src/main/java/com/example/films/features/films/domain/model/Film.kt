package com.example.films.features.films.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Film (@field:PrimaryKey val id: Int,
            val localized_name: String,
            val name: String,
            val year: Int?,
            val rating: Double?,
            val image_url: String?,
            val description: String?)