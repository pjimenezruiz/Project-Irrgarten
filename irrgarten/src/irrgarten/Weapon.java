package irrgarten;
/**
<<<<<<< HEAD
 *
=======
>>>>>>> 1fe4584be61467713d9bee7c1973916b27fcf79a
 * @author Juan Molina Molina
 * @author Paula Jiménez Ruiz
 * @brief La clase Weapon representa las armas que cada jugador puede utilizar durante un combate
 */
class Weapon extends CombatElement{
    
    public Weapon (float p, int u) {
    
        super(p,u);
    }
    
    public float attack () {
    
        return super.produceEffect();
    }
    
    @Override
    public String toString() {
    
        return "W"+super.toString();
    }
    
}
