name := """gestaodeprocessos"""
organization := "com.gestaodeprocessos"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.12.8"

libraryDependencies += guice

// https://mvnrepository.com/artifact/org.apache.clerezza.ext/org.json.simple
libraryDependencies += "org.apache.clerezza.ext" % "org.json.simple" % "0.4"


libraryDependencies += "org.postgresql" % "postgresql" % "9.4-1206-jdbc42"
