package org.knoldus.database

trait dbOperations[A] {
  def create(a: A) : Boolean
  def retrieve(id:Int): Any
  def update(id: Int, newName: String): Boolean
  def delete(id: Int): Boolean
  def display(): List[A]
}
