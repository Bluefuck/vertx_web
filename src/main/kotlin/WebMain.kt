package vertx.core

import com.sun.xml.internal.fastinfoset.util.StringArray
import io.vertx.core.AbstractVerticle
import io.vertx.core.Vertx
import io.vertx.core.http.HttpHeaders
import io.vertx.core.http.HttpMethod
import io.vertx.ext.web.handler.CorsHandler

class WebMain:AbstractVerticle() {
    companion object {
        @JvmStatic fun main(array: Array<String>){
            Vertx.vertx().deployVerticle(WebMain())
        }
    }

    override fun start() {
        var server = Vertx.vertx().createHttpServer()
        var array = mutableListOf<String>()
        XSpringContext.initSpring(array.toTypedArray())
        var route = HandlerScanner.router
        //扫描kt文件
        HandlerScanner.scan()
        server.requestHandler({route.accept(it)}).listen(8080)
    }
}