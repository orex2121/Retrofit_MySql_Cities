package ru.jandroid.mysql_cities

data class CountryRequest(
    val error: Boolean,
    val countries: List<Country>
)

data class Country(
    val country_id: Int,
    val title_ru: String
)