package cz.sedy.router.service.impl

import cz.sedy.router.exception.NoPathException
import cz.sedy.router.model.domain.Country
import cz.sedy.router.model.domain.Route
import cz.sedy.router.service.CountryService
import cz.sedy.router.service.RouteService
import kotlinx.coroutines.flow.toSet
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Service
import kotlin.collections.ArrayDeque

@Service
@Primary
@ConditionalOnProperty(name = ["strategy"], havingValue = "bfs")
class BfsRouteService(
    private val countryService: CountryService
) : RouteService {

    override suspend fun findRoute(origin: String, destination: String): Route {
        val countryMap = countryService.getAllCountries()
            .toSet()
            .associateByTo(mutableMapOf()) { country -> country.code }

        val expandedCountries = mutableSetOf<String>()
        val queue = ArrayDeque(expandBorders(listOf(origin), countryMap, expandedCountries))

        while (queue.isNotEmpty()) {
            val currentRoute = queue.removeFirst()
            if (currentRoute.last() == (destination)) {
                return Route(currentRoute)
            }
            queue.addAll(expandBorders(currentRoute, countryMap, expandedCountries))
        }
        throw NoPathException.of(origin, destination)
    }


    fun expandBorders(route: List<String>, countryMap: MutableMap<String, Country>, expandedCountries: MutableSet<String>) =
        countryMap.remove(route.last())
            ?.borders
            ?.asSequence()
            ?.filter(countryMap::containsKey)
            ?.filterNot(expandedCountries::contains)
            ?.mapNotNull(countryMap::get)
            ?.map(Country::code)
            ?.onEach(expandedCountries::add)
            ?.map { expandedCode -> route.toMutableList().plus(expandedCode) }
            ?.toSet() ?: emptySet()
}


