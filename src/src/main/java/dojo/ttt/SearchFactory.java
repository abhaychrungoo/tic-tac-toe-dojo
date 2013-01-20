package dojo.ttt;

public class SearchFactory {

    private SearchFactory() {}

    public static Search createSearch() {
        return new TicTacToeMinMax(); //TODO create an instance of your implementation here
    }

}