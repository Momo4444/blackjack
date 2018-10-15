package blackjack

import cats.effect.IO

object Game {

  def newDeck(): IO[Deck] = IO(Deck())
  def newHand(): IO[Hand] = IO(Hand())
  def newRound(): IO[Unit] = IO{
    val deck = newDeck()
    val hand = newHand()
  }

  def print(message: String): IO[Unit] = IO(println(message))




  val program = for {
    _ <- print("Let's play Blackjack!")

  } yield ()

  def play() = program.unsafeRunSync()

}
