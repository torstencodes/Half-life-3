import java.util.ArrayList;
import java.util.Arrays;

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
    public ArrayList<Game> priceHitoLo() {
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
        return gameList;
    }
    /*
     * Calls the priceHitoLo to sort the array from highest to lowest and then
     * just reverses that array. 
     */
    public ArrayList<Game> priceLotoHi() {
        ArrayList<Game> gameList = priceHitoLo();
        Game dummy = null;
        for (int i = 0; i < gameList.size(); i++) {
            dummy = gameList.get(i);
            gameList.set(i, gameList.get(gameList.size() - i - 1));
            gameList.set(gameList.size() - i - 1, dummy);
        }
        return gameList;
    } 
    public ArrayList<Game> sortRating() {
        ArrayList<Game> gameList = new ArrayList<Game>(Arrays.asList(games));
        
        return gameList;
    }
    public ArrayList<Game> alphaOrder() {
        ArrayList<Game> gameList = new ArrayList<Game>(Arrays.asList(games));
        
        
        return gameList;
    }
    public ArrayList<Game> sortCategory() {
        ArrayList<Game> gameList = new ArrayList<Game>(Arrays.asList(games));
        
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
