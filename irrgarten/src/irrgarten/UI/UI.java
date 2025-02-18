
package irrgarten.UI;

import irrgarten.Directions;
import irrgarten.GameState;

/**
 * @author Juan Molina Molina
 * @author Paula Jiménez Ruiz
 */
public interface UI {
    public Directions nextMove();
    public boolean pasarTurno();
    public boolean robarTurno();
    public void showGame(GameState gameState);
}
