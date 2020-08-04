package zarucki.recruitment

// TODO: better OOP representation?
case class Triangle(rows: Array[Array[Int]]) {
  // if there is at least one row it is of size 1
  require(rows.headOption.map(_.size).forall(_ == 1))

  // if there is more than one row, the difference between consecutive rows is 1
  require(
    rows.size == 1 ||
      rows
        .view
        .map(_.size)
        .sliding(2)
        .forall { pair =>
          val (first :: second :: Nil) = pair.toList
          println(s"$second $first")
          second - first == 1
        }
  )
}
