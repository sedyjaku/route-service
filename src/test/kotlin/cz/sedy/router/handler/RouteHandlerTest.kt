package cz.sedy.router.handler

import cz.sedy.router.SpringTestParent
import cz.sedy.router.constant.DESTINATION_PATH_VARIABLE
import cz.sedy.router.constant.ORIGIN_PATH_VARIABLE
import cz.sedy.router.constant.ROUTING_ENDPOINT
import cz.sedy.router.model.controller.response.RouteResponse
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.reactive.server.WebTestClient


class RouteHandlerTest: SpringTestParent() {

    @Test
    fun shouldReturnShortestRoute_WhenRouteExists(){
        val response = webTestClient
            .get()
            .uri("$ROUTING_ENDPOINT/{$ORIGIN_PATH_VARIABLE}/{$DESTINATION_PATH_VARIABLE}", "CZE", "ITA")
            .accept(APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk
            .expectBody(RouteResponse::class.java)
            .returnResult()

        val routeResponse = response.responseBody as RouteResponse

        Assertions.assertThat(routeResponse.route.size).isEqualTo(3)
        Assertions.assertThat(routeResponse.route[0]).isEqualTo("CZE")
        Assertions.assertThat(routeResponse.route[1]).isEqualTo("AUT")
        Assertions.assertThat(routeResponse.route[2]).isEqualTo("ITA")
    }

    @Test
    fun shouldReturnBadRequest_WhenRouteDoesNotExist(){ webTestClient
        .get()
        .uri("$ROUTING_ENDPOINT/{$ORIGIN_PATH_VARIABLE}/{$DESTINATION_PATH_VARIABLE}", "CZE", "ABW")
        .accept(APPLICATION_JSON)
        .exchange()
        .expectStatus().isBadRequest
    }

    @Test
    fun shouldReturnBadRequest_WithInvalidOrigin(){ webTestClient
        .get()
        .uri("$ROUTING_ENDPOINT/{$ORIGIN_PATH_VARIABLE}/{$DESTINATION_PATH_VARIABLE}", "AAA", "ITA")
        .accept(APPLICATION_JSON)
        .exchange()
        .expectStatus().isBadRequest
    }

    @Test
    fun shouldReturnBadRequest_WithInvalidDestination(){ webTestClient
        .get()
        .uri("$ROUTING_ENDPOINT/{$ORIGIN_PATH_VARIABLE}/{$DESTINATION_PATH_VARIABLE}", "CZE", "AAA")
        .accept(APPLICATION_JSON)
        .exchange()
        .expectStatus().isBadRequest
    }
}