package vertx.core

import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext

interface IHandler {

    val name:String

    fun bootLoad(router: Router)

    fun root(router: Router,init:XRouter.(router:Router) ->Unit){
        var boot =XRouter(router)
        boot.init(router)
    }
}

    class XRouter(val rooter: Router) {

        fun get(url: String, init: (context: RoutingContext) -> Unit) {
            rooter.get(url).handler(init)
        }

        fun post(url: String, init: (context: RoutingContext) -> Unit) {
            rooter.post(url).handler(init)
        }

        fun put(url: String, init: (context: RoutingContext) -> Unit) {
            rooter.put(url).handler(init)
        }

        fun delete(url: String, init: (context: RoutingContext) -> Unit) {
            rooter.delete(url).handler(init)
        }

        fun block(url: String, init: (context: RoutingContext) -> Unit) {
            rooter.route(url).blockingHandler(init)
        }

        fun fail(url: String, init: (context: RoutingContext) -> Unit) {
            rooter.route(url).failureHandler(init)
        }
}