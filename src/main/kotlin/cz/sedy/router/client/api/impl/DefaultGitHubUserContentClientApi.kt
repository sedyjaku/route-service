package cz.sedy.router.client.api.impl

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import cz.sedy.router.client.api.GITHUB_COUNTRIES_URL
import cz.sedy.router.client.api.GitHubUserContentClientApi
import cz.sedy.router.model.client.CountryResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.ClientResponse
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToFlow
import org.springframework.web.reactive.function.client.exchangeToFlow
import java.net.URL

@Component
class DefaultGitHubUserContentClientApi(
    private val objectMapper: ObjectMapper
) : GitHubUserContentClientApi {

    override suspend fun getAllCountries() =
            objectMapper.readValue(URL(GITHUB_COUNTRIES_URL), customReference()).asFlow()

}
    class customReference: TypeReference<List<CountryResponse>>()
