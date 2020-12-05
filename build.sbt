ThisBuild / scalaVersion := "2.12.8"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "com.github.jeko2000"
ThisBuild / organizationName := "jeko2000"
ThisBuild / description := "A (reference) implementation of a virtual file system"

lazy val root = (project in file("."))
  .settings(
    name := "vfs"
  )
