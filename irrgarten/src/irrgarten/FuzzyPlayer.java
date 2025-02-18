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
class FuzzyPlayer extends Player{
        
    public FuzzyPlayer(Player other) {  
    
        super(other); 
    }
    
    @Override
    public Directions move(Directions directions, ArrayList<Directions> validMoves) {
        
        Dice dice=new Dice();
        Directions preferredDirection=super.move(directions, validMoves);
        return dice.nextStep(preferredDirection, validMoves, super.getIntelligence());
    }
    
    @Override
    public float attack() {
    
        return super.attack();
    }
    
    @Override
    protected float defensiveEnergy() {
    
        return super.defensiveEnergy();
    }
    
    @Override
    public String toString() {
    
        return "Fuzzy"+super.toString();
    }
}
