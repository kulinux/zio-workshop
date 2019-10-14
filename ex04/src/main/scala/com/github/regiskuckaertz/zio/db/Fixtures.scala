package com.github.regiskuckaertz.zio.db

private[db] object Fixtures {
  val users = List(
    User(1, "Tintin"),
    User(2, "Snowy"),
    User(3, "Thomson and Thompson"),
    User(4, "Professor Alembick"),
    User(5, "King Muskar XII"),
    User(6, "Müsstler"),
    User(7, "Colonel Jorgen"),
    User(8, "Bianca Castafiore"),
    User(9, "Igor Wagner"),
    User(10, "Mrs. Finch"),
    User(11, "Colonel Sponsz")
  )

  val products = List(
    Product(1, "Buzz Cola"),
    Product(2, "Duff Beer"),
    Product(3, "Krusty-O's"),
    Product(4, "Radioactive Man"),
    Product(5, "Squishee"),
    Product(6, "Tomacco ")
  )

  val sales = collection.mutable.List(
    Sell(1, 2, 10),
    Sell(4, 4, 1),
    Sell(6, 5, 20),
    Sell(8, 2, 4),
    Sell(5, 2, 76),
    Sell(4, 2, 8),
    Sell(10, 2, 6),
    Sell(3, 2, 4),
    Sell(2, 6, 1),
  )
}