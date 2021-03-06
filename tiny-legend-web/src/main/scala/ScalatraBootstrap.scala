import com.tinylegend.app._
import org.scalatra._
import javax.servlet.ServletContext

import com.tinylegend.app.api.products.ProductsServlet
import org.scalatra.metrics.MetricsBootstrap
import org.scalatra.metrics.MetricsSupportExtensions._

class ScalatraBootstrap extends LifeCycle with MetricsBootstrap {

  //override val metricRegistry = ???
  //override val healthCheckRegistry = ???

  override def init(context: ServletContext) {

    context.mountMetricsAdminServlet("/metrics-admin")
    context.mountHealthCheckServlet("/health")
    context.mountMetricsServlet("/metrics")
    context.mountThreadDumpServlet("/thread-dump")
    context.installInstrumentedFilter("/test/*")

    context.mount(new LegendServlet, "/*")

    context.mount(new ProductsServlet(), "/api/products")
  }
}
