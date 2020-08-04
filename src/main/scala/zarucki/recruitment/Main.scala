package zarucki.recruitment

object Main extends App {
  val triangleAlgorithms = new TriangleAlgorithms()

  val result = Triangle
    .fromInput(io.Source.stdin.getLines())
    .map(triangleAlgorithms.getMinPath)

  result match {
    case Left(errorMessage) => println(s"Error: $errorMessage")
    case Right(path)        => println(s"Minimal path is: ${path.pathFromTopToBottom.mkString(" + ")} = ${path.totalOfPath}")
  }
}
