package dojo.ttt

sealed trait BoardState {

  /**
   * @return true if current state represents a final game board (e.g win condition or draw)
   */
  def isTerminal: Boolean = true

}

/**
 * Means that there are still empty positions where to put a player token.
 */
case object Play extends BoardState {
  override def isTerminal = false
}

/**
 * The game board is full, but nobody won.
 */
case object Draw extends BoardState

/**
 * Player X won.
 */
case object WinX extends BoardState

/**
 * Player O won.
 */
case object WinO extends BoardState