package cz.sedy.router.mapper.impl

import cz.sedy.router.mapper.CountryMapper
import cz.sedy.router.model.client.CountryResponse
import cz.sedy.router.model.domain.Country
import org.springframework.stereotype.Component

@Component
class DefaultCountryMapper: CountryMapper {

    override fun createFromCountryResponse(countryResponse: CountryResponse) =
        Country(
            code = countryResponse.cca3,
            latitude = countryResponse.latlng[0],
            longitude = countryResponse.latlng[1],
            borders = countryResponse.borders
        )

}