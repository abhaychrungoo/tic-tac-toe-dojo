package dojo.ttt

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite
import TestUtils._

@RunWith(classOf[JUnitRunner])
class AITest extends FunSuite {


  test("avoid immediate loss") {
    // X about to win by row combination (X will win for sure if marks [row=2, column=0])
    assert(ai.bestMove(boardOf(
      X, e, X,
      e, e, O,
      e, X, O
    ), turn = O) == new Position(row = 0, column = 1))

    // X about to win by diagonal combination (best possible outcome for O will be win)
    assert(ai.bestMove(boardOf(
      X, e, e,
      e, e, O,
      O, X, X
    ), turn = O) == new Position(row = 1, column = 1))

    // X about to win by column combination (best possible outcome for O will be draw)
    assert(ai.bestMove(boardOf(
      O, X, X,
      X, e, e,
      O, e, X
    ), turn = O) == new Position(row = 1, column = 2))

    // X about to win by diagonal combination (best possible outcome for O will be draw)
    assert(ai.bestMove(boardOf(
      O, X, e,
      e, X, O,
      X, O, X
    ), turn = O) == new Position(row = 0, column = 2))
  }


  test("pick immediate win position") {
    assert(ai.bestMove(boardOf(
      O, O, e,
      e, e, e,
      X, e, X
    ), turn = X) == new Position(row = 2, column = 1))

    assert(ai.bestMove(boardOf(
      X, e, O,
      e, O, X,
      X, e, O
    ), turn = X) == new Position(row = 1, column = 0))

    assert(ai.bestMove(boardOf(
      O, e, X,
      X, e, e,
      X, O, O
    ), turn = X) == new Position(row = 1, column = 1))
  }


  test("pick the last remaining available position") {
    assert(ai.bestMove(boardOf(
      O, e, X,
      X, O, O,
      X, O, X
    ), turn = X) == new Position(row = 0, column = 1))

    assert(ai.bestMove(boardOf(
      O, O, X,
      X, e, O,
      X, O, X
    ), turn = X) == new Position(row = 1, column = 1))

    assert(ai.bestMove(boardOf(
      O, X, X,
      X, O, O,
      e, O, X
    ), turn = X) == new Position(row = 2, column = 0))
  }


  test("win in 2 steps") {
    val bestMove = ai.bestMove(boardOf(
      X, O, X,
      e, e, O,
      e, X, O
    ), turn = X)
    assert(bestMove == new Position(row = 2, column = 0))
  }

}