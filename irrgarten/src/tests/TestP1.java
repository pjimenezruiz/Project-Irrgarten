
package irrgarten;

/**
<<<<<<< HEAD
 *
 * @author Juan Molina Molina
 * @author Paula Jiménez Ruiz
>>>>>>> 1fe4584be61467713d9bee7c1973916b27fcf79a
 */
public class TestP1 {
    
    public static void main (String []args){
    
        /**
         * Constructores
         */
        
        Weapon weapon=new Weapon(0.5f, 5);
        Shield shield=new Shield(0.6f,5);
        Dice dice=new Dice();
        GameState gamestate=new GameState("laberinto", "jugadoras", "monstruos", 2, false, "");
        
        /**
         * Atacar, defender
         */
        
        System.out.println("---------------------------------------------");
        System.out.println("Ataque:");
        System.out.println(weapon.attack());
        System.out.println("Protección:");
        System.out.println(shield.protect());
        System.out.println("---------------------------------------------");
        
        /**
         * Getters, setters, to_string
         */
        
        System.out.println("---------------------------------------------");
        System.out.println("To Strings:");
        System.out.println(weapon.toString());
        System.out.println(shield.toString());
        
        System.out.println("---------------------------------------------");
        
        System.out.println("GameState:");
        System.out.println(gamestate.getCurrentPlayer());
        System.out.println(gamestate.getLabyrinth());
        System.out.println(gamestate.getLog());
        System.out.println(gamestate.getMonsters());
        System.out.println(gamestate.getPlayers());
        System.out.println(gamestate.isWinner());
        
        System.out.println("---------------------------------------------");
        
        System.out.println(dice.randomPos(10));
        System.out.println(dice.whoStarts(3));
        System.out.println(dice.randomIntelligence());
        System.out.println(dice.randomStrength());
        System.out.println(dice.resurrectPlayer());
        System.out.println(dice.weaponsReward());
        System.out.println(dice.shieldsReward());
        System.out.println(dice.healthReward());
        System.out.println(dice.weaponPower());
        System.out.println(dice.shieldPower());
        System.out.println(dice.usesLeft());
        System.out.println(dice.intensity(0.5f));
        System.out.println(dice.discardElement(4));
        System.out.println("---------------------------------------------");
        
        /**
         * Otros
         */
        
        System.out.println("---------------------------------------------");
        System.out.println("Usos:");
        System.out.println(weapon.toString());
        System.out.println(shield.toString());
        System.out.println("Descartar arma:");
        System.out.println(weapon.discard());
        System.out.println("Descartar escudo:");
        System.out.println(shield.discard());
        
    }
}
