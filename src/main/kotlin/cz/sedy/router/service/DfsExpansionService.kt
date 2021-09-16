package cz.sedy.router.service

import cz.sedy.router.model.domain.Country
import kotlin.math.pow
import kotlin.math.sqrt

interface DfsExpansionService {

    fun expandBorders(
        route: List<String>,
        countryMap: MutableMap<String, Country>,
        destinationCountry: Country
    ): Set<List<String>>

    fun Country.distance(to: Country) =
        sqrt(
            (this.latitude.toDouble() - to.latitude.toDouble()).pow(2) + (this.longitude.toDouble() - to.longitude.toDouble()).pow(
                2
            )
        )
}