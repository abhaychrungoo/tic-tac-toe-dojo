package dojo.ttt.swing;/*
* Copyright 2011 Yaana Technologies. All rights reserved.
* Yaana Technologies PROPRIETARY/CONFIDENTIAL.
*/

import javax.swing.*;

import dojo.ttt.Player;
import dojo.ttt.Position;

public class Button extends JButton {

    private final Position position;
    private Player player;

    public Button(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public boolean isFree() {
        return player == null;
    }

    public void setPlayer(Player player) {
        this.player = player;
        updateButtonText();
        setEnabled(false);
    }

    public void reset() {
        player = null;
        updateButtonText();
        setEnabled(true);
    }

    private void updateButtonText() {
        if (player == null) {
            setText(null);
        } else {
            setText(player.toString());
        }
    }
}
