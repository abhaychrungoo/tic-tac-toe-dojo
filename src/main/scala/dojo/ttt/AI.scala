package dojo.ttt

trait AI {

  /**
   * Find the best move for the given player.
   * @param board current game board
   * @param player token representing the current player
   * @return position that this player should mark next
   */
  def bestMove(board: GameBoard, player: PlayerToken): Position

}