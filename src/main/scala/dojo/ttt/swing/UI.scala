package dojo.ttt.swing

import javax.swing._
import java.awt.{BorderLayout, FlowLayout}
import java.awt.event.KeyEvent._
import javax.swing.KeyStroke._
import java.awt.event.ActionEvent._
import javax.swing.JFrame._
import java.awt.event.{ActionEvent, ActionListener}
import dojo.ttt._

class UI {

  var board = new SeqBoard
  var human: PlayerToken = X
  var computer: PlayerToken = human.opposite
  val ai = Minimax

  val panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 2, 2))

  val buttons = for (row <- 0 to 2; column <- 0 to 2) yield new Button(new Position(row, column))
  buttons.foreach(b => b.addActionListener(clickHandler(b)))
  buttons.foreach(panel.add)

  val restartGameMenuItem = new JMenuItem("Restart", VK_R)
  restartGameMenuItem.setAccelerator(getKeyStroke(VK_R, CTRL_MASK))
  restartGameMenuItem.addActionListener(restartGame())

  val exitMenuItem = new JMenuItem("Exit", VK_X)
  exitMenuItem.setAccelerator(getKeyStroke(VK_Q, CTRL_MASK))
  exitMenuItem.addActionListener(System.exit(0))

  val gameMenu = new JMenu("Game")
  gameMenu.setMnemonic(VK_G)
  gameMenu.add(restartGameMenuItem)
  gameMenu.addSeparator()
  gameMenu.add(exitMenuItem)

  val menuBar = new JMenuBar
  menuBar.add(gameMenu)

  val frame = new JFrame("Tic-Tac-Toe")
  frame.setJMenuBar(menuBar)
  frame.add(panel, BorderLayout.CENTER)
  frame.setDefaultCloseOperation(EXIT_ON_CLOSE)
  frame.setSize(376, 419)
  frame.setResizable(false)
  frame.setVisible(true)
  frame.setLocationRelativeTo(null)

  def toggleEmptyButtons(enabled: Boolean) {
    buttons.foreach(b => if (b.isFree) b.setEnabled(enabled))
  }

  def clickHandler(button: Button) {
    button.setPlayer(human)
    board = board.mark(button.position, human)
    if (board.state.isTerminal) {
      showResultDialog(board.state)
      restartGame()
    } else
      computerMove()
  }

  def getButtonAt(position: Position) = buttons.find(_.position == position).get

  def restartGame() {
    board = new SeqBoard
    buttons.foreach(_.reset())
    human = human.opposite
    computer = human.opposite
    if (computer == X)
      computerMove()
  }

  def computerMove() {
    toggleEmptyButtons(enabled = false)
    val position = ai.bestMove(board, computer)
    getButtonAt(position).setPlayer(computer)
    board = board.mark(position, computer)
    if (board.state.isTerminal) {
      showResultDialog(board.state)
      restartGame()
    } else
      toggleEmptyButtons(enabled = true)
  }

  def showResultDialog(result: BoardState) {
    def resultMessage(result: BoardState) = result match {
      case Draw => "It's a tie!"
      case WinX => "Player X won!"
      case WinO => "Player O won!"
      case _ => throw new IllegalStateException("Game still in progress")
    }
    JOptionPane.showMessageDialog(frame, resultMessage(result))
  }

  implicit def function2ActionListener(f: => Unit): ActionListener =
    new ActionListener {
      def actionPerformed(e: ActionEvent) { f }
    }
}