package com.github.regiskuckaertz.zio
package db

import zio.ZIO

/** Here is where you define your `Database` module */
trait Database {
  val db: Database.Service
}

object Database {
  trait Service {
    def getUsers: ZIO[Any, Nothing, List[User]]
    def getProducts: ZIO[Any, Nothing, List[Product]]
    def sale(userId: Int, productId: Int, qty: Int): ZIO[Any, DatabaseError, Unit]
  }

  // 1
  trait Prod extends Database {
    val db = new Service {
      val getUsers = ZIO.succeed(Fixtures.users)

      val getProducts = ZIO.succeed(Fixtures.products)

      def sale(userId: Int, productId: Int, qty: Int): ZIO[Any, DatabaseError, Unit] =
        if (qty > 10)
          ZIO.fail(OutOfStock(productId, qty))
        else
          ZIO.effectTotal(Fixtures.sales :+ Sell(userId, productId, qty)).unit
    }
  }

  object Prod extends Prod  
}

sealed trait DatabaseError
case class UserDoesntExist(userId: Int) extends DatabaseError
case class ProductDoesntExist(productId: Int) extends DatabaseError
case class OutOfStock(productId: Int, qty: Int) extends DatabaseError