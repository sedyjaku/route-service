package cz.sedy.router.exception.handler

import org.springframework.boot.web.error.ErrorAttributeOptions
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest

@Component
@Order(1)
class GlobalErrorAttributes: DefaultErrorAttributes() {

    override fun getErrorAttributes(request: ServerRequest?, options: ErrorAttributeOptions?): MutableMap<String, Any> {
        val errorAttributes = super.getErrorAttributes(request, options)
        errorAttributes["status"] = HttpStatus.BAD_REQUEST
        errorAttributes["error"] = HttpStatus.BAD_REQUEST.reasonPhrase
        return errorAttributes
    }
}