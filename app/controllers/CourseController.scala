package controllers

import javax.inject.Inject
import play.api.mvc._
import tables.Courses
import models.Course._
import play.api.libs.json._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{ExecutionContext, Future}

class CourseController @Inject()(cc: ControllerComponents)
  extends AbstractController(cc) {

  def getAll = Action.async(
    Courses.all.map(x => Ok(Json.toJson(x)))
  )

}

