package vertx.handler

import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext
import org.springframework.stereotype.Component
import vertx.core.IHandler

@Component("test-handler")
class testhandler: IHandler {
    override val name: String
        get() = "test-handler"

    override fun bootLoad(router: Router) {
        root(router){
            get("/test"){context: RoutingContext ->
                context.response().end("test")
            }
        }
    }
}