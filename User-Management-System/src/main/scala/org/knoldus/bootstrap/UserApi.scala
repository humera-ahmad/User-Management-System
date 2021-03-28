package org.knoldus.bootstrap

import org.knoldus.database.dbOperations
import org.knoldus.models.User
import org.knoldus.request.UserImpl
import org.knoldus.validator.UserValidator

object UserApi {
  def main(args: Array[String]): Unit = {
    val userValidator: UserValidator = new UserValidator
    val userImpl: dbOperations[User] = new UserImpl(userValidator)
    userImpl.create(User(1, "Damon", "damon@gmail.com", "admin"))
    userImpl.create(User(2, "Niklaus", "klaus@gmail.com", "customer"))
    userImpl.update(2, "Klaus")
    userImpl.display()
    userImpl.delete(2)
    userImpl.retrieve(1)
  }
}
