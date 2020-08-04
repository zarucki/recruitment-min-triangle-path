package zarucki.recruitment

import scala.annotation.tailrec

// TODO: maybe singleton object?
class TriangleAlgorithms {
  def getMinPath(triangle: Triangle): TrianglePath = {
    if (triangle.rows.isEmpty) {
      TrianglePath()
    } else {
      val reverseRows = triangle.rows.reverseIterator // iterator to not copy whole
      val lastRowOfTriangle = reverseRows.next() // non empty so no need to check hasNext

      getMinPathForTriangleRowsInReverseOrder(
        rowsInReverseOrder = reverseRows,
        allParentRowsSummarized = lastRowOfTriangle.map(TrianglePath.ofOne)
      )
    }
  }

  @tailrec
  private def getMinPathForTriangleRowsInReverseOrder(
      rowsInReverseOrder: Iterator[Vector[Int]],
      allParentRowsSummarized: Vector[TrianglePath]
  ): TrianglePath = {
    if (!rowsInReverseOrder.hasNext) {
      allParentRowsSummarized.headOption.getOrElse(TrianglePath())
    } else {
      val currentRow = rowsInReverseOrder.next()

      val minOfParentRow = pickMinInEveryAdjacentPair(allParentRowsSummarized).iterator.to(Iterable)
      val summarizedWithCurrent = currentRow
        .lazyZip(minOfParentRow)
        .map { case (currentValue, bestSolution) => bestSolution.withPrepended(currentValue) }

      getMinPathForTriangleRowsInReverseOrder(rowsInReverseOrder, summarizedWithCurrent)
    }
  }

  private def pickMinInEveryAdjacentPair(solutions: Vector[TrianglePath]): Iterator[TrianglePath] = {
    solutions
      .sliding(2)
      .map(_.minBy(_.totalOfPath))
  }
}
