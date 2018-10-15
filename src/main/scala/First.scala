import cats.effect.IO

object First extends App {

  val io: IO[String] = IO.pure("Hello!")
  val io2: IO[Unit] = io.flatMap(s => IO(println(s)))

  io2.unsafeRunSync()

  val result = for (
    _ <- io2
  ) yield ()

  result.unsafeRunSync()

  /////////

  def oi(s: String): IO[String] = IO.pure(s)
  def oi2(s: String): IO[Unit] = IO(println(s))
  val oi3: IO[Unit] = oi("Wag1").flatMap(oi2)//.map(x => x)

  oi3.unsafeRunSync()

  val res = for {
    s <- oi("Yoooo")
    _ <- oi2(s)
  } yield ()

  res.unsafeRunSync()

}
