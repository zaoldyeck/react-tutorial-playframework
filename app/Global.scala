import models.Comment
import play.api._

/**
 * Created by zaoldyeck on 15/3/29.
 */
object Global extends GlobalSettings {

  override def onStart(app: Application): Unit = {
    Comment.create("Pete Hunt", "This is one comment")
    Comment.create("Jordan Walke", "This is *another* comment")
  }
}