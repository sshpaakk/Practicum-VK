package com.example.rustore

fun categoryTitle(id: CategoryId): String = when (id) {
    CategoryId.FINANCE     -> "Финансы"
    CategoryId.TOOLS       -> "Инструменты"
    CategoryId.GAMES       -> "Игры"
    CategoryId.GOVERNMENT  -> "Государственные"
    CategoryId.TRANSPORT   -> "Транспорт"
}
