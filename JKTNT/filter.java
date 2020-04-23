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
    public ArrayList<Game> priceLotoHi() {
        Game dummy = null;
        ArrayList<Game> gameList = new ArrayList<Game>(Arrays.asList(games));
        for (int i = 0; i < gameList.size(); i++) {
            for (int j = 1; j < (gameList.size()); j++) {
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
    public ArrayList<Game> priceHitoLo() {
        ArrayList<Game> gameList = new ArrayList<Game>(Arrays.asList(games));
        Game dummy = null;
        for (int i = 0; i < gameList.size(); i++) {
            for (int j = 1; j < (gameList.size()); j++) {
                if (gameList.get(j - 1).getPrice() < gameList.get(j).getPrice()) {
                    dummy = gameList.get(j - 1);
                    gameList.set(j - 1, gameList.get(j));
                    gameList.set(j, dummy);
                }
            }
        }
        return gameList;
    } 
    public ArrayList<Game> sortRating() {
        ArrayList<Game> gameList = new ArrayList<Game>(Arrays.asList(games));
        
        return gameList;
    }
    /*
     * Returns array list of games in alphabetical order
     */
    public ArrayList<Game> atoZ() {
        ArrayList<Game> gameList = new ArrayList<Game>(Arrays.asList(games));
        Game dummy = null;
        for (int i = 0; i < gameList.size(); i++) {
            for (int j = i + 1; j < gameList.size(); j++) {
                if (gameList.get(i).getName().compareTo(gameList.get(j).getName()) > 0) {
                    dummy = gameList.get(i);
                    gameList.set(i, gameList.get(j));
                    gameList.set(j, dummy);
                }
            }
        }
        
        return gameList;
    }
    public ArrayList<Game> ztoA() {
        ArrayList<Game> gameList = new ArrayList<Game>(Arrays.asList(games));
        Game dummy = null;
        for (int i = 0; i < gameList.size(); i++) {
            for (int j = i + 1; j < gameList.size(); j++) {
                if (gameList.get(i).getName().compareTo(gameList.get(j).getName()) < 0) {
                    dummy = gameList.get(i);
                    gameList.set(i, gameList.get(j));
                    gameList.set(j, dummy);
                }
            }
        }
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
        CharSequence csq = query.toLowerCase();
        for (int i = 0; i < games.length; i++) {
            if (games[i].getName().toLowerCase().contains(csq)) {
                gameList.add(games[i]);
            }
        }
        return gameList;
    }
    
    public void setGames(Game[] gameList) {
        this.games = gameList;
    }
}
