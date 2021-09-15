package cz.sedy.router.service.impl

import cz.sedy.router.model.domain.Route
import cz.sedy.router.service.CountryService
import cz.sedy.router.service.RouteService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import org.springframework.stereotype.Service

@Service
class SimpleRouteService(
    private val countryService: CountryService
    ): RouteService {

    override suspend fun findRoute(origin: String, destination: String): Route {
        val routePath = countryService.getAllCountries().map { it.code }.toList()
        return Route(routePath)
    }
    }


