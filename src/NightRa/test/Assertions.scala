//Created By Ilan Godik
package NightRa.test

object Assertions{
  //           Of type:  Able to retrieve:
  def intercept[T <: Throwable : Manifest](f: => Any): T = {
    val expectedName = manifest.runtimeClass.getName
    val actual = try { // The exception
      f //Preform throwing operation
      None //Nothing thrown
    } catch {
      case caught: Throwable => caught // Propagate to actual.
    }

    actual match {
      case None => throw new AssertionError("Exception expected but not thrown. \r\nExpected: <" + expectedName + ">\r\n")
      case matching: T => matching
      case _ => throw new ComparisonFailure(expectedName, actual.getClass.getName,"Wrong exception was thrown. ")
    }
  }

  private[test] lazy val equalityPredicate: (Any, Any) => Boolean = (_ == _)

  private[test] def compareDouble(expected: Double, actual: Double, delta: Double): Boolean = {
    if (expected == actual) return true
    if (diff(expected, actual) <= delta) return true
    false
  }

  private[test] def diff(num1:Double,num2:Double):Double = {
    Math.abs(num1-num2)
  }

  private[test] def assertEquals[K](expected: K, actual: K, predicate: (K, K) => Boolean) {
    if (!predicate(expected, actual)) throw new ComparisonFailure(expected, actual)
  }

  private[test] def assertIterable[K](expected: Iterable[K], actual: Iterable[K], predicate: (K, K) => Boolean) {
    if (expected == actual) return
    val expectedItr = expected.iterator
    val actualItr = actual.iterator
    var i = 0
    while (expectedItr.hasNext && actualItr.hasNext) {
      i += 1
      val currentExpected = expectedItr.next()
      val currentActual = actualItr.next()
      if (!predicate(currentExpected, currentActual)) throw new ComparisonFailure(s"Expected[$i]=$currentExpected", s"Actual[$i]=$currentActual", s"Comparison failure:\r\nFirst elements differed at index:$i")
    }
  }

  implicit def toAssertion[T](expected: T) = Assertion(expected)
  implicit def toAssertion[T](expected: T,delta:Double) = Assertion(expected,delta)
}