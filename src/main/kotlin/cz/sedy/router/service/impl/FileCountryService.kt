package cz.sedy.router.service.impl

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import cz.sedy.router.mapper.CountryMapper
import cz.sedy.router.model.client.CountryListReference
import cz.sedy.router.model.client.CountryResponse
import cz.sedy.router.model.domain.Country
import cz.sedy.router.service.CountryService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map
import org.springframework.context.annotation.Profile
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Component

@Component
@Profile("test")
class FileCountryService(
    private val objectMapper: ObjectMapper,
    private val countryMapper: CountryMapper
) : CountryService {
    override suspend fun getAllCountries(): Flow<Country> =
        objectMapper.readValue(ClassPathResource("countries.json").file, CountryListReference()).asFlow()
            .map(countryMapper::createFromCountryResponse)
}
