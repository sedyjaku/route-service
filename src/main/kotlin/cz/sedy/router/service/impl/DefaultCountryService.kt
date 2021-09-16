package cz.sedy.router.service.impl

import cz.sedy.router.client.api.impl.DefaultGitHubUserContentClientApi
import cz.sedy.router.mapper.CountryMapper
import cz.sedy.router.model.domain.Country
import cz.sedy.router.service.CountryService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service

@Service
@Profile("!test")
class DefaultCountryService(
    private val gitHubUserContentClientApi: DefaultGitHubUserContentClientApi,
    private val countryMapper: CountryMapper
    ): CountryService{

    override suspend fun getAllCountries(): Flow<Country> =
        gitHubUserContentClientApi.getAllCountries().map (countryMapper::createFromCountryResponse)

}