package dojo.ttt

sealed trait BoardState2 {

  /**
   * @return true if current state represents a final game board (e.g win condition or draw)
   */
  def isTerminal: Boolean = true

}

/**
 * Means that there are still empty positions where to put a player token.
 */
case object Play extends BoardState2 {
  override def isTerminal = false
}

/**
 * The game board is full, but nobody won.
 */
case object Draw extends BoardState2

/**
 * Player X won.
 */
case object WinX extends BoardState2

/**
 * Player O won.
 */
case object WinO extends BoardState2