ThisBuild / scalaVersion     := "2.13.0"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.github.regiskuckaertz"
ThisBuild / organizationName := "Regis Kuckaertz"
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

lazy val ex01 = configure("ex01", project in (file("ex01")))

lazy val ex02 = configure("ex02", project in (file("ex02")))

lazy val ex03 = configure("ex03", project in (file("ex03")))

lazy val ex04 = configure("ex04", project in (file("ex04")))