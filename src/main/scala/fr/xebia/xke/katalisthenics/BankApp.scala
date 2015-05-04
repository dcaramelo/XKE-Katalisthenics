package fr.xebia.xke.katalisthenics

object BankApp {

  val portfolio = new Portfolio

  def createAccount(user: String) =
    portfolio.createAccount(user)

  // Manage client account
  def deposit(user: String, amount: Amount) = portfolio.accountOf(user) match {
    case Some(account) =>
      account.deposit(amount)
    case _ =>
      println(s"Unknown account with username $user")
  }

  def withdrawal(user: String, amount: Amount) = portfolio.accountOf(user) match {
    case Some(account) =>
      account.withdrawal(amount)
    case _ =>
      println(s"Unknown account with username $user")
  }

  def printHistory(user: String) = portfolio.accountOf(user) match {
    case Some(account) =>
      account.printHistory
    case _ =>
      println(s"Unknown account with username $user")
  }

  def balance(user: String) = portfolio.accountOf(user) match {
    case Some(account) =>
      account.balance
    case _ =>
      println(s"Unknown account with username $user")
  }

  // Transfers
  def transfer(from: String, to: String, amount: Amount): Unit =
    (portfolio.accountOf(from), portfolio.accountOf(to)) match {
      case (Some(fromAccount), Some(toAccount)) =>
        transfer(fromAccount, toAccount, amount)
      case _ =>
        println(s"Unknown account with username $from or $to")
    }

  private def transfer(from: Account, to: Account, amount: Amount) = {
    from.withdrawal(amount)
    to.deposit(amount)
  }

  /**
   * Main application
   */
  def main(args: Array[String]) {
    val user1 = "user1"
    val user2 = "user2"

    // Account 1
    createAccount(user1)
    deposit(user1, Amount(10))
    deposit(user1, Amount(15))
    withdrawal(user1, Amount(5))
    deposit(user1, Amount(13))
    deposit(user1, Amount(56))
    withdrawal(user1, Amount(22))
    deposit(user1, Amount(14))

    // Account 2
    createAccount(user2)
    deposit(user2, Amount(100))
    withdrawal(user2, Amount(20))
    deposit(user2, Amount(22))
    deposit(user2, Amount(7))
    withdrawal(user2, Amount(10))
    deposit(user2, Amount(23))

    // Transfer
    transfer(user2, user1, Amount(20))

    printHistory(user1)
  }
}
