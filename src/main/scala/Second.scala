import cats.effect.IO

import scala.collection.mutable.ListBuffer

object Second extends App {

  val names: ListBuffer[String] = ListBuffer() // change this to state monad :)

  def askQuestion(q: String): IO[Unit] = IO(println(q))
  def readReply: IO[String] = IO(scala.io.StdIn.readLine)
  def addToNames(name: String): IO[Unit] = IO(names.append(name))
  def echoConfirmation(name: String): IO[Unit] = IO(println(s"$name has been added to the list!"))
  def showNames: IO[Unit] = IO {
    println("----------")
    println("Guests:")
    for (name <- names) println(name)
    println("----------")
  }

  val program = for {
    _ <- askQuestion("Who do you want to invite to Ping Pong?")
    name1 <- readReply
    _ <- addToNames(name1)
    _ <- echoConfirmation(name1)
    _ <- showNames
    _ <- askQuestion("Who else do you want to invite?")
    name2 <- readReply
    _ <- addToNames(name2)
    _ <- echoConfirmation(name2)
    _ <- showNames
    _ <- askQuestion("Final guest?")
    name3 <- readReply
    _ <- addToNames(name3)
    _ <- echoConfirmation(name3)
    _ <- showNames
  } yield ()

  program.unsafeRunSync()

}
