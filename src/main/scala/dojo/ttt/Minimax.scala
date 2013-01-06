package dojo.ttt

import util.Random

object Minimax extends AI {

  def bestMove(board: GameBoard, player: PlayerToken) = {
    if (board.isEmpty)
      randomCorner
    else {
      val positions = board.availablePositions
      val numberOfAvailablePositions = positions.length
      require(numberOfAvailablePositions > 0)
      if (numberOfAvailablePositions == 1) positions(0)
      else minimax(board, player, Maximizer, produceScoringFn(player), 0).pos
    }
  }

  def randomCorner = {
    val corners = List((0, 0), (0, 2), (2, 0), (2, 2))
    val (row, column) = Random.shuffle(corners).head
    new Position(row, column)
  }

  def produceScoringFn(player: PlayerToken) =
    (state: BoardState, depth: Int) => state match {
      case Play => 0
      case Draw => 1
      case _ => if (player.isWinner(state)) 100 - depth else -100 + depth
    }

  def minimax(board: GameBoard, player: PlayerToken, evaluator: Evaluator,
              score: (BoardState, Int) => Int, depth: Int): Move = {
    val positions = board.availablePositions
    val moves = positions.map(pos => {
      val newBoard = board.mark(pos, player)
      val state = newBoard.state
      if (state.isTerminal) new Move(pos, score(state, depth))
      else {
        val move = minimax(newBoard, player.opposite, evaluator.opposite, score, depth + 1)
        new Move(pos, move.score)
      }
    })
    evaluator.bestMove(moves)
  }

  class Move(val pos: Position, val score: Int)

  trait Evaluator {
    def bestMove(moves: Seq[Move]): Move
    def opposite: Evaluator
  }

  case object Minimizer extends Evaluator {
    def bestMove(moves: Seq[Move]) = moves.minBy(_.score)
    def opposite = Maximizer
  }

  case object Maximizer extends Evaluator {
    def bestMove(moves: Seq[Move]) = moves.maxBy(_.score)
    def opposite = Minimizer
  }

}