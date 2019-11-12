package com.github.regiskuckaertz.zio

import com.github.regiskuckaertz.zio.db._
import java.io.IOException
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
  def run(args: List[String]): ZIO[Environment, Nothing, Int] = {
    def program: ZIO[Database with Console, Throwable, Int] = for {
      _      <- showMenu
      choice <- userInput
      status <- execute(choice)
      code   <- status match {
                  case Success => 
                    ZIO(0)
                  case Loop =>
                    program
                  case Error(msg) =>
                    putStrLn(msg) *> program
                }
    } yield code

    val environment: Console with Database = new Console with Database {
      val db = Database.Prod.db
      val console = Console.Live.console
    }

    program.catchAll(
      e => putStrLn(s"Woops, something bad happened: $e") *> UIO(-1)
    ).provide(environment)
  }

  val menu = 
    """
      |Menu:
      | 1. list users
      | 2. list products
      | 3. list products bought by a particular user
      | 4. register a sale for a specific user
      | 5. exit
      |
      |Pick your poison:
      |""".stripMargin

  val showMenu = putStrLn(menu)

  val userInput: ZIO[Console, IOException, Int] =
    getStrLn.flatMap { x =>
      ZIO(x.toInt).catchAll(_ => ZIO.succeed(0))
    }

  def execute(command: Int): ZIO[Console with Database, DatabaseError, Status] = command match {
    case 1 => getUsers.map(_.mkString("\n")).flatMap(putStrLn(_)) *> ZIO.succeed(Loop)
    case 2 => getProducts.map(_.mkString("\n")).flatMap(putStrLn(_)) *> ZIO.succeed(Loop)
    // case 4 =>
      // for {
      //   _ <- putStrLn("What user?")
      //   userId <- readStrLn
      //   _ <- putStrLn("What product?")
      //   productId <- readStrLn
      //   _ <- putStrLn(Quantity?)
      //   qty <- readStrLn
      //   _ <- sale(userId, productId, qty)
      // } yield Loop
    case 5 => ZIO.succeed(Success)
    case x => putStrLn(s"Invalid option: $x. Please try again.") *> ZIO.succeed(Loop)
  }
}