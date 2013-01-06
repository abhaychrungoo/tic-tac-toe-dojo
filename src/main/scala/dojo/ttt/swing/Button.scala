package dojo.ttt.swing

import javax.swing.JButton
import java.awt.Dimension
import dojo.ttt.{PlayerToken, Empty, Token, Position}

class Button(val position: Position) extends JButton {

  private var token: Token = Empty

  setFont(getFont.deriveFont(100f))
  setText(label)
  setPreferredSize(new Dimension(120, 120))

  def isFree = token == Empty

  def reset() {
    token = Empty
    setText(label)
    setEnabled(true)
  }

  def setPlayer(player: PlayerToken) {
    token = player
    setText(label)
    setEnabled(false)
  }

  def label: String = if (isFree) null else token.toString

}