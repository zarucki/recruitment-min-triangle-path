package zarucki.recruitment

import scala.annotation.tailrec

case class MinPathSolution(totalOfPath: Int = 0, pathFromTopToBottom: List[Int] = Nil) {
  def prependPath(pathFragment: Int): MinPathSolution = {
    copy(
      totalOfPath = totalOfPath + pathFragment,
      pathFromTopToBottom = pathFragment :: pathFromTopToBottom
    )
  }
}

object MinPathSolution {
  def ofOne(oneFragment: Int) = MinPathSolution(oneFragment, List(oneFragment))
}

class TriangleAlgorithms {
  def getMinPath(triangle: Triangle): MinPathSolution = {
    actualGetMinPath(rowsInReverseOrder = triangle.rows.reverseIterator) // iterator to not copy whole
  }

  @tailrec
  private def actualGetMinPath(
      rowsInReverseOrder: Iterator[Array[Int]],
      summarizedParentRow: Option[Array[MinPathSolution]] = None
  ): MinPathSolution = {
    if (rowsInReverseOrder.hasNext) {

      val currentRow = rowsInReverseOrder.next()

      summarizedParentRow match {
        case None => actualGetMinPath(rowsInReverseOrder, Some(currentRow.map(MinPathSolution.ofOne)))
        case Some(parentRow) =>
          val minOfParentRow = pickMinInEveryAdjacentPair(parentRow).iterator.to(Iterable)
          actualGetMinPath(
            rowsInReverseOrder,
            Some(currentRow.lazyZip(minOfParentRow).map {
              case (currentValue, bestSolution) => bestSolution.prependPath(currentValue)
            })
          )
      }
    } else {
      summarizedParentRow.flatMap(_.headOption).getOrElse(MinPathSolution())
    }
  }

  private def pickMinInEveryAdjacentPair(solutions: Array[MinPathSolution]): Iterator[MinPathSolution] = {
    solutions
      .sliding(2)
      .map(_.minBy(_.totalOfPath))
  }
}
