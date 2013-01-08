package dojo.ttt

object Minimax2 {
/*

  def bestMove(board: GameBoard, player: PlayerToken) = {
    val evaluate = evaluateFactory(player)
    val posAndScores = board.availablePositions.zip(nextScores(board, player, 0, evaluate, MaxNode))
    posAndScores.maxBy(_._2)._1
  }

  type EvaluateFn = (BoardState, Int) => Int

  def evaluateFactory(player: PlayerToken) =
    (state: BoardState, depth: Int) =>
      if (player.isWinner(state)) 100 - depth
      else if (player.opposite.isWinner(state)) -100 + depth
      else if (state == Draw) 1
      else 0

  def minimax(board: GameBoard, player: PlayerToken, depth: Int, evaluate: EvaluateFn, node: Node): Int =
    node.pick(nextScores(board, player, depth, evaluate, node))

  def nextScores(board: GameBoard, player: PlayerToken, depth: Int, evaluate: EvaluateFn, node: Node): Seq[Int] =
    board.availablePositions.map(pos => {
      val newBoard = board.mark(pos, player)
      if (newBoard.state.isTerminal) evaluate(newBoard.state, depth)
      else minimax(newBoard, player.opposite, depth + 1, evaluate, node.opposite)
    })

  trait Node {
    def opposite: Node
    def pick(scores: Seq[Int]): Int
  }

  case object MinNode extends Node {
    def opposite = MaxNode
    def pick(scores: Seq[Int]) = scores.min
  }

  case object MaxNode extends Node {
    def opposite = MinNode
    def pick(scores: Seq[Int]) = scores.max
  }
*/

}