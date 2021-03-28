package org.knoldus.bootstrap

import org.knoldus.database.dbOperations
import org.knoldus.models.User
import org.knoldus.request.UserImpl
import org.knoldus.validator.UserValidator
import org.scalatest.flatspec.AnyFlatSpec

class UserApiTest extends AnyFlatSpec{
  val userValidator: UserValidator = new UserValidator
  val userImpl: dbOperations[User] = new UserImpl(userValidator)

  "UserApi" should "create the admin" in {
    val result = userImpl.create(User(1,"Damon","damon@gmail.com","admin"))
    assert(result)
  }

  it should "create the customer" in {
    val result = userImpl.create(User(2,"Niklaus","klaus@gmail.com","customer"))
    assert(result)
  }

  it should "update the customer" in {
    val result = userImpl.update(2,"Klaus")
    assert(result)
  }

  it should "display the list of users" in {
    val result = userImpl.display()
    assertResult(List(User(1,"Damon","damon@gmail.com","admin"), User(2,"Klaus","klaus@gmail.com","customer")))(result)
  }

  it should "delete the user" in {
    val result = userImpl.delete(2)
    assert(result)
  }

  it should "retrieve the desired user" in {
    val result = userImpl.retrieve(1)
    assertResult(User(1,"Damon","damon@gmail.com","admin"))(result)
  }

}
