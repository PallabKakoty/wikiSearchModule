package controllers

import com.hhandoko.play.pdf.PdfGenerator
import javax.inject._
import play.api.mvc._
import globals.Configurations
import play.api.libs.ws.{WSClient}
import play.twirl.api.Html
import scala.concurrent.ExecutionContext.Implicits.global

@Singleton
class HomeController @Inject()(configurations: Configurations,pdfGen: PdfGenerator, wSClient: WSClient) extends Controller {

  implicit val logAddress = "controllers.HomeController"

  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index("Hello"))
  }

  def wsAction = Action.async { request =>
    val qStr = request.getQueryString("title").getOrElse("").replaceAll("%20", " ")
    val url ="https://en.wikipedia.org/wiki/"+qStr;
    wSClient.url(url).get().map { response =>
      pdfGen.ok(views.html.wikipdf(Html.apply(response.body)), configurations.app.hostname)
      //Ok(views.html.wikipdf(Html.apply(response.body)))
    }
  }
}
