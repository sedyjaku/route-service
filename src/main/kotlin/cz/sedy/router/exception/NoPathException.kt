package cz.sedy.router.exception

import java.lang.Exception

class NoPathException(message: String = "No path found"): Exception(message) {

    companion object  {
        fun of(origin: String, destination: String): NoPathException {
            return NoPathException("There is no valid path between $origin and $destination")
        }
    }
}