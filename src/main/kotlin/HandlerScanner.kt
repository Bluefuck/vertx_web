import io.vertx.core.Vertx
import io.vertx.ext.web.Router
import vertx.core.IHandler
import vertx.core.XSpringContext

object HandlerScanner {
    @JvmStatic
    val router: Router by lazy {
        Router.router(Vertx.currentContext().owner())
    }
    /*
    * 扫描vertx.handler目录下所有集成IHandler接口的类
    * 调用bootLoad方法传入router参数并构造路由
    * */
    @JvmStatic
    fun scan() {
        val handlers =  XSpringContext.applicationContext.getBeansOfType(IHandler::class.java)
        for ((_, handler) in handlers) {
            handler.bootLoad(router)
        }
    }
}