package org.knoldus.request

import org.knoldus.models.User
import org.knoldus.validator.UserValidator
import org.mockito.MockitoSugar.{mock, when}
import org.scalatest.flatspec.AnyFlatSpec

class UserImplTest extends AnyFlatSpec {

  val mockedUserValidator: UserValidator = mock[UserValidator]
  val userImpl = new UserImpl(mockedUserValidator)
  val user1: User = User(1,"Damon","damon@gmail.com","admin")
  val user2: User = User(2,"Niklaus","klaus@gmail.com","customer")
  val user3: User = User(3,"","stefan@gmail.com","customer")
  val user4: User = User(4,"Stefan","stefangmailcom","customer")
  val user5: User = User(5, "Stefan","stefan@gmail.com","c")

  "UserImpl" should "create user successfully" in {
    when(mockedUserValidator.userIsValid(user1)) thenReturn true
    val result = userImpl.create(user1)
    assert(result)
  }

  it should "not create user as name is invalid" in {
    when(mockedUserValidator.userIsValid(user3)) thenReturn false
    val result = userImpl.create(user3)
    assert(!result)
  }

  it should "not create user as email is invalid" in {
    when(mockedUserValidator.userIsValid(user4)) thenReturn false
    val result = userImpl.create(user4)
    assert(!result)
  }

  it should "not create user as userCategory is invalid" in {
    when(mockedUserValidator.userIsValid(user5)) thenReturn false
    val result = userImpl.create(user5)
    assert(!result)
  }

  it should "update the user successfully" in {
    when(mockedUserValidator.userIsValid(user2)) thenReturn true
    userImpl.create(user2)
    val result = userImpl.update(2,"Klaus")
    assert(result)
  }


  it should "not update user as key is invalid" in {
    when(mockedUserValidator.userIsValid(user1)) thenReturn true
    val result = userImpl.update(5,"Stefan")
    assert(!result)
  }

  it should "not update as username is invalid" in {
    when(mockedUserValidator.userIsValid(user3)) thenReturn false
    userImpl.create(user3)
    val result = userImpl.update(3,"Stefan")
    assert(!result)
  }

  it should "not update as email is invalid" in {
    when(mockedUserValidator.userIsValid(user4)) thenReturn false
    val result = userImpl.update(4,"Stefan")
    assert(!result)
  }

  it should "not update as user category is invalid" in {
    when(mockedUserValidator.userIsValid(user5)) thenReturn false
    val result = userImpl.update(5,"Stefan")
    assert(!result)
  }

  it should "delete the user successfully" in {
    when(mockedUserValidator.userIsValid(user1)) thenReturn true
    userImpl.create(user1)
    val result = userImpl.delete(1)
    assert(result)
  }

  it should "not delete any user as key is invalid" in {
    when(mockedUserValidator.userIsValid(user1)) thenReturn true
    userImpl.create(user1)
    val result = userImpl.delete(8)
    assert(!result)
  }

  it should "not perform deletion as no username found" in {
    when(mockedUserValidator.userIsValid(user3)) thenReturn false
    val result = userImpl.delete(3)
    assert(!result)
  }

  it should "not perform deletion as no user found" in {
    when(mockedUserValidator.userIsValid(user2)) thenReturn true
    val result = userImpl.delete(3)
    assert(!result)
  }


  it should "retrieve the user" in {
    when(mockedUserValidator.userIsValid(user1)) thenReturn true
    userImpl.create(user1)
    val result = userImpl.retrieve(1)
    assertResult(user1)(result)
  }

  it should "not be able to retrieve the user as key is invalid" in {
    when(mockedUserValidator.userIsValid(user1)) thenReturn true
    userImpl.create(user1)
    val result = userImpl.retrieve(8)
    assertResult("Sorry! No Such Key Present")(result)
  }

  it should "return no user found" in {
    when(mockedUserValidator.userIsValid(user1)) thenReturn false
    userImpl.create(user1)
    val result = userImpl.retrieve(8)
    assertResult("Sorry! No Such Key Present")(result)
  }


  it should "display all the users" in {
    when(mockedUserValidator.userIsValid(user1)) thenReturn true
    when(mockedUserValidator.userIsValid(user2)) thenReturn true
    userImpl.create(user1)
    userImpl.create(user2)
    val result = userImpl.display()
    assertResult(List(user1,user2))(result)
  }

}
