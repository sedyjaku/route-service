package cz.sedy.router.service.impl

import cz.sedy.router.exception.NoPathException
import cz.sedy.router.model.domain.Route
import cz.sedy.router.service.CountryService
import cz.sedy.router.service.DfsExpansionService
import cz.sedy.router.service.RouteService
import kotlinx.coroutines.flow.toSet
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Service

@Service
@ConditionalOnProperty(name = ["strategy"], havingValue = "dfs")
class DfsRouteService(
    private val countryService: CountryService,
    private val dfsExpansionService: DfsExpansionService
): RouteService {

    override suspend fun findRoute(origin: String, destination: String): Route {
        val countryMap = countryService.getAllCountries()
            .toSet()
            .associateByTo(mutableMapOf()) { country -> country.code }

        val destinationCountry = countryMap[destination] ?: throw NoPathException.of(origin, destination)
        val queue = ArrayDeque(dfsExpansionService.expandBorders(listOf(origin), countryMap, destinationCountry))

        while (queue.isNotEmpty()) {
            val currentRoute = queue.removeLast()
            if (currentRoute.last() == (destination)) {
                return Route(currentRoute)
            }
            queue.addAll(dfsExpansionService.expandBorders(currentRoute, countryMap, destinationCountry))
        }
        throw NoPathException.of(origin, destination)
    }


}