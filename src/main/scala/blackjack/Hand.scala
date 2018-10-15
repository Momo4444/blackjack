package blackjack

case class Hand(cards: List[Card]) {

  def reshuffle(): Hand = Hand()

  def add(card: Card): Hand = Hand(card :: cards)

  def sumValue(): Int = cards.foldLeft(0)((totalValue: Int, card: Card) => totalValue + card.getValue())

}

object Hand {

  def apply(): Hand = Hand(Nil)

}
