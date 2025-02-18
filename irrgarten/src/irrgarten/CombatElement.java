/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 * @author Juan Molina Molina
 * @author Paula Jiménez Ruiz
 */
abstract class CombatElement {
    
    private int uses;
    private float effect;
    
    public CombatElement(float effect, int uses) {
    
        this.effect=effect;
        this.uses=uses;
    }
    
    protected float produceEffect() {
    
        if(uses>0) {
        
            --uses;
            return effect;
        }
        
        else {
        
            return 0;
        }
    }
    
    public boolean discard() {
    
        Dice dice=new Dice();
        return dice.discardElement(uses);
    }
    
    @Override
    public String toString() {
    
        String cad="[" + effect + ", " + uses + "]";
        return cad;
    }
}
