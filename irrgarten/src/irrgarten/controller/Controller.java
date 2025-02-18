package irrgarten.controller;

import irrgarten.Directions;
import irrgarten.Game;
import irrgarten.UI.UI;


public class Controller {
    
    private Game game;
    private UI view;
    
    public Controller(Game game, UI view) {
        this.game = game;
        this.view = view;
    }
    
    public void play() {
        boolean endOfGame = false;
        while (!endOfGame) {
            view.showGame(game.getGameState()); 
            boolean pasar=view.pasarTurno();
            if (pasar==true) {
            
                game.nextPlayer();
            }
            view.showGame(game.getGameState());
            Directions direction = view.nextMove(); // Se delega en cursors para obtener la dirección
            boolean conservar=view.robarTurno();
            if(conservar==false) {
            
                
            }
            endOfGame = game.nextStep(direction);
        }
      view.showGame(game.getGameState());        
    }
    
}
