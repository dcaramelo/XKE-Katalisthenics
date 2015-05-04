package fr.xebia.xke.katalisthenics

case class Amount(private val value: Int) {

  // Add and sub operations
  def +(that: Amount) = new Amount(this.value + that.value)
  def -(that: Amount) = new Amount(this.value - that.value)


  override def toString: String = f"$value%1.2f"

  // Equals & hashCode
  def canEqual(other: Any) =
    other.isInstanceOf[Amount]

  override def equals(other: Any) = other match {
    case that: Amount => value == that.value
    case _ => false
  }

  override def hashCode() = value.hashCode()
}