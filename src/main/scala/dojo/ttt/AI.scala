package dojo.ttt

trait AI {

  def bestMove(board: GameBoard, turn: PlayerToken): Position

}