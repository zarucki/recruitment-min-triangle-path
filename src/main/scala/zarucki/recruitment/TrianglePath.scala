package zarucki.recruitment

case class TrianglePath(totalOfPath: Int = 0, pathFromTopToBottom: List[Int] = Nil) {
  def withPrepended(pathFragment: Int): TrianglePath = {
    copy(
      totalOfPath = totalOfPath + pathFragment,
      pathFromTopToBottom = pathFragment :: pathFromTopToBottom
    )
  }
}

object TrianglePath {
  def ofOne(oneFragment: Int) = TrianglePath(oneFragment, List(oneFragment))
}
