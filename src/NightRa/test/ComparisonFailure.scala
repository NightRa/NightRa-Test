//Created By Ilan Godik
package NightRa.test

class ComparisonFailure(val expected:Any,val actual:Any,val msg:String = "Comparison failure:") extends AssertionError(toString){
  override def toString: String = msg + "\r\nExpected: <" + expected + ">\r\nActual: <" + actual + ">\r\n"
}
