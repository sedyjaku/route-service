package cz.sedy.router.mapper

import cz.sedy.router.model.client.CountryResponse
import cz.sedy.router.model.domain.Country

interface CountryMapper {

    fun createFromCountryResponse(countryResponse: CountryResponse): Country
}