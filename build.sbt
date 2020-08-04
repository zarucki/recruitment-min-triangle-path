name := "recruitment-min-triangle-path"

version := "0.1"

scalaVersion := "2.13.3"

val scalaTestVersion = "3.2.0"

libraryDependencies += "org.scalatest" %% "scalatest" % scalaTestVersion % "test"

mainClass in (Compile,run) := Some("zarucki.recruitment.Main")