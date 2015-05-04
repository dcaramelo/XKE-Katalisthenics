package fr.xebia.xke.katalisthenics

import java.util.Calendar

import fr.xebia.xke.katalisthenics.transactions._

case class Account(private val user: String) {

  /**
   * Account transaction history
   */
  private val history = new History

  def isAccountOf(user: String) = this.user == user

  def deposit(amount: Amount) =
    history add Deposit(amount)

  def withdrawal(amount: Amount) =
    history add Withdrawal(amount)


  def balance() = history.balance

  def printHistory() = history.print()


  def canEqual(other: Any): Boolean = other.isInstanceOf[Account]

  override def equals(other: Any): Boolean = other match {
    case that: Account =>
      (that canEqual this) && user == that.user
    case _ => false
  }

  override def hashCode() = user.hashCode
}