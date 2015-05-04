package fr.xebia.xke.katalisthenics

import scala.collection.mutable.ArrayBuffer

class Portfolio {

  private val clients = ArrayBuffer[Account]()

  def createAccount(user: String) =
    clients += Account(user)

  /**
   * Find user account from username
   */
  def accountOf(username: String): Option[Account] =
    clients.find(account => account.isAccountOf(username))
}
