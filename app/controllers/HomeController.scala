package controllers

import javax.inject._

import play.api.mvc._

/**
  * Entry point into the application.
  */
@Singleton
class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }

}
