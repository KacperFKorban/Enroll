package controllers

import javax.inject.Inject
import models.{Course, Student}
import play.api.libs.json._
import play.api.mvc._
import tables.{Students, StudentsToCourses}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{ExecutionContext, Future}

class StudentController @Inject()(cc: ControllerComponents)
extends AbstractController(cc) {

  def getAll = Action.async(
    Students.all.map(x => Ok(Json.toJson(x)))
  )

  def getAllWithCourses = Action.async(
    StudentsToCourses.all.map(x => Ok(Json.toJson(x)))
  )

  def getForId(id: Int) = Action.async(
    StudentsToCourses.forId(id).map(x => Ok(Json.toJson(x)))
  )

  def insertWithCourses = Action { request =>
    val json = request.body.asJson.get
    val (student, courseIds) = json.as[(Student, List[Int])]
    Students.insert(student)
    courseIds
      .map(c => (student.id, c))
      .foreach(x => StudentsToCourses.insert(x._1, x._2))
    // TODO remove actual courses to make room for new ones
    Ok
  }

  def insert = Action { request =>
    val json = request.body.asJson.get
    val student = json.as[Student]
    Students.insert(student)
    Ok
  }

}
