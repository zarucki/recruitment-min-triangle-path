package zarucki.recruitment

object Main extends App {
  val allInputLines = io.Source.stdin.getLines()
  val parsedInput = allInputLines
    .map { line =>
      line.split("\\s+").map { numString =>
        numString.toIntOption match {
          case Some(value) => value
          case None =>
            println(s"There's invalid input. '${numString}' is not a integer!")
            throw new IllegalArgumentException("Triangle is not proper.")
        }
      }
    }
    .to(Array)

  val triangle = Triangle(parsedInput) // this does require so it can crash with inproper triangle

  val result = new TriangleAlgorithms().getMinPath(triangle)

  println(s"Minimal path is: ${result.pathFromTopToBottom.mkString(" + ")} = ${result.totalOfPath}")
}
