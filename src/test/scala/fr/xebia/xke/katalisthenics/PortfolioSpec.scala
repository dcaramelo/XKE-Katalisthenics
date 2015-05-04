package fr.xebia.xke.katalisthenics

import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner

@RunWith(classOf[JUnitRunner])
object PortfolioSpec extends Specification {

  val portfolio = new Portfolio()
  var username = "test.user"
  var username2 = "test.user2"

  "Portfolio" should {
    portfolio.createAccount(username)

    "returns created user" in {
      portfolio.accountOf(username) must beEqualTo(Some(Account(username)))
    }

    "returns none when asking for unknown user" in {
      portfolio.accountOf(username2) must beEqualTo(None)
    }
  }
}
