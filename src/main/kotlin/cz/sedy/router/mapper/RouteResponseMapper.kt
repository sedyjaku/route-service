package cz.sedy.router.mapper

import cz.sedy.router.model.controller.response.RouteResponse
import cz.sedy.router.model.domain.Route

interface RouteResponseMapper {
    fun createFromRoute(domain: Route): RouteResponse
}