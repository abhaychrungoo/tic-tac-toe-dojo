package dojo.ttt

/**
 * Token represents the concept of what can be put on the game board at some position.
 */
sealed trait Token2

/**
 * This token is used to denote that the game board does not contain anything at the given position.
 */
case object Empty extends Token2


/**
 * Represents a token that a player can use: either X or O.
 */
sealed trait PlayerToken extends Token2 {

  /**
   * @return the opponent symbol of this player
   */
  def opposite: PlayerToken

  /**
   * @param state board state
   * @return true if the state means that the player using this symbol won
   */
  def isWinner(state: BoardState): Boolean

}

/**
 * Token that represents the player who is playing as X.
 */
case object X extends PlayerToken {
  def opposite = O
  def isWinner(state: BoardState) = state == WinX
}

/**
 * Token that represents the player who is playing as O.
 */
case object O extends PlayerToken {
  def opposite = X
  def isWinner(state: BoardState) = state == WinO
}