package zarucki.recruitment

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class TriangleAlgorithmsSpec extends AnyFlatSpec with Matchers {

  val sut = new TriangleAlgorithms

  behavior of "TriangleAlgorithms"

  it should "solve example case" in {
    sut.getMinPath(
      triangle(
        Vector(
          Vector(7),
          Vector(6, 3),
          Vector(3, 8, 5),
          Vector(11, 2, 10, 9)
        )
      )
    ) shouldEqual TrianglePath(18, List(7, 6, 3, 2))
  }

  it should "solve case #2" in {
    sut.getMinPath(
      triangle(
        Vector(
          Vector(4),
          Vector(5, 6),
          Vector(1, 1, 1)
        )
      )
    ) shouldEqual TrianglePath(10, List(4, 5, 1))
  }

  it should "solve case #3" in {
    sut.getMinPath(
      triangle(
        Vector(
          Vector(2),
          Vector(3, 4),
          Vector(6, 5, 7),
          Vector(4, 1, 8, 3)
        )
      )
    ) shouldEqual TrianglePath(11, List(2, 3, 5, 1))
  }

  it should "solve case #4" in {
    sut.getMinPath(
      triangle(
        Vector(
          Vector(2),
          Vector(5, 4),
          Vector(5, 5, 7),
          Vector(1, 4, 8, 3)
        )
      )
    ) shouldEqual TrianglePath(13, List(2, 5, 5, 1))
  }

  it should "solve case #5" in {
    sut.getMinPath(
      triangle(
        Vector(
          Vector(2),
          Vector(3, 7),
          Vector(8, 5, 6),
          Vector(6, 1, 9, 3)
        )
      )
    ) shouldEqual TrianglePath(11, List(2, 3, 5, 1))
  }

  it should "solve case #6" in {
    sut.getMinPath(
      triangle(
        Vector(
          Vector(3),
          Vector(6, 4),
          Vector(5, 2, 7),
          Vector(9, 1, 8, 6)
        )
      )
    ) shouldEqual TrianglePath(10, List(3, 4, 2, 1))
  }

  it should "solve case #7 with 0s" in {
    sut.getMinPath(
      triangle(
        Vector(
          Vector(9),
          Vector(3, 8),
          Vector(0, 2, 4),
          Vector(8, 3, 9, 0),
          Vector(5, 2, 2, 7, 3),
          Vector(7, 9, 0, 2, 3, 9),
          Vector(9, 7, 0, 3, 9, 8, 6),
          Vector(5, 7, 6, 2, 7, 0, 3, 9)
        )
      )
    ) shouldEqual TrianglePath(19, List(9, 3, 0, 3, 2, 0, 0, 2))
  }

  it should "solve case #8 with negative number" in {
    sut.getMinPath(
      triangle(
        Vector(
          Vector(9),
          Vector(3, 8),
          Vector(0, 2, 4),
          Vector(8, 3, 9, 0),
          Vector(5, 2, 2, 7, 3),
          Vector(7, 9, 0, 2, 3, -200),
          Vector(9, 7, 0, 3, 9, 8, 6),
          Vector(5, 7, 6, 2, 7, 0, 3, 9)
        )
      )
    ) shouldEqual TrianglePath(-168, List(9, 8, 4, 0, 3, -200, 8, 0))
  }

  it should "solve case #9" in {
    sut.getMinPath(
      triangle(
        Vector(
          Vector(9),
          Vector(1, 7),
          Vector(2, 3, 6),
          Vector(5, 5, 8, 1),
          Vector(4, 7, 1, 3, 8),
          Vector(4, 8, 0, 4, 6, 0),
          Vector(3, 2, 6, 9, 4, 1, 3),
          Vector(7, 8, 8, 3, 8, 1, 5, 3),
          Vector(5, 4, 3, 6, 5, 9, 5, 4, 9),
          Vector(1, 7, 5, 5, 4, 1, 8, 8, 3, 5),
        )
      )
    ) shouldEqual TrianglePath(33, List(9, 1, 2, 5, 1, 0, 6, 3, 5, 1))
  }

  // TODO: could this be detected and more optimal?
  it should "solve all ones" in {
    sut.getMinPath(
      triangle(
        Vector(
          Vector(1),
          Vector(1, 1),
          Vector(1, 1, 1)
        )
      )
    ) shouldEqual TrianglePath(3, List(1, 1, 1))
  }

  it should "handle one line triangle" in {
    sut.getMinPath(triangle(Vector(Vector(1)))) shouldEqual TrianglePath(1, List(1))
  }

  it should "handle empty triangle" in {
    sut.getMinPath(triangle(Vector())) shouldEqual TrianglePath()
  }

  it should "return error for invalid triangle with more than 2 elements as first row" in {
    Triangle(
      Vector(
        Vector(1, 2),
        Vector(1)
      )
    ) shouldEqual Left("First row of triangle definition needs to have one element.")
  }

  it should "return error if not every row of triangle has one more item than previous one" in {
    Triangle(
      Vector(
        Vector(1),
        Vector(1, 2, 3),
        Vector(1, 2)
      )
    ) shouldEqual Left("Every row of triangle definition needs to have one more element than previous one.")
  }

  it should "properly parse triangle definition " in {
    Triangle.fromInput(List("1", "1 -2", "3 1 0").iterator) shouldEqual Right(
      triangle(
        Vector(
          Vector(1),
          Vector(1, -2),
          Vector(3, 1, 0)
        )
      )
    )
  }

  it should "return all parse errors if errors in triangle definition " in {
    Triangle
      .fromInput(List("1", "1 2", "a 1 x", "1 2 3 0.4").iterator)
      .left
      .map(_.replaceAll("\t", "")) shouldEqual Left(
      """Errors in the line 2 'a 1 x':
        |There's invalid input. 'a' is not a integer!
        |There's invalid input. 'x' is not a integer!
        |
        |Errors in the line 3 '1 2 3 0.4':
        |There's invalid input. '0.4' is not a integer!
        |""".stripMargin
    )
  }

  def triangle(rows: Vector[Vector[Int]]) = {
    Triangle(rows).toOption.get
  }
}
