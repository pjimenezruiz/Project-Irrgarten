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
public class Labyrinth {
    
    private static char BLOCK_CHAR='X';
    private static char EMPTY_CHAR='-';
    private static char MONSTER_CHAR='M';
    private static char COMBAT_CHAR='C';
    private static char EXIT_CHAR='E';
    private static int ROW=0;
    private static int COL=1;
    private int nRows;
    private int nCols;
    private int exitRow;
    private int exitCol;
    
    private Monster[][] monster;
    private Player[][] player;
    private char[][] character;
    
    public Labyrinth(int nRows, int nCols, int exitRow, int exitCol) {
           
        this.nRows=nRows;
        this.nCols=nCols;
        this.exitRow=exitRow;
        this.exitCol=exitCol;
        monster = new Monster[this.nRows][this.nCols];
        player = new Player[this.nRows][this.nCols];
        character = new char[this.nRows][this.nCols];
        
        for (int i=0; i<this.nRows;i++){
            for (int j=0; j<this.nCols;j++){
                character[i][j]=EMPTY_CHAR;
            }
        }
        character[exitRow][exitCol]=EXIT_CHAR;
    }
    
    public void spreadPlayers(ArrayList<Player> players) {

        for (int i= 0; i < players.size(); i++){
            int pos[]=randomEmptyPos();
            putPlayer2D(-1, -1, pos[0], pos[1], players.get(i));
        }
    }
    
    public boolean haveAWinner() {
    
        if(player[exitRow][exitCol]==null) {
        
            return false;
        }
        
        else
            return true;
    }
    
    public String toString() {
        
        String salida = "";
        
        for (int i=0; i<nRows;i++) {
            
            for (int j=0; j<nCols;j++) {
                String aux = character[i][j] + " ";
                salida = salida.concat(aux);
            }
            salida = salida.concat("\n");
        }
        
        return salida;
    }
    
    public void addMonster(int row, int col, Monster monster) {
        
        if ((row <nRows) && (col < nCols) && (character[row][col] == EMPTY_CHAR)){
            this.monster[row][col] = monster;
            character[row][col] = MONSTER_CHAR;
        }
   
    }
    
    public Monster putPlayer(Directions direction, Player player) {

        int oldRow=player.getRow();
        int oldCol=player.getCol();
        int[] newPos= dir2Pos(oldRow, oldCol, direction);
        Monster monster=putPlayer2D(oldRow, oldCol, newPos[ROW], newPos[COL], player);
        
        return monster;
    }
    
    public void addBlock(Orientation orientation, int startRow, int startCol, int lenght) {
    
        int incRow, incCol;
        if (orientation== Orientation.HORIZONTAL){
            incRow = 0;
            incCol = 1;
        }
        else {
            incRow = 1;
            incCol = 0;
        }

        int row = startRow;
        int col = startCol;

        while (posOK(row,col)&&(emptyPos(row,col))&&(lenght>0)){

            character[row][col] = BLOCK_CHAR;
            lenght--;

            row += incRow;
            col += incCol;
        }
    }
    
    public ArrayList<Directions> validMoves(int row, int col) {
    
        ArrayList<Directions> output = new ArrayList<Directions>();
        
        if(canStepOn(row+1, col)) {
        
            output.add(Directions.DOWN);
        }
        
        if(canStepOn(row-1, col)) {
        
            output.add(Directions.UP);
        }
        
        if(canStepOn(row, col+1)) {
        
            output.add(Directions.RIGHT);
        }
        
        if(canStepOn(row, col-1)) {
        
            output.add(Directions.LEFT);
        }
        
        return output;
    }
    
    public void putFuzzyPlayer(FuzzyPlayer fuzzyplayer){
        
        player[fuzzyplayer.getRow()][fuzzyplayer.getCol()] = fuzzyplayer;
    }
    
    private boolean posOK(int row, int col) {
    
        if ((row < nRows) && (col < nCols) && (row >= 0) && (col >= 0)){
            
            return true;
        }
        else 
            return false;
        
    }
    
    private boolean emptyPos(int row, int col) {
        
        if (character[row][col] == EMPTY_CHAR) {
            return true;
        }
        else 
            return false;
        
    }
    
    private boolean monsterPos(int row, int col) {
    
        if (character[row][col] == MONSTER_CHAR) {
            return true;
        }
        else {
            return false;
        }
    }
    
    private boolean exitPos(int row, int col) {
    
        if (character[row][col] == EXIT_CHAR) {
            return true;
        }
        else {
            return false;
        }
    }
    
    private boolean combatPos(int row, int col) {

        if (character[row][col] == COMBAT_CHAR) {
            return true;
        }
        else {
            return false;
        }
    }
    
    private boolean canStepOn(int row, int col) {
    
        if (posOK(row,col)) {
            
            if (emptyPos(row,col) || monsterPos(row,col) || exitPos(row,col)) {
                
                return true;
            }
            
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }
    
    private void updateOldPos(int row, int col) {
    
        if (posOK(row,col)) {
            
            if (combatPos(row,col)) {
                
                character[row][col]=MONSTER_CHAR;
            }
            else {
                character[row][col]=EMPTY_CHAR;
            }
        }
    }
    
    private int[] dir2Pos(int row, int col, Directions direction) {
    
        int salida []= new int [2];
        
        if (direction == Directions.LEFT){
            
            salida[0]=row;
            salida[1]=col-1;
        }
        else if (direction == Directions.RIGHT){
            
            salida[0]=row;
            salida[1]=col+1;
        }
        else if (direction == Directions.UP){
            
            salida[0]=row-1;
            salida[1]=col;
        }
        else {
            
            salida[0]=row+1;
            salida[1]=col;
        }
        return salida;
    }
    
    private int[] randomEmptyPos() {
    
        Dice dice = new Dice();
        
        int salida []= new int [2];
        int row, col;
        do {
            row = dice.randomPos(nRows);
            col = dice.randomPos(nCols);
        } while (!emptyPos(row,col));
        
        salida[0]=row;
        salida[1]=col;
        return salida;
    }
    
    private Monster putPlayer2D(int oldRow, int oldCol, int row, int col, Player player) {
    
        Monster output = null;
        if (canStepOn(row, col)){

            if (posOK(oldRow,oldCol)){
                Player p = this.player[oldRow][oldCol];
                if (p==player){
                    this.player[oldRow][oldCol]=null;
                    updateOldPos(oldRow,oldCol);
                }
            }

            boolean monsterpos = monsterPos(row,col);

            if (monsterpos== true){
                character[row][col] = COMBAT_CHAR;
                output = monster[row][col];
            }

            else {
                character[row][col]= player.getNumber();
            }
            this.player[row][col] = player;
            player.setPos(row, col);
        }

        return output;
    }
}
