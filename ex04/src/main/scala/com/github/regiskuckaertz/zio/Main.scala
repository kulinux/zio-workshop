package com.github.regiskuckaertz.zio

import zio.App

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
  def run(args: List[String]): ZIO[Environment, Nothing, Int] = ???
  
}