package cz.sedy.router.client.api

import cz.sedy.router.model.client.CountryResponse
import kotlinx.coroutines.flow.Flow

const val GITHUB_USER_CONTENT_URL = "https://raw.githubusercontent.com"
const val GITHUB_COUNTRIES_URL = "$GITHUB_USER_CONTENT_URL/mledoze/countries/master/countries.json"

interface GitHubUserContentClientApi {

    suspend fun getAllCountries(): Flow<CountryResponse>
}