package com.example.rustore

class Repository {

    private val commonScreens = listOf(
        R.drawable.scrin_1,
        R.drawable.scrin_2,
        R.drawable.scrin_3
    )

    val categories = listOf(
        Category(CategoryId.FINANCE, "Финансы"),
        Category(CategoryId.TOOLS, "Инструменты"),
        Category(CategoryId.GAMES, "Игры"),
        Category(CategoryId.GOVERNMENT, "Государственные"),
        Category(CategoryId.TRANSPORT, "Транспорт")
    )

    private val items = listOf(
        AppItem(
            id = "app1",
            name = "Оплата Онлайн",
            company = "FinTech LLC",
            category = CategoryId.FINANCE,
            ageRating = "12+",
            shortDescription = "Платежи и переводы.",
            fullDescription = "Удобное приложение для платежей, переводов и управления картами.",
            iconRes = R.drawable.logo,
            screenshots = commonScreens
        ),
        AppItem(
            id = "app2",
            name = "Инструменты+",
            company = "Toolbox Inc.",
            category = CategoryId.TOOLS,
            ageRating = "6+",
            shortDescription = "Набор полезных утилит.",
            fullDescription = "Сканер документов, QR, заметки и конвертеры в одном месте.",
            iconRes = R.drawable.logo,
            screenshots = commonScreens
        ),
        AppItem(
            id = "app3",
            name = "Гоночки 3D",
            company = "Fun Games",
            category = CategoryId.GAMES,
            ageRating = "6+",
            shortDescription = "Аркадные гонки.",
            fullDescription = "Соревнуйся с друзьями и улучшай автомобили.",
            iconRes = R.drawable.logo,
            screenshots = commonScreens
        ),
        AppItem(
            id = "app4",
            name = "Услуги+",
            company = "ГосПортал",
            category = CategoryId.GOVERNMENT,
            ageRating = "0+",
            shortDescription = "Услуги и документы.",
            fullDescription = "Запись к врачу, налоги, штрафы и другие сервисы.",
            iconRes = R.drawable.logo,
            screenshots = commonScreens
        ),
        AppItem(
            id = "app5",
            name = "Транспорт",
            company = "City Mobility",
            category = CategoryId.TRANSPORT,
            ageRating = "0+",
            shortDescription = "Маршруты и билеты.",
            fullDescription = "Планирование поездок, расписание и оплата проезда.",
            iconRes = R.drawable.logo,
            screenshots = commonScreens
        )
    )

    fun getAll(category: CategoryId? = null): List<AppItem> =
        if (category == null) items else items.filter { it.category == category }

    fun getById(id: String): AppItem? = items.find { it.id == id }
}
