package fr.xebia.xke.katalisthenics.transactions

import fr.xebia.xke.katalisthenics.Amount

import scala.collection.mutable.ArrayBuffer

class History {

  private val entries = ArrayBuffer[Transaction]()

  def add(transaction: Transaction) =
    entries += transaction

  def balance() =
    entries.foldLeft(Amount(0))((acc, tr) => tr.updateBalance(acc))

  def print() =
    entries.foreach(tr => tr.print)
}
