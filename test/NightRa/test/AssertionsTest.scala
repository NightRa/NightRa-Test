//Created By Ilan Godik
package NightRa.test

import org.junit.Test
import Assertions._

class AssertionsTest {
  @Test
  def testCompareDouble() {
    true === compareDouble(3.5, 3.49, 0.01)
  }

  @Test
  def testDoubleIdent() {
    5.5 === 5.5
  }

  @Test
  def testDouble() {
    3.5 === 3.49
  }

  @Test
  def testDiffPos() {
    8.9 === diff(13.5, 4.6)
  }

  @Test
  def testDiffNeg() {
    5.6 === diff(82.2, 87.8)
  }

  @Test
  def testDoubleArray() {
    val arr1 = Array(3.5, 2.8, 32, 6.77)
    val arr2 = Array(3.5, 2.8, 32, 6.771)
    arr1 === arr2
  }

  @Test
  def testDoublePrecisionImplicitPropagation() {
    val assertion = toAssertion(0.01, 0.0001)
    assertion.expected === 0.01
    assertion.delta === 0.0001
  }

  @Test
  def testAssertionDoublePrecision() {
    val assertion = Assertion(0.01, 0.0001)
    assertion.===(0.0099)
  }

  @Test
  def testAssertionDoublePrecisionFail() {
    intercept[ComparisonFailure] {
      Assertion(0.09, 0.001) === 0.08
    }
  }

  @Test
  def testExpectedValue() {
    Assertion(5).expected === 5
  }

  @Test
  def testInt() {
    3 === 3
  }

  @Test
  def testArrayEquality() {
    val arr1 = Array("Hello!", "How are you?")
    val arr2 = Array("Hello!", "How are you?")
    arr1 === arr2
  }

  @Test
  def testObjectEquality() {
    class Test(val i: Int) {
      override def equals(obj: Any) = obj.isInstanceOf[Test] && obj.asInstanceOf[Test].i == i
    }
    val test1 = new Test(10)
    val test2 = new Test(10)
    test1 === test2
  }

  @Test
  def testCaseClass() {
    case class Test(i: String)
    val test1 = Test("Hello!")
    val test2 = Test("Hello!")
    test1 === test2
  }

  @Test
  def testNotEqualsExceptionExact() {
    intercept[ComparisonFailure] {
      1 === 2
    }
  }

  @Test
  def testInterceptNoException() {
    intercept[AssertionError] {
      intercept[Throwable] {
        1 + 2
      }
    }
  }

  @Test
  def testWrongExceptionThrown() {
    val exception = intercept[ComparisonFailure] {
      // val = thrown exception from inside::
      intercept[IllegalArgumentException] {
        //Throws ComparisonFailure. Expects IllegalArgumentException
        1 === 2 // Actual is ComparisonFailure.
      }
    }
    "NightRa.test.ComparisonFailure" === exception.getClass.getName
    "java.lang.IllegalArgumentException" === exception.expected
    "NightRa.test.ComparisonFailure" === exception.actual
  }

  @Test
  def testExceptionPropagation() {
    val exception = intercept[IllegalArgumentException] {
      throw new IllegalArgumentException("I am an exception!")
    }
    "I am an exception!" === exception.getMessage
  }
}
