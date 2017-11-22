package controllers

import java.io.File
import javax.inject.{ Inject, Singleton }

import play.api.mvc.{ AbstractController, Action, ControllerComponents }

@Singleton
class StaticFile @Inject() (cc: ControllerComponents) extends AbstractController(cc) {

  def html(file: String) = Action {
    var f = new File(file)

    if (f.exists())
      Ok(scala.io.Source.fromFile(f.getCanonicalPath()).mkString).as("text/html")
    else
      NotFound
  }

}
