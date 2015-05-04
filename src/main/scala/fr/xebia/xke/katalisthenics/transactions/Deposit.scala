package fr.xebia.xke.katalisthenics.transactions

import fr.xebia.xke.katalisthenics.Amount

class Deposit(amount: Amount) extends Transaction(amount) {

  override def updateBalance(oldBalance: Amount) =
    oldBalance + amount

  override def print = printTransaction("Deposit")
}

object Deposit {
  def apply(amount: Amount) = new Deposit(amount)
}