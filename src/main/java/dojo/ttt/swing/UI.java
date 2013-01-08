package dojo.ttt.swing;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import dojo.ttt.Board;
import dojo.ttt.BoardFactory;
import dojo.ttt.BoardState;
import dojo.ttt.Player;
import dojo.ttt.Position;
import dojo.ttt.Search;
import dojo.ttt.SearchFactory;

public class UI {

    private Board board = BoardFactory.createBoard();
    private Search search = SearchFactory.createSearch();
    private Player human = Player.X;
    private Player computer = Player.O;

    private JFrame frame;
    private List<Button> buttons = new ArrayList<Button>(9);

    public UI() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 2, 2));
        ActionListener clickHandler = new ClickHandler();

        for (int row = 0; row <= 2; row++) {
            for (int column = 0; column <= 2; column++) {
                final Button button = new Button(new Position(row, column));
                button.addActionListener(clickHandler);
                panel.add(button);
                buttons.add(button);
            }
        }

        JMenuItem restartGameMenuItem = new JMenuItem("Restart", KeyEvent.VK_R);
        restartGameMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.CTRL_MASK));
        restartGameMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restartGame();
            }
        });

        JMenuItem exitMenuItem = new JMenuItem("Exit", KeyEvent.VK_X);
        exitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_MASK));
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JMenu gameMenu = new JMenu("Game");
        gameMenu.setMnemonic(KeyEvent.VK_G);
        gameMenu.add(restartGameMenuItem);
        gameMenu.addSeparator();
        gameMenu.add(exitMenuItem);

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(gameMenu);

        frame = new JFrame("Tic-Tac-Toe");
        frame.setJMenuBar(menuBar);
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(376, 419);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    private String getBoardStateText(BoardState state) {
        switch (state) {
            case WIN_X:
                return "X won!";
            case WIN_O:
                return "O won!";
            case DRAW:
                return "It's a draw!";
            case PLAY:
                return "Game still in progress...";
            default:
                throw new IllegalArgumentException("Illegal board state " + state);
        }
    }

    private void showResultDialog(String message) {
        JOptionPane.showMessageDialog(frame, message);
    }

    private void restartGame() {
        for (Button button : buttons) {
            button.reset();
        }

        board = BoardFactory.createBoard();
        human = computer;
        computer = human;

        if (computer == Player.X) {
            computerMove();
        }
    }

    private void computerMove() {
        setFreeButtonsEnabled(false);
        Position position = search.getBestMove(board, computer);
        getButtonAt(position).setPlayer(computer);
        board = board.mark(position, computer);
        BoardState state = board.getState();

        if (state.terminal) {
            showResultDialog(getBoardStateText(state));
            restartGame();
        } else {
            setFreeButtonsEnabled(true);
        }
    }

    private void setFreeButtonsEnabled(boolean enabled) {
        for (Button button : buttons) {
            if (button.isFree()) {
                button.setEnabled(enabled);
            }
        }
    }

    private Button getButtonAt(Position position) {
        Button button = null;

        for (Button b : buttons) {
            if (b.getPosition().equals(position)) {
                button = b;
                break;
            }
        }

        return button;
    }

    class ClickHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Button button = (Button) e.getSource();
            button.setPlayer(human);
            board = board.mark(button.getPosition(), human);
            BoardState state = board.getState();

            if (state.terminal) {
                showResultDialog(getBoardStateText(state));
                restartGame();
            } else {
                computerMove();
            }
        }
    }
}