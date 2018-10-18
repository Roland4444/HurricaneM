package Essentials

case class voiceResult( checkResult: Int, lastErrorInSession: Int, ResultLoadingSoSymbols: Int)

case class fullvoiceResult(filename: String, details: voiceResult)
