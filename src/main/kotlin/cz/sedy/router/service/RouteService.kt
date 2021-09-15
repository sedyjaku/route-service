package cz.sedy.router.service

import cz.sedy.router.model.domain.Route
import kotlinx.coroutines.flow.Flow

interface RouteService {

    suspend fun findRoute(origin: String, destination: String): Route
}