package dojo.ttt;

public class SearchFactory {

    private SearchFactory() {}

    public static Search createSearch() {
        return ABPruning.INSTANCE;
    }

}