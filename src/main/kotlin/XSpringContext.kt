package vertx.core

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.context.EnvironmentAware
import org.springframework.core.env.Environment

@SpringBootApplication(scanBasePackages = arrayOf("vertx.handler"))
open class XSpringContext: ApplicationContextAware, EnvironmentAware
{
    override fun setApplicationContext(applicationContext: ApplicationContext?) {
        context = applicationContext ?: throw IllegalArgumentException("spring容器上下文不能为null")
    }

    override fun setEnvironment(env: Environment?)
    {
        environment = env ?:  throw IllegalArgumentException("spring容器上下文环境对象不能为null")
    }

    companion object {

        private lateinit var context: ApplicationContext

        private lateinit var environment:Environment

        val applicationContext: ApplicationContext by lazy {
            context
        }

        val properties: Environment by lazy {
            environment
        }

        @JvmStatic
        fun initSpring(args: Array<String>) {
            SpringApplication.run(XSpringContext::class.java, *args)
        }

    }
}