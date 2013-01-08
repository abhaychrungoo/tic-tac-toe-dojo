package dojo.ttt

object TestUtils {

  def e = Empty

  def emptyBoard: GameBoard = new SeqBoard

  def boardOf(tokens: Token*): GameBoard = new SeqBoard(tokens.grouped(3).toSeq)

  def ai: AI = new AlphaBetaPruning

}