import java.util.ArrayList;

public class filter {
    private Game[] games;
    /*
     * Basic constructor
     */
    public filter(Game[] list) {
        games = list;
    }
    /*
     * Using bubble sort to sort games based on price going from high to low
     */
    public Game[] priceHitoLo() {
        Game dummy = null;
        ArrayList<Game> gameList = new ArrayList<Game>();
        for (int i = 0; i < games.length; i++) {
            for (int j = 1; j < (games.length - 1); j++) {
                if (gameList.get(j - 1).getPrice() > gameList.get(j).getPrice()) {
                    dummy = gameList.get(j - 1);
                    gameList.set(j - 1, gameList.get(j));
                    gameList.set(j, dummy);
                }
            }
        }
        return (Game[]) gameList.toArray();
    }
    /*
     * Calls the priceHitoLo to sort the array from highest to lowest and then
     * just reverses that array. 
     */
    public Game[] priceLotoHi() {
        Game[] gameList = priceHitoLo();
        Game dummy = null;
        for (int i = 0; i < gameList.length; i++) {
            dummy = gameList[i];
            gameList[i] = gameList[gameList.length - i - 1];
            gameList[gameList.length - i - 1] = dummy;
        }
        return gameList;
    } 
    /*
     * Takes a query to filter games by name and returns an array of games
     * that are to be displayed after filtering.
     *
     */
    public ArrayList<Game> search(final String query) {
        ArrayList<Game> gameList = new ArrayList<Game>();
        CharSequence csq = query;
        for (int i = 0; i < games.length - 1; i++) {
            if (games[i].getName().contains(csq)) {
                gameList.add(games[i]);
            }
        }
        return gameList;
    }
    
    public void setGames(Game[] gameList) {
        this.games = gameList;
    }
}
