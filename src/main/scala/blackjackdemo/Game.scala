package blackjackdemo

import cats.effect.IO

object Game {

  def print(message: String): IO[Unit] = IO(println(message))

  def newDeck(): IO[Deck] = IO(Deck())
  def newHand(): IO[Hand] = IO(Hand())

  def draw(deck: Deck, hand: Hand): IO[(Deck, Hand)] = ???

  def newRound(): IO[(Deck, Hand)] = ???




  def viewHand(hand: Hand): IO[Unit] = ???





  def yourTurn(deck: Deck, hand: Hand): IO[Hand] = ???




  def readResponse: IO[Response] = ???





  def results(hand: Hand): IO[Unit] = ???




  val program: IO[Unit] = for {
    _ <- print("Let's play Blackjack!")
  } yield ()

  def play(): Unit = program.unsafeRunSync()

}
