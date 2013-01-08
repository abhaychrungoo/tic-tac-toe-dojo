package dojo.ttt.swing

import dojo.ttt.AlphaBetaPruning

object GameLauncher {
  def main(args: Array[String]) {
    new UI(ai = new AlphaBetaPruning)
  }
}