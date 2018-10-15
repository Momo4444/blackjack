package blackjack

import scala.util.Random

case class Deck(cards: List[Card]) {

  def reshuffle(): Deck = Deck(Random.shuffle(Deck.allCards))

  def draw: Card = cards.head

  def remove(card: Card): Deck = Deck(cards.tail)

}

object Deck {

  def apply(): Deck = Deck(allCards).reshuffle()

  val suits: List[Suit] = List(Clubs, Diamonds, Hearts, Spades)
  val ranks: List[Rank] = List(Ace, Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King)
  val allCards: List[Card] = for {
    r <- ranks
    s <- suits
  } yield Card(r, s)

}
