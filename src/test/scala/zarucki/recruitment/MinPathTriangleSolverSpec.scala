package zarucki.recruitment

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class MinPathTriangleSolverSpec extends AnyFlatSpec with Matchers {

  val sut = new MinPathTriangleSolver

  behavior of "MinPathTriangleSolver"

  it should "solve example case" in {
    sut.getMinPath(Triangle(
      Array(
        Array(7),
        Array(6, 3),
        Array(3, 8, 5),
        Array(11, 2, 10, 9)
      )
    )) shouldEqual MinPathSolution(18, List(7, 6, 3, 2))
  }

  it should "handle one line triangle" in {
    sut.getMinPath(Triangle(Array(Array(1)))) shouldEqual MinPathSolution(1, List(1))
  }

  it should "handle empty triangle" in {
    sut.getMinPath(Triangle(Array())) shouldEqual MinPathSolution()
  }
}