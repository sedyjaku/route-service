package cz.sedy.router.service

import cz.sedy.router.service.impl.DfsLongestExpansionService
import cz.sedy.router.service.impl.DfsRouteService
import cz.sedy.router.service.impl.DfsShortestExpansionService
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class DfsShortestServiceTest {
    lateinit var routeService: RouteService

    @Autowired
    lateinit var countryService: CountryService

    @BeforeEach
    fun setUp(){
        routeService = DfsRouteService(countryService, DfsShortestExpansionService())
    }

    @Test
    fun shouldReturnShortestPath_WhenRouteExists(){

        val route = runBlocking { (routeService.findRoute("CZE", "ITA"))}
        Assertions.assertThat(route.route.size).isEqualTo(3)
        Assertions.assertThat(route.route[0]).isEqualTo("CZE")
        Assertions.assertThat(route.route[1]).isEqualTo("AUT")
        Assertions.assertThat(route.route[2]).isEqualTo("ITA")
    }
}