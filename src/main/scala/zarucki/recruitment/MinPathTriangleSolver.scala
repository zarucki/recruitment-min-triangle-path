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

class MinPathTriangleSolver() {

  def getMinPath(triangle: Triangle): MinPathSolution = {
    actualGetMinPath(rowsInReverseOrder = triangle.rows.reverseIterator)
  }

  @tailrec
  private def actualGetMinPath(
      rowsInReverseOrder: Iterator[Array[Int]],
      memoizedSums: Option[Array[MinPathSolution]] = None
  ): MinPathSolution = {
    def getMinSolutionOfAdjacentLeafs(index: Int): MinPathSolution = {
      memoizedSums
        .map(_.slice(index, index + 2))
        .map(_.minBy(_.totalOfPath))
        .getOrElse(MinPathSolution())
    }

    if (rowsInReverseOrder.hasNext) {
      val newSums = rowsInReverseOrder.next().zipWithIndex.map {
        case (value, index) =>
          getMinSolutionOfAdjacentLeafs(index).prependPath(value)
      }

      actualGetMinPath(rowsInReverseOrder, Some(newSums))
    } else {
      memoizedSums.flatMap(_.headOption).getOrElse(MinPathSolution())
    }
  }

}
