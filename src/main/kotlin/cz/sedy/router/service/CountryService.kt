package cz.sedy.router.service

import cz.sedy.router.model.domain.Country
import kotlinx.coroutines.flow.Flow

interface CountryService {

    suspend fun getAllCountries(): Flow<Country>
}