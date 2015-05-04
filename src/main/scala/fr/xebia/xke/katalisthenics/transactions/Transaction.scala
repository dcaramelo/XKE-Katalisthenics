package fr.xebia.xke.katalisthenics.transactions

import java.text.SimpleDateFormat
import java.util.{GregorianCalendar, Calendar}

import fr.xebia.xke.katalisthenics.Amount

abstract class Transaction(protected val amount: Amount) {

  protected val date = new GregorianCalendar()
  private val df = new SimpleDateFormat("dd/MM/yyyy")

  def printTransaction(trType: String) =
    printf("%-15s %8s€ %20s\n", trType, amount, df.format(date.getTime))

  def updateBalance(oldBalance: Amount): Amount

  def print()
}
