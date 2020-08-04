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
    if (triangle.rows.isEmpty) {
      MinPathSolution()
    } else {
      val reverseRows = triangle.rows.reverseIterator // iterator to not copy whole
      val lastRowOfTriangle = reverseRows.next() // non empty so no need to check hasNext

      getMinPathForTriangleRowsInReverseOrder(
        rowsInReverseOrder = reverseRows,
        allParentRowsSummarized = lastRowOfTriangle.map(MinPathSolution.ofOne)
      )
    }
  }

  @tailrec
  private def getMinPathForTriangleRowsInReverseOrder(
      rowsInReverseOrder: Iterator[Array[Int]],
      allParentRowsSummarized: Array[MinPathSolution]
  ): MinPathSolution = {
    if (!rowsInReverseOrder.hasNext) {
      allParentRowsSummarized.headOption.getOrElse(MinPathSolution())
    } else {
      val currentRow = rowsInReverseOrder.next()

      val minOfParentRow = pickMinInEveryAdjacentPair(allParentRowsSummarized).iterator.to(Iterable)
      val summarizedWithCurrent = currentRow
        .lazyZip(minOfParentRow)
        .map { case (currentValue, bestSolution) => bestSolution.prependPath(currentValue) }

      getMinPathForTriangleRowsInReverseOrder(rowsInReverseOrder, summarizedWithCurrent)
    }
  }

  private def pickMinInEveryAdjacentPair(solutions: Array[MinPathSolution]): Iterator[MinPathSolution] = {
    solutions
      .sliding(2)
      .map(_.minBy(_.totalOfPath))
  }
}
