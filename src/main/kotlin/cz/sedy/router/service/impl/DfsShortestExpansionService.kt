package cz.sedy.router.service.impl

import cz.sedy.router.model.domain.Country
import cz.sedy.router.service.DfsExpansionService
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Service

@Service
@ConditionalOnProperty(prefix = "dfs", name = ["strategy"], havingValue = "shortest")
class DfsShortestExpansionService: DfsExpansionService {

    override fun expandBorders(
        route: List<String>,
        countryMap: MutableMap<String, Country>,
        destinationCountry: Country
    ) =
        countryMap.remove(route.last())
            ?.borders
            ?.asSequence()
            ?.filter(countryMap::containsKey)
            ?.filterNot(route::contains)
            ?.mapNotNull(countryMap::get)
            ?.sortedByDescending { country -> country.distance(destinationCountry) }
            ?.map(Country::code)
            ?.map { expandedCode -> route.toMutableList().plus(expandedCode) }
            ?.toSet() ?: emptySet()
}