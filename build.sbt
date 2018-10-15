lazy val buildSettings = Seq(
  name         := "cats-test-project",
  version      := "0.1",
  scalaVersion := "2.12.7",
)

lazy val commonScalacOptions = Seq(
  "-language:higherKinds",
  "-language:implicitConversions",
  "-Ypartial-unification",
)

lazy val commonLibraryDependencies = Seq(
  "org.typelevel" %% "cats-effect" % "1.0.0-RC2",
)

lazy val commonSettings = Seq(
  scalacOptions ++= commonScalacOptions,
  libraryDependencies ++= commonLibraryDependencies,
)

lazy val root = (project in file("."))
  .settings(buildSettings)
  .settings(commonSettings)
