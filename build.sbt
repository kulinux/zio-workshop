ThisBuild / scalaVersion     := "2.13.0"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.gu"
ThisBuild / organizationName := "Guardian News & Media"
ThisBuild / turbo            := true

val commonSettings = Seq(
  libraryDependencies := Seq(
    "dev.zio" %% "zio" % "1.0.0-RC12-1"
  ),
  scalacOptions := Seq(
    "-language:postfixOps"
  )
)

def configure(n: String, p: Project) =
  p.settings(commonSettings).settings(name := n)

lazy val helloWorld = configure("hello-world", project in (file("hello-world")))

lazy val chaining = configure("chaining", project in (file("chaining")))

lazy val async = configure("async", project in (file("async")))