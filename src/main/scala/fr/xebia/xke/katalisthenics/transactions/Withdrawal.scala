package fr.xebia.xke.katalisthenics.transactions

import fr.xebia.xke.katalisthenics.Amount

class Withdrawal(amount: Amount) extends Transaction(amount) {

  override def updateBalance(oldBalance: Amount) =
    oldBalance - amount

  override def print = printTransaction("Withdrawal")
}

object Withdrawal {
  def apply(amount: Amount) = new Withdrawal(amount)
}
