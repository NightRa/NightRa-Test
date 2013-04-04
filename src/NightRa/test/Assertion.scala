//Created By Ilan Godik
package NightRa.test

import Assertions._

case class Assertion[T](expected: T, delta: Double = 0.01) {
  private[test] lazy val doubleEquality: (Double, Double) => Boolean = compareDouble(_, _, delta)

  def =!(actual:Any){
    ===(actual)
  }

  def ===(actual: Any) {
    (expected, actual) match {
      case (e: Double, a: Double) => assertEquals(e, a, doubleEquality)
      case (e: Array[Double], a: Array[Double]) => assertIterable(e, a, doubleEquality)
      case (e: Array[Any], a: Array[Any]) => assertIterable(e, a, equalityPredicate)
      case (e: Iterable[Any], a: Iterable[Any]) => assertIterable(e, a, equalityPredicate)
      case (e: Any, a: Any) => assertEquals(e, a, equalityPredicate)
    }
  }
}