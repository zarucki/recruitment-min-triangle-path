
// TODO: better representation?
case class Triangle(rows: Array[Array[Int]])

case class MinPathSolution(total: Int, nodesInOrder: List[Int])

class MinPathTriangleSolver {
  def getMinPath(triangle: Triangle): MinPathSolution = {
    MinPathSolution(0, List.empty)
  }
}
