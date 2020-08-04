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
      val newSums = rowsInReverseOrder.next().zipWithIndex.map {
        case (value, index) =>
          summarizedParentRow
            .map(getMinSolutionOfAdjacentLeafs(_, index))
            .getOrElse(MinPathSolution())
            .prependPath(value)
      }

      actualGetMinPath(rowsInReverseOrder, Some(newSums))
    } else {
      summarizedParentRow.flatMap(_.headOption).getOrElse(MinPathSolution())
    }
  }

  private def getMinSolutionOfAdjacentLeafs(
      summarizedParentRow: Array[MinPathSolution],
      index: Int
  ): MinPathSolution = {
    summarizedParentRow
      .slice(from = index, until = index + 2)
      .minBy(_.totalOfPath)
  }
}
