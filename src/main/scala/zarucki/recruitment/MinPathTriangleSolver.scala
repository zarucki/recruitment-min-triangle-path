package zarucki.recruitment

import scala.annotation.tailrec

case class MinPathSolution(total: Int = 0, nodesInOrder: List[Int] = Nil) {
  def prependNode(newNode: Int) = {
    copy(total + newNode, newNode :: nodesInOrder)
  }
}

class MinPathTriangleSolver() {

  def getMinPath(triangle: Triangle): MinPathSolution = {
    val reverseRows = triangle.rows.reverseIterator
    actualGetMinPath(reverseRows)
  }

  @tailrec
  final def actualGetMinPath(rowsInReverseOrder: Iterator[Array[Int]], memoizedSums: Option[Array[MinPathSolution]] = None): MinPathSolution = {
    def getMinSolutionOfAdjacentLeafs(index: Int): MinPathSolution = {
      memoizedSums.map(_.slice(index, index + 2))
        .map(_.minBy(_.total))
        .getOrElse(MinPathSolution())
    }

    if (rowsInReverseOrder.hasNext) {
      val currentRow = rowsInReverseOrder.next()

      val newSums = currentRow.zipWithIndex.map { case (value, index) =>
        getMinSolutionOfAdjacentLeafs(index).prependNode(value)
      }

      actualGetMinPath(rowsInReverseOrder, Some(newSums))
    } else {
      memoizedSums.flatMap(_.headOption).getOrElse(MinPathSolution())
    }
  }

}
