package com.gu.zio

import scala.sys.process._
import zio.{UIO, ZIO}
import zio.blocking
import zio.blocking.Blocking

trait Capi {
  def capi: Capi.Service[Any]
}

object Capi {
  trait Service[R] {
    def search(q: String): ZIO[R with Blocking, Throwable, String]

    def article(p: String): ZIO[R with Blocking, Throwable, String]
  }

  def make(apiKey: String): Capi = new Capi {
    private[this] val host = "https://content.guardianapis.com"

    private def get(url: String) = blocking.effectBlocking {
      s"curl -L -sS -XGET $url" #| "jq" !!
    }

    val capi = new Service[Any] {
      def search(q: String) =  get(s"$host/search?api-key=$apiKey&q=$q")
      def article(p: String) = get(s"$host/$p?api-key=$apiKey&show-fields=all")
    }
  }
}