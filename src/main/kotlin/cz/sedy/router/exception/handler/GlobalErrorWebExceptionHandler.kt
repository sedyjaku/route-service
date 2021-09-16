package cz.sedy.router.exception.handler

import org.springframework.boot.autoconfigure.web.WebProperties
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler
import org.springframework.boot.web.error.ErrorAttributeOptions
import org.springframework.boot.web.reactive.error.ErrorAttributes
import org.springframework.context.ApplicationContext
import org.springframework.core.annotation.Order
import org.springframework.http.MediaType
import org.springframework.http.codec.ServerCodecConfigurer
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.RequestPredicates
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse

@Component
@Order(-2)
class GlobalErrorWebExceptionHandler(
    errorAttributes: ErrorAttributes?,
    resources: WebProperties.Resources?,
    applicationContext: ApplicationContext?,
    serverCodecConfigurer: ServerCodecConfigurer
) : AbstractErrorWebExceptionHandler(errorAttributes, resources, applicationContext) {

    init {
        super.setMessageReaders(serverCodecConfigurer.readers)
        super.setMessageWriters(serverCodecConfigurer.writers)
    }

    override fun getRoutingFunction(errorAttributes: ErrorAttributes?) =
        RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse)


    fun renderErrorResponse(request: ServerRequest) = ServerResponse.badRequest()
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromValue(getErrorAttributes(request, ErrorAttributeOptions.defaults().including(
            ErrorAttributeOptions.Include.MESSAGE,
            ErrorAttributeOptions.Include.EXCEPTION))))

}