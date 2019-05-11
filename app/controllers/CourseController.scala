package controllers

import javax.inject.Inject
import play.api.mvc._
import tables.Courses
import utils.WritableImplicits._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{ExecutionContext, Future}

class CourseController @Inject()(cc: ControllerComponents)
  extends AbstractController(cc) {

  def getAll = Action.async(
    Courses.all.map(Ok(_))
  )

}

