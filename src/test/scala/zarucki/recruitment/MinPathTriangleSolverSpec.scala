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

  it should "solve case #2" in {
    sut.getMinPath(Triangle(
      Array(
        Array(4),
        Array(5, 6),
        Array(1, 1, 1)
      )
    )) shouldEqual MinPathSolution(10, List(4, 5, 1))
  }

  it should "solve case #3" in {
    sut.getMinPath(Triangle(
      Array(
        Array(2),
        Array(3, 4),
        Array(6, 5, 7),
        Array(4, 1, 8, 3)
      )
    )) shouldEqual MinPathSolution(11, List(2, 3, 5, 1))
  }

  it should "solve case #4" in {
    sut.getMinPath(Triangle(
      Array(
        Array(2),
        Array(5, 4),
        Array(5, 5, 7),
        Array(1, 4, 8, 3)
      )
    )) shouldEqual MinPathSolution(13, List(2, 5, 5, 1))
  }

  it should "solve case #5" in {
    sut.getMinPath(Triangle(
      Array(
        Array(2),
        Array(3, 7),
        Array(8, 5, 6),
        Array(6, 1, 9, 3)
      )
    )) shouldEqual MinPathSolution(11, List(2, 3, 5, 1))
  }

  it should "solve case #6" in {
    sut.getMinPath(Triangle(
      Array(
        Array(3),
        Array(6, 4),
        Array(5, 2, 7),
        Array(9, 1, 8, 6)
      )
    )) shouldEqual MinPathSolution(10, List(3, 4, 2, 1))
  }

  it should "solve case #7 with 0s" in {
    sut.getMinPath(Triangle(
      Array(
        Array(9),
        Array(3, 8),
        Array(0, 2, 4),
        Array(8, 3, 9, 0),
        Array(5, 2, 2, 7, 3),
        Array(7, 9, 0, 2, 3,  9),
        Array(9, 7, 0, 3, 9,  8, 6),
        Array(5, 7, 6, 2, 7,  0, 3, 9)
    ))) shouldEqual MinPathSolution(19, List(9, 3, 0, 3, 2, 0, 0, 2))
  }

  it should "solve case #8 with negative number" in {
    sut.getMinPath(Triangle(
      Array(
        Array(9),
        Array(3, 8),
        Array(0, 2, 4),
        Array(8, 3, 9, 0),
        Array(5, 2, 2, 7, 3),
        Array(7, 9, 0, 2, 3, -200),
        Array(9, 7, 0, 3, 9,  8, 6),
        Array(5, 7, 6, 2, 7,  0, 3, 9)
      ))) shouldEqual MinPathSolution(-168, List(9, 8, 4, 0, 3, -200, 8, 0))
  }

  it should "solve case #9" in {
    sut.getMinPath(Triangle(
      Array(
        Array(9),
        Array(1, 7),
        Array(2, 3, 6),
        Array(5, 5, 8, 1),
        Array(4, 7, 1, 3, 8),
        Array(4, 8, 0, 4, 6, 0),
        Array(3, 2, 6, 9, 4, 1, 3),
        Array(7, 8, 8, 3, 8, 1, 5, 3),
        Array(5, 4, 3, 6, 5, 9, 5, 4, 9),
        Array(1, 7, 5, 5, 4, 1, 8, 8, 3, 5),
      ))) shouldEqual MinPathSolution(33, List(9, 1, 2, 5, 1, 0, 6, 3, 5, 1))
  }

  // TODO: could this be detected and more optimal?
  it should "solve all ones" in {
    sut.getMinPath(Triangle(
      Array(
        Array(1),
        Array(1, 1),
        Array(1, 1, 1)
      )
    )) shouldEqual MinPathSolution(3, List(1, 1, 1))
  }

  it should "handle one line triangle" in {
    sut.getMinPath(Triangle(Array(Array(1)))) shouldEqual MinPathSolution(1, List(1))
  }

  it should "handle empty triangle" in {
    sut.getMinPath(Triangle(Array())) shouldEqual MinPathSolution()
  }
}
