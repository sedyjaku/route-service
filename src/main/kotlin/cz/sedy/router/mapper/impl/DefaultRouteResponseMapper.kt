package cz.sedy.router.mapper.impl

import cz.sedy.router.mapper.RouteResponseMapper
import cz.sedy.router.model.controller.response.RouteResponse
import cz.sedy.router.model.domain.Route
import org.springframework.stereotype.Component

@Component
class DefaultRouteResponseMapper : RouteResponseMapper {

    override fun createFromRoute(domain: Route) = RouteResponse(
        route = domain.route
    )
}