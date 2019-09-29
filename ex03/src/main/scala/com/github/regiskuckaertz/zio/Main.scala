
package com.github.regiskuckaert.zio

import zio._
import zio.console._
import zio.system._

/**
 * Write a program that:
 * - reads the CAPI_TEST_KEY environment variable (https://static.javadoc.io/dev.zio/zio_2.12/1.0.0-RC11-1/zio/system/index.html)
 * - write a program that loops forever (look up the ZIO docs again):
 *   - prompting for a keyword: 
 *     * if the user enters: search <keyword>, perform a CAPI search with the specific keyword
 *     * if the user enters: get <path>, perform a CAPI article request with the specified path
 *   - searching CAPI is done with the Capi service that is provided to you (open up the Capi.scala)
 *   - storing the output of all CAPI requests into a json file called results.json. That file must be properly closed, even in the
 *     occurence of an asynchronous exception (like the user hitting Ctrl-C).
 */
object Main extends App {
  final def run(args: List[String]) = ???
}