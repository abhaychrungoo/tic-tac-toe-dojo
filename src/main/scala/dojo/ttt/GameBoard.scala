package dojo.ttt

/**
 * A 3 x 3 tic-tac-toe game board:
 *
 * 1 | 2 | 3
 * ---------
 * 4 | 5 | 6
 * ---------
 * 7 | 8 | 9
 *
 * Rows are indexed from 0 to 2, for example:
 * row 0 corresponds to 1, 2, 3
 * row 2 corresponds to 7, 8, 9
 *
 * Columns are also indexed from 0 to 2, for example:
 * column 0 corresponds to 1, 4, 7
 * column 2 corresponds to 3, 6, 9
 */
trait GameBoard {

  /**
   * @see Token
   * @return true if all board cells are empty, false otherwise
   */
  def isEmpty: Boolean

  /**
   * @see Position
   * @return sequence of positions that are not yet occupied by X or O
   */
  def availablePositions: Seq[Position]

  /**
   * This function is used to update the game board.
   * @param pos row/column position where to place the player token
   * @param player X or O
   * @return board that represents the updated grid
   * @throws IllegalArgumentException if position is already occupied by X or O
   */
  def mark(pos: Position, player: PlayerToken): GameBoard

  /**
   * @see BoardState
   * @return calculated state of the board (Play, Draw, WinX, WinO)
   */
  def state: BoardState

}

object GameBoard {

  private val rowWinCombinations =
    for (row <- 0 to 2) yield
      for (column <- 0 to 2) yield new Position(row, column)

  private val columnWinCombinations =
    for (column <- 0 to 2) yield
      for (row <- 0 to 2) yield new Position(row, column)

  private val diagonalWinCombinations = List(
    List(new Position(0, 0), new Position(1, 1), new Position(2, 2)),
    List(new Position(0, 2), new Position(1, 1), new Position(2, 0)))

  /**
   * All the possible win combinations (rows, columns, diagonals) represented as position sequences
   */
  val winCombinations: Seq[Seq[Position]] = rowWinCombinations ++ columnWinCombinations ++ diagonalWinCombinations

}