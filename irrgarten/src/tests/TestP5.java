package tests;

import irrgarten.Game;
import irrgarten.UI.UserInterface;
import irrgarten.Directions;




public class TestP5 {
    public static void main(String[] args) {
        int nplayers = 3;
        Game game = new Game(nplayers);
        UserInterface view = new UserInterface();
        Directions nextdirection;
        while (!game.finished()){
            
            view.showGame(game.getGameState());
            nextdirection = view.nextMove();
            game.nextStep(nextdirection);
        }
        view.showGame(game.getGameState());
        try {
            Thread.sleep(5*1000);
         }
         catch (Exception e) {
            System.out.println(e);
         }
        view.dispose();
    }
}
