package cz.sedy.router.model.client

data class CountryResponse (
    val cca3: String,
    val latlng: List<Number>,
    val borders: List<String>
)