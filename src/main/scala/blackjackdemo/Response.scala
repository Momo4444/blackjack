package blackjackdemo

sealed trait Response

case object HitMe extends Response
case object Stay extends Response
