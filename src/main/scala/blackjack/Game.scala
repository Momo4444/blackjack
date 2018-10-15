package blackjack

import cats.effect.IO

object Game {



  def print(message: String): IO[Unit] = IO(println(message))

  def read: IO[String] = IO(scala.io.StdIn.readLine)
  def resolve(string: String): IO[Response] = {
    if (string == 'h') IO(HitMe)
    else IO(Stay)
  }

  def readResponse: IO[Response] = for {
    s <- read
    r <- resolve(s)
  } yield r



  def newDeck(): IO[Deck] = IO(Deck())
  def newHand(): IO[Hand] = IO(Hand())

  def draw(deck: Deck, hand: Hand): IO[(Deck, Hand)] = for {
    c <- IO(deck.pick)
    d <- IO(deck.remove(c))
    h <- IO(hand.add(c))
  } yield (d, h)

  def newRound(): IO[(Deck, Hand)] = for {
    d <- newDeck()
    h <- newHand()
    dh <- draw(d, h)
    dh2 <- draw(dh._1, dh._2)
  } yield (dh2._1, dh2._2)



  def viewHand(hand: Hand): IO[Unit] = for {
    _ <- print("\n-------------------")
    _ <- print("Your hand:")
    c <- IO(hand.cards.mkString("\n"))
    _ <- print(c)
    _ <- print("-------------------\n")
  } yield ()



  def action(deck: Deck, hand: Hand, response: Response): IO[Hand] = response match {
    case HitMe => for {
      dh <- draw(deck, hand)
    } yield dh._2
    case Stay => IO(hand)
  }

  def yourTurn(deck: Deck, hand: Hand): IO[Hand] = for {
    _ <- print("\n-------------------")
    _ <- print("Your turn:")
    _ <- print("Type 'h' for hit me, 's' for stay")
    r <- readResponse
    h <- action(deck, hand, r)
    _ <- print("-------------------\n")
  } yield h



  def calculateScore(hand: Hand): IO[Int] = IO(hand.sumValue())

  def calculateResult(score: Int): IO[String] = {
    if (score < 21) IO(s"You were ${21 - score} under")
    else if (score > 21) IO(s"score, bust!")
    else IO(s"21, BLACKJACK!")
  }

  def results(hand: Hand): IO[Unit] = for {
    _ <- print("\n-------------------")
    _ <- print("Results:")
    s <- calculateScore(hand)
    r <- calculateResult(s)
    _ <- print(r)
    _ <- print("-------------------\n")
  } yield ()




  val program = for {
    _ <- print("Let's play Blackjack!")
    dh <- newRound()
    _ <- viewHand(dh._2)
    h <- yourTurn(dh._1, dh._2)
    _ <- viewHand(h)
    _ <- results(h)
  } yield ()

  def play() = program.unsafeRunSync()

}
