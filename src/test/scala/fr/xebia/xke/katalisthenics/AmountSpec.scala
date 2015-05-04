package fr.xebia.xke.katalisthenics

import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner

@RunWith(classOf[JUnitRunner])
object AmountSpec extends Specification {

  "Operations on the amounts" should {
    val twenty = Amount(20)
    val twelve = Amount(12)
    val eight = Amount(8)

    "returns twenty when sum twelve with eight" in {
      (twelve + eight) must beEqualTo(twenty)
    }

    "returns twelve when subtract eight from twenty" in {
      (twenty - eight) must beEqualTo(twelve)
    }
  }
}
