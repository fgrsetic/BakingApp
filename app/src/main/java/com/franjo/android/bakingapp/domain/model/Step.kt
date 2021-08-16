package com.franjo.android.bakingapp.domain.model

data class Step(
    val id: Int,
    val shortDescription: String,
    val description: String,
    val videoURL: String,
    val thumbnailURL: String
)
