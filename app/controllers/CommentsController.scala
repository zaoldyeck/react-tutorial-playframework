package controllers

import models.Comment
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import play.api.data._
import play.api.data.Forms._

/**
 * Created by zaoldyeck on 15/3/29.
 */
object CommentsController extends Controller {

  implicit val commentFormat = Json.format[Comment]

  def index = Action {
    Ok(Json.toJson(Comment.all))
  }

  def create = Action { implicit request =>

    Form(tuple(
      "author" -> text,
      "text" -> text)).bindFromRequest.fold(
        formWithErrors => BadRequest,
        comment => {
          Comment.create(comment._1, comment._2)
          Ok(Json.toJson(Comment.all))
        }
      )
  }
}