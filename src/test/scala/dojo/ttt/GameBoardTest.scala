package dojo.ttt

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite

@RunWith(classOf[JUnitRunner])
class GameBoardTest extends FunSuite {

  def emptyBoard: GameBoard = new SeqBoard

  def boardOf(tokens: Token*): GameBoard = new SeqBoard(tokens.grouped(3).toSeq)

  test("empty and non-empty game board") {
    assert(emptyBoard.isEmpty)
    val nonEmptyBoard = emptyBoard.mark(new Position(row = 0, column = 0), X)
    assert(!nonEmptyBoard.isEmpty)
  }

  test("available positions") {
    val positions = for (row <- 0 to 2; column <- 0 to 2) yield new Position(row, column)
    assert(emptyBoard.availablePositions == positions)
    val fullBoard = positions.foldLeft(emptyBoard)((board, pos) => board.mark(pos, X))
    assert(fullBoard.availablePositions.isEmpty)
  }

  test("game board state") {
    assert(emptyBoard.state == Play)

    assert(boardOf(
      X, O, X,
      X, O, X,
      O, X, O
    ).state == Draw)

    def testWinCombinations(player: PlayerToken) {
      GameBoard.winCombinations.foreach(combinations => {
        val board = combinations.foldLeft(emptyBoard)((board, pos) => board.mark(pos, player))
        assert(player.isWinner(board.state))
      })
    }

    testWinCombinations(X)
    testWinCombinations(O)
  }

}