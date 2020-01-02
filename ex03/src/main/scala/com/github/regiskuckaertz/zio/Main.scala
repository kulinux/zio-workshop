
package com.github.regiskuckaert.zio

import zio._
import zio.console._
import zio.system._

import zio.blocking.Blocking

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


  final def run(args: List[String]) = {
    zio.fold(err => {
        err.printStackTrace
        println(s"ERROR $err")
        1
      },
      suc => {
        println(s"OK $suc")
        0
      }
    )
  }

  
  def readCapiKey(): RIO[App#Environment, String] =
    system.env("CAPI_TEST_KEY").someOrFailException

  def processInput(capi: Capi.Service[Any], input: String):
    ZIO[Any with Blocking, Throwable, String] = {
    input.split(" ")toList  match {
      case "search" :: keyword :: _ => capi.search(keyword)
      case "get" :: path :: _ => capi.article(path)
      case _  => ???
    }
  }

  val zio: ZIO[App#Environment, Throwable, Int] = for {
    capiKey <- readCapiKey()
    capi = Capi.make(capiKey)
    _ <- console.putStrLn("Please, enter a command")
    input <- console.getStrLn
    _ <- processInput(capi.capi, input)
  } yield 0
}