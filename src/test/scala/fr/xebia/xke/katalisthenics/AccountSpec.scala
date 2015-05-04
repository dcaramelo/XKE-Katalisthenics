package fr.xebia.xke.katalisthenics

import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner

@RunWith(classOf[JUnitRunner])
object AccountSpec extends Specification {

  var username = "test.user"

  var fifty = Amount(50)
  val twenty = Amount(20)
  var seventy = Amount(70)

  "Account" should {

    f"return true if asked is account of $username" in {
      val account = new Account(username)
      account.isAccountOf(username) must beTrue
    }

    "have balance at 0" in {
      val account = new Account(username)
      account.balance() must beEqualTo(Amount(0))
    }

    "See his balance increase to fifty when you make a deposit of fifty" in {
      val account = new Account(username)
      account.deposit(fifty)
      account.balance() must beEqualTo(fifty)
    }

    "See his balance increase to seventy when you make a deposit of fifty and twenty" in {
      val account = new Account(username)
      account.deposit(fifty)
      account.deposit(twenty)
      account.balance() must beEqualTo(seventy)
    }

    "See his balance increase to twenty when you make a deposit of seventy and withdrawal of fifty" in {
      val account = new Account(username)
      account.deposit(seventy)
      account.withdrawal(fifty)
      account.balance() must beEqualTo(twenty)
    }

    /*
    "Print history" in {
      val account = new Account(username)
      account.deposit(seventy)
      account.deposit(twenty)
      account.withdrawal(fifty)

      account.printHistory()
    }
    */
  }
}