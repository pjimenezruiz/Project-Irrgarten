/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

import java.util.ArrayList;

/**
 * @author Juan Molina Molina
 * @author Paula Jiménez Ruiz
 */
public class Game {
    
    private static int MAX_ROUNDS=10;  // Atributo de clase
    private static int N_ROWS=5;
    private static int N_COLS=5;
    private static int E_ROW= 4;
    private static int E_COL= 4;
    public int currentPlayerIndex;
    private String log;
    
    private ArrayList<Player> players;
    private ArrayList<Monster> monsters;
    private Labyrinth labyrinth;
    
    public Game(int nplayers) {
    
        players = new ArrayList<Player>();
        monsters = new ArrayList<Monster>();
        Dice dice=new Dice();
        for (int i=0; i<nplayers; i++){
            char number = (char) (i+48);
            Player player = new Player(number, dice.randomIntelligence(), dice.randomStrength());
            players.add(player);
        }
        currentPlayerIndex = dice.whoStarts(nplayers);
        labyrinth = new Labyrinth (N_ROWS,N_COLS,E_ROW,E_COL);
        
        labyrinth.spreadPlayers(players);
        configureLabyrinth();
    }
    
    public boolean finished() {
    
        return labyrinth.haveAWinner();
    }
    
    public boolean nextStep(Directions preferredDirection) {
    
        log = "";
        boolean dead = players.get(currentPlayerIndex).dead();
        if (!dead){
            Directions direction = actualDirection(preferredDirection);
            
            if (direction != preferredDirection){
                logPlayerNoOrders();
            }
            
            Monster monster = labyrinth.putPlayer(direction, players.get(currentPlayerIndex));
            
            if (monster == null) {
                logNoMonster();
            }
            else {
                GameCharacter winner = combat(monster);
                manageReward(winner);
            }
        }
        else {
            manageResurrection();
        }
        
        boolean endGame = finished();
        
        if (!endGame){
            nextPlayer();
        }
        return endGame;
    }
    
    public GameState getGameState() {
    
        GameState gamestate = new GameState(labyrinth.toString(),players.toString(),
            monsters.toString(),currentPlayerIndex,labyrinth.haveAWinner(),log);
        return gamestate;
    }
    
    private void configureLabyrinth() {
    
        Dice dice = new Dice();
        int n_squares = N_ROWS*N_COLS;
        for (int i = 0; i<n_squares/20; i++){
            int row = dice.randomPos(N_ROWS);
            int col = dice.randomPos(N_COLS);
            labyrinth.addBlock(Orientation.HORIZONTAL, row, col,N_ROWS);
            row = dice.randomPos(N_ROWS);
            col = dice.randomPos(N_COLS);
            labyrinth.addBlock(Orientation.VERTICAL, row, col,N_COLS);
        }
        
        for (int i = 0; i<n_squares/5; i++){
            int row = dice.randomPos(N_ROWS);
            int col = dice.randomPos(N_COLS);;
            Monster monster = new Monster("Monster #"+i,dice.randomIntelligence(),dice.randomStrength());
            monsters.add(monster);
            labyrinth.addMonster(row, col, monster);
        }
        
    }
    
    public void nextPlayer() {
        
        currentPlayerIndex = (currentPlayerIndex+1)%players.size();
    }
    
    
    private Directions actualDirection(Directions preferedDirection) {
    
        int currentRow = players.get(currentPlayerIndex).getRow();
        int currentCol = players.get(currentPlayerIndex).getCol();
        
        ArrayList <Directions> preferredDirections = labyrinth.validMoves(currentRow, currentCol);
        
        Directions output =players.get(currentPlayerIndex).move(preferedDirection, preferredDirections);
        
        return output;
    }
    
    private GameCharacter combat(Monster monster) {
    
        int rounds = 0;
        GameCharacter winner = GameCharacter.PLAYER;
        
        float playerAttack = players.get(currentPlayerIndex).attack();
        boolean lose = monster.defend(playerAttack);
        
        while ((!lose) && (rounds < MAX_ROUNDS)){
            winner = GameCharacter.MONSTER;
            rounds++;
            
            float monsterAttack = monster.attack();
            lose = players.get(currentPlayerIndex).defend(monsterAttack);
            
            if (!lose){
                playerAttack = players.get(currentPlayerIndex).attack();
                winner = GameCharacter.PLAYER;
                lose = monster.defend(playerAttack);
            }
        }
        logRounds(rounds,MAX_ROUNDS);
        
        return winner;
    }
    
    private void manageReward(GameCharacter winner) {
        
        if (winner == GameCharacter.PLAYER){
            
            players.get(currentPlayerIndex).receiveReward();
            logPlayerWon();
        }
        else {
            logMonsterWon();
        }
    }
    
    private void manageResurrection() {

        Dice dice= new Dice();
        boolean resurrect = dice.resurrectPlayer();

        if(resurrect){
            players.get(currentPlayerIndex).resurrect();
            FuzzyPlayer fuzzyplayer =new FuzzyPlayer(players.get(currentPlayerIndex));
            players.set(currentPlayerIndex, fuzzyplayer);
            labyrinth.putFuzzyPlayer(fuzzyplayer);
            logResurrected();
        }
        else {
            logPlayerSkipTurn();
        }
    }
    
    private void logPlayerWon() {
    
        log = log.concat("Player won \n");
    }
    
    private void logMonsterWon() {
    
        log = log.concat("Monster won \n");
    }
    
    private void logResurrected() {
    
        log = log.concat("Player resurrected \n");
    }
    
    private void logPlayerSkipTurn() {
    
        log =log.concat("Player lost turn \n");
    }
    
    private void logPlayerNoOrders() {
    
        log = log.concat("Player couldn't follow the orders \n");
    }
    
    private void logNoMonster() {
    
        log = log.concat("Player moved to a empty square or couldn't move \n");
    }
    
    private void logRounds(int rounds, int max) {
    
        String logline = rounds+"/"+max+ " combat rounds \n";
        log = log.concat(logline);
    }
    
}
