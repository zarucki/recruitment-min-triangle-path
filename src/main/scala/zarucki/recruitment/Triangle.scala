package zarucki.recruitment

case class Triangle private (rows: Vector[Vector[Int]])

object Triangle {

  /**
    * @param rows
    * @return Left is string error message
    */
  def apply(rows: Vector[Vector[Int]]): Either[String, Triangle] = {
    if (rows.headOption.map(_.size).forall(_ == 1)) {
      if (rows.size == 1 ||
          rows.view
            .map(_.size)
            .sliding(2)
            .forall { pair =>
              val (first :: second :: Nil) = pair.toList
              second - first == 1
            }) {
        Right(new Triangle(rows))
      } else {
        Left("Every row of triangle definition needs to have one more element than previous one.")
      }
    } else {
      Left("First row of triangle definition needs to have one element.")
    }

  }

  def fromInput(lines: Iterator[String]): Either[String, Triangle] = {
    parseInput(lines).flatMap(apply(_))
  }

  def parseInput(lines: Iterator[String]): Either[String, Vector[Vector[Int]]] = {
    val allLinesParseResults = lines.zipWithIndex
      .map {
        case (line, lineIndex) =>
          val lineParseResults = line.split("\\s+").map { numString =>
            numString.toIntOption match {
              case Some(value) => Right(value)
              case None        => Left(List(s"There's invalid input. '$numString' is not a integer!"))
            }
          }

          mergeEithers(lineParseResults) match {
            case Left(lineErrorMessages) =>
              Left(List(lineErrorMessages.mkString(s"Errors in the line $lineIndex '$line':\n\t", "\n\t", "\n")))
            case Right(numbers) => Right(numbers)
          }
      }
      .to(Array)

    mergeEithers(allLinesParseResults) match {
      case Left(value)  => Left(value.mkString("\n"))
      case Right(value) => Right(value)
    }
  }

  private def mergeEithers[T](eithers: Iterable[Either[List[String], T]]): Either[List[String], Vector[T]] = {
    eithers.foldLeft[Either[List[String], Vector[T]]](Right(Vector.empty)) {
      case (Left(errorMsgs), Left(anotherErrorMsg)) => Left(errorMsgs ++ anotherErrorMsg)
      case (l @ Left(_), Right(_))                  => l
      case (Right(_), Left(errorMsgs))              => Left(errorMsgs)
      case (Right(numberArray), Right(number))      => Right(numberArray :+ number)
    }
  }
}
