package cz.sedy.router.handler

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyAndAwait
import org.springframework.web.reactive.function.server.json

@Component
class RouteHandler {

    suspend fun getRoute(request: ServerRequest): ServerResponse =
        ServerResponse.ok().json().bodyAndAwait(flow {
            emit("ITA")
            emit("AU")
            emit("CZ")
        }
        )
}