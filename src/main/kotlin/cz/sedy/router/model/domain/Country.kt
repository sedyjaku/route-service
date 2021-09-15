package cz.sedy.router.model.domain

data class Country (
    val code: String,
    val latitude: Number,
    val longitude: Number,
    val borders: List<String>
)