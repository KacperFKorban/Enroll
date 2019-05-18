package controllers

import javax.inject.Inject
import play.api.mvc._
import tables.{Students, StudentsToCourses}
import utils.WritableImplicits._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{ExecutionContext, Future}

class StudentController @Inject()(cc: ControllerComponents)
extends AbstractController(cc) {

  def getAll = Action.async(
    Students.all.map(Ok(_))
  )

  def getAllWithCourses = Action.async(
    StudentsToCourses.all.map(Ok(_))
  )

  def getForId(id: Int) = Action.async(
    StudentsToCourses.forId(id).map(Ok(_))
  )

}
