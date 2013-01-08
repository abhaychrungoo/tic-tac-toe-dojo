package dojo.ttt;

import java.util.Collection;

public interface Board {

    Collection<Position> getAvailablePositions();

    Board mark(Position position, Player player);

    BoardState getState();

}