package irrgarten;

/**
<<<<<<< HEAD
 *
=======
 * @author Juan Molina Molina
 * @author Paula Jiménez Ruiz
>>>>>>> 1fe4584be61467713d9bee7c1973916b27fcf79a
 */
public class GameState {

    
    private String labyrinth;
    private String players;
    private String monsters;
    private int currentPlayer;
    private boolean winner;
    private String log;
    
    public GameState(String l, String p, String m, int cP, boolean w, String lo) {
    
        labyrinth=l;
        players=p;
        monsters=m;
        currentPlayer=cP;
        winner=w;
        log= lo;
    }
    
    public String getLabyrinth() {
    
        return labyrinth;
    }
    
    public String getPlayers() {
    
        return players;
    }
    
    public String getMonsters() {
    
        return monsters;
    }
    
    public int getCurrentPlayer() {
    
        return currentPlayer;
    }
    
    public boolean isWinner() {
    
        return winner;
    }
    
    public String getLog() {
    
        return log;
    }

}
