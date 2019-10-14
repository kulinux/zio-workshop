package com.github.regiskuckaertz.zio

case class User(id: Int, name: String)

case class Product(id: Int, name: String)

case class Sell(userId: Int, productId: Int, quantity: Int)