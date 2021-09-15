package cz.sedy.router.handler

import cz.sedy.router.constant.DESTINATION_PATH_VARIABLE
import cz.sedy.router.constant.ORIGIN_PATH_VARIABLE
import cz.sedy.router.mapper.RouteResponseMapper
import cz.sedy.router.service.RouteService
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import org.springframework.web.reactive.function.server.json

@Component
class RouteHandler(
    private val routeService: RouteService,
    private val routeResponseMapper: RouteResponseMapper
) {

    suspend fun getRoute(request: ServerRequest) =
        ServerResponse.ok().json().bodyValueAndAwait(
            routeResponseMapper.createFromRoute(
                routeService.findRoute(
                    request.pathVariable(ORIGIN_PATH_VARIABLE),
                    request.pathVariable(DESTINATION_PATH_VARIABLE)
                )
            )
        )

}