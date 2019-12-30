package com.github.regiskuckaert.zio

import zio._
import zio.console._

object ChainingApp extends App {
  /* Write a program that 
   * - prints out a prompt, asking for the user's name
   * - reads the name from the console
   * - replies with a welcome message *BUT* if the name is "Regis" then it must fail
   *   with an error message and return a negative exit code
   * 
   * See https://static.javadoc.io/dev.zio/zio_2.12/1.0.0-RC11-1/zio/console/index.html for the console API
   */
  final def run(args: List[String]) = chainingAppMain *> ZIO.succeed(0)

  def chainingAppMain: ZIO[App#Environment, Nothing, Int] =
    chainingApp.catchAll(_ => IO.succeed(-1))

  def fail = {
    putStrLn("Error, dont mention this name again!!!") *> IO.succeed(-1)
  }

  def chainingApp: ZIO[Console, java.io.IOException, Int] =  for {
    _ <- putStrLn("What is your name?")
    name <- getStrLn
    _ <- if(name != "Regis") putStrLn(s"Welcome $name") else fail
  } yield 0

}