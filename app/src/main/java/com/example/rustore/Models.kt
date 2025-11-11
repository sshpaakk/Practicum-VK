package com.example.rustore

enum class CategoryId { FINANCE, TOOLS, GAMES, GOVERNMENT, TRANSPORT }

data class Category(
    val id: CategoryId,
    val title: String
)

data class AppItem(
    val id: String,
    val name: String,
    val company: String,
    val category: CategoryId,
    val ageRating: String,
    val shortDescription: String,
    val fullDescription: String,
    val iconRes: Int? = null,
    val iconUrl: String? = null,
    val screenshots: List<Int> = emptyList(),
    val apkUrl: String? = null
)
