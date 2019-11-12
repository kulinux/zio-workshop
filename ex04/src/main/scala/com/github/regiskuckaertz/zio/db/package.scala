package com.github.regiskuckaertz.zio

import zio.ZIO

package object db {
  def getUsers: ZIO[Database, Nothing, List[User]] =
    ZIO.accessM(_.db.getUsers)

  def getProducts: ZIO[Database, Nothing, List[Product]] =
    ZIO.accessM(_.db.getProducts)
}