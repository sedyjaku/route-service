package cz.sedy.router.service

import cz.sedy.router.service.impl.BfsRouteService
import cz.sedy.router.service.impl.DfsLongestExpansionService
import cz.sedy.router.service.impl.DfsRouteService
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class BfsServiceTest {

    lateinit var routeService: RouteService

    @Autowired
    lateinit var countryService: CountryService

    @BeforeEach
    fun setUp(){
        routeService = BfsRouteService(countryService)
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