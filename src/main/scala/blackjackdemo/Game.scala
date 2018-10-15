package blackjackdemo

import cats.effect.IO
import cats.syntax.flatMap._

object Game {

  def print(message: String): IO[Unit] = IO(println(message))

  def newDeck(): IO[Deck] = IO.pure(Deck())
  def newHand(): IO[Hand] = IO.pure(Hand())

  def draw(deck: Deck, hand: Hand): (Deck, Hand) = {
    val g = deck.pick
    val h = hand.add(g._1)
    (g._2, h)
  }

  def newRound(): IO[(Deck, Hand)] = for {
    d <- newDeck()
    h <- newHand()
    dh <- IO.pure(draw(d, h))
    dh2 <- IO.pure(draw(dh._1, dh._2))
  } yield (dh2._1, dh2._2)




  def viewHand(hand: Hand): IO[Unit] = print(hand.toString)





  def yourTurn(deck: Deck, hand: Hand): IO[Hand] = {

    viewHand(hand) >>
      print("Do you want to hit or stick") >>
    readResponse.flatMap {
      case HitMe =>
        val (d, h) = draw(deck, hand)
        yourTurn(d, h)
      case Stay => IO.pure(hand)
    }

  }




  def readResponse: IO[Response] =
    IO (scala.io.StdIn.readLine()).map{
      case "hit" => HitMe
      case _ => Stay
    }







  def results(hand: Hand): IO[Unit] = ???




  val program: IO[Unit] = for {
    _ <- print("Let's play Blackjack!")
    r <- newRound()
    t <- yourTurn(r._1, r._2)
    _ <- viewHand(t)
  } yield ()

  def play(): Unit = program.unsafeRunSync()

}
