package com.github.regiskuckaertz.zio

sealed trait Status
final object Success extends Status
final object Loop extends Status
final case class Error(msg: String) extends Status

