package dojo.ttt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public final class TicTacToeUtils {

    private TicTacToeUtils(){}

    /**
     * Provides all the position combinations that would result in a win.
     */
    public static final Collection<Collection<Position>> WIN_COMBINATIONS =
        Collections.unmodifiableCollection(createWinCombinations());

    private static Collection<Collection<Position>> createWinCombinations() {
        final Collection<Collection<Position>> combinations = new ArrayList<Collection<Position>>();

        for (int i = 0; i <= 2; i++) {
            final List<Position> rowCombination = new ArrayList<Position>(3);
            final List<Position> columnCombination = new ArrayList<Position>(3);

            for (int j = 0; j <= 2; j++) {
                rowCombination.add(new Position(i, j));
                columnCombination.add(new Position(j, i));
            }

            combinations.add(rowCombination);
            combinations.add(columnCombination);
        }

        List<Position> leftToRight = new ArrayList<Position>(3);
        for (int i = 0; i <= 2; i++) {
            leftToRight.add(new Position(i, i));
        }
        combinations.add(leftToRight);

        List<Position> rightToLeftDiagonal = new ArrayList<Position>(3);
        for (int i = 0, j = 2; i <= 2; i++, j--) {
            rightToLeftDiagonal.add(new Position(i, j));
        }
        combinations.add(rightToLeftDiagonal);

        return combinations;
    }

}