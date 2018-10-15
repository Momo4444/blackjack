package blackjack

sealed trait Suit

case object Clubs extends Suit
case object Diamonds extends Suit
case object Hearts extends Suit
case object Spades extends Suit


sealed trait Rank

case object Ace extends Rank
case object Two extends Rank
case object Three extends Rank
case object Four extends Rank
case object Five extends Rank
case object Six extends Rank
case object Seven extends Rank
case object Eight extends Rank
case object Nine extends Rank
case object Ten extends Rank
case object Jack extends Rank
case object Queen extends Rank
case object King extends Rank


case class Card(rank: Rank, suit: Suit) {

  def getValue(): Int = value

  val value: Int = rank match {
    case Ace => 1
    case Two => 2
    case Three => 3
    case Four => 4
    case Five => 5
    case Six => 6
    case Seven => 7
    case Eight => 8
    case Nine => 9
    case Ten || Jack || Queen || King => 10
  }

}
