package dojo.ttt

class SeqBoard(grid: Seq[Seq[Token]]) extends GameBoard {

  def this() = this(Iterator.continually(Empty).take(3 * 3).grouped(3).toSeq)

  def isEmpty = grid.flatten.forall(_ == Empty)

  lazy val availablePositions =
    for {
      rowIdx <- 0 to 2
      row = grid(rowIdx)
      columnIdx <- 0 to 2
      token = row(columnIdx)
      if (token == Empty)
    } yield new Position(rowIdx, columnIdx)

  def mark(pos: Position, player: PlayerToken) = {
    val row = grid(pos.row)
    require(row(pos.column) == Empty)
    val newRow = row.updated(pos.column, player)
    val newGrid = grid.updated(pos.row, newRow)
    new SeqBoard(newGrid)
  }

  lazy val state = {
    def win(token: PlayerToken) =
      GameBoard.winCombinations.exists(_.forall(pos =>
        grid(pos.row)(pos.column) == token))

    if (win(X)) WinX
    else if (win(O)) WinO
    else if (availablePositions.isEmpty) Draw
    else Play
  }
}