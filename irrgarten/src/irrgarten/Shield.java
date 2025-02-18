package irrgarten;

/**
<<<<<<< HEAD
 *
=======
>>>>>>> 1fe4584be61467713d9bee7c1973916b27fcf79a
 * @author Juan Molina Molina
 * @author Paula Jiménez Ruiz
 */
class Shield extends CombatElement{
    
    public Shield (float p, int u) {
        
        super(p,u);
    }
    
    public float protect () {
    
        return super.produceEffect();
    }
    
    @Override
    public String toString() {
    
        return "S"+super.toString();
    }
    
}
