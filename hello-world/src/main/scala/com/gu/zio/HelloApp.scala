package com.gu.zio

import zio._
import zio.console._

/**
 * ZIO's `App` expects a program of the following type:
 *   ZIO[App#Environment, Nothing, Int]
 * 
 * In other words,
 * - it must yield an integer (used as an exit code, UNIX-style)
 * - it may not fail
 * - it has access to the whole of ZIO environment
 */
object HelloApp extends App {
  final def run(args: List[String]) = helloWorld *> ZIO.succeed(0)

  /* Write a program that prints out a message to the console */
  val helloworld: ZIO[???, ???, ???] = ???
}