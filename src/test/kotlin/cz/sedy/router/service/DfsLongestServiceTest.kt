package cz.sedy.router.service

import com.fasterxml.jackson.databind.ObjectMapper
import cz.sedy.router.mapper.impl.DefaultCountryMapper
import cz.sedy.router.service.impl.DfsLongestExpansionService
import cz.sedy.router.service.impl.DfsRouteService
import cz.sedy.router.service.impl.DfsShortestExpansionService
import cz.sedy.router.service.impl.FileCountryService
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class DfsLongestServiceTest {

    lateinit var routeService: RouteService

    @Autowired
    lateinit var countryService: CountryService

    @BeforeEach
    fun setUp(){
        routeService = DfsRouteService(countryService, DfsLongestExpansionService())
    }

    @Test
    fun shouldReturnShortestPath_WhenRouteExists(){
        val route = runBlocking { (routeService.findRoute("CZE", "ITA"))}
        Assertions.assertThat(route.route.size).isEqualTo(87)
    }

}