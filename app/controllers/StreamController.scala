package controllers

import javax.inject.Inject
import javax.inject.Singleton

import models.ApiStream
import play.api.libs.json.Json
import play.api.mvc._

/**
 * This controller handles creating and describing streams.
 */
@Singleton
class StreamController @Inject() (cc: ControllerComponents)
    extends AbstractController(cc) {

  def get(id: String) = Action { implicit request: Request[AnyContent] =>
    // TODO: get the stream here!
    Ok(Json.toJson(ApiStream("retrievedStream", List.empty)))
  }

  def create() = Action { implicit request: Request[AnyContent] =>
    // TODO: create the stream here!
    Ok(Json.toJson(ApiStream("newstream", List.empty)))
  }

  def all() = Action { implicit request: Request[AnyContent] =>
    // TODO: list all streams!
    Ok(Json.toJson(List.empty[ApiStream]))
  }

}

