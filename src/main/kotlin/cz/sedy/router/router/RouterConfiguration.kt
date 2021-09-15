package cz.sedy.router.router

import cz.sedy.router.constant.DESTINATION_PATH_VARIABLE
import cz.sedy.router.constant.ORIGIN_PATH_VARIABLE
import cz.sedy.router.constant.ROUTING_ENDPOINT
import cz.sedy.router.handler.RouteHandler
import kotlinx.coroutines.FlowPreview
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class RouterConfiguration {

    @FlowPreview
    @Bean
    fun activityPrototypeRoutes(handler: RouteHandler) = coRouter {
        ROUTING_ENDPOINT.nest {
            GET("/{$ORIGIN_PATH_VARIABLE}/{$DESTINATION_PATH_VARIABLE}", handler::getRoute)
        }
    }

}