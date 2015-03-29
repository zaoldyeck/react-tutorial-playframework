package models

import anorm._
import anorm.SqlParser._
import play.api.db._
import play.api.Play.current

/**
 * Created by zaoldyeck on 15/3/29.
 */
case class Comment(id: Long, author: String, text: String)

object Comment {

  val comment = {
    get[Long]("id") ~
      get[String]("author") ~
      get[String]("text") map {
      case id ~ author ~ text => Comment(id, author, text)
    }
  }

  def all: List[Comment] = DB.withConnection { implicit c =>
    SQL("select * from comment").as(comment *)
  }

  def create(author: String, text: String): Unit = {
    DB.withConnection { implicit c =>
      SQL("insert into comment (author,text) values ({author},{text})").on(
        'author -> author,
        'text -> text).executeUpdate()
    }
  }

  def delete(id: Long): Unit = {
    DB.withConnection { implicit c =>
      SQL("delete from comment where id = {id}").on(
        'id -> id).executeUpdate()
    }
  }
}