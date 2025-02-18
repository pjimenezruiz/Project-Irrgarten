package tests;

import irrgarten.Game;
import irrgarten.UI.UserInterface;
import irrgarten.controller.Controller;
import irrgarten.UI.Button;




public class TestP5_2 {
    public static void main(String[] args) {
        int nplayers = 2;
        Game game = new Game(nplayers);
        UserInterface view = new UserInterface();
        Controller controller = new Controller(game,view);
        controller.play();
        // view.dispose();
    }
}
