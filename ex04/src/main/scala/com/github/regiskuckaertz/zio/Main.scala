package com.github.regiskuckaertz.zio

import zio.{ App, ZIO, UIO }
import zio.console._

object Main extends App {

  /**
   * Design a small online store management app. The store allows you to:
   *  - list users
   *  - list products
   *  - list products bought by a particular user
   *  - register a sale for a specific user
   * 
   * In order to interact with the database of users and products, write
   * a module `Database`. You have complete freedom on its API, but the
   * above description should guide you.
   */
  def run(args: List[String]): ZIO[Environment, Nothing, Int] =
    for {
      _      <- showMenu
      choice <- userInput
      res    <- execute(choice)
      code   <- res match {
                  case Success => 
                    ZIO(0)
                  case Loop =>
                    run(Nil)
                  case Error(msg) =>
                    putStrLn(msg) *> run(Nil)
                }
    } yield code


    
  val menu = 
    """Menu:
      | 1. list users
      | 2. list products
      | 3. list products bought by a particular user
      | 4. register a sale for a specific user
      | 5. exit
      |
      | Pick your poison:
      |""".stripMargin

  val showMenu = putStrLn(menu)

  val userInput: ZIO[Console, Nothing, String] = ???

  def execute(command: String): ZIO[Any, Nothing, Status] = ???
}