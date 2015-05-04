package fr.xebia.xke.katalisthenics.transactions

import fr.xebia.xke.katalisthenics.Amount

case class Withdrawal(override protected val amount: Amount) extends Transaction(amount) {

  override def updateBalance(oldBalance: Amount) =
    oldBalance - amount

  override def print = printTransaction("Withdrawal")
}
