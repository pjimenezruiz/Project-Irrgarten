/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 * @author Juan Molina Molina
 * @author Paula Jiménez Ruiz
 */
class Monster extends LabyrinthCharacter{
    
    private static int INITIAL_HEALTH=5;
    
    public Monster(String name, float intelligence, float strenght) {
    
        super(name, intelligence, strenght, INITIAL_HEALTH);    // PREGUNTAR POR EL 5
    }
    
    @Override
    public float attack() {
    
        Dice dice=new Dice();
        float attack=dice.intensity(getStrength());
        return attack;
    }
    
    @Override
    public boolean defend(float receivedAttack) {

        boolean isDead=dead();
        if(!isDead) {
        
            Dice dice= new Dice();
            float defensiveEnergy=dice.intensity(getIntelligence());
            
            if(defensiveEnergy<receivedAttack) {
            
                gotWounded();
                isDead=dead();
            }
        }
        
        return isDead;
    }
    
    @Override
    public String toString() { 
    
        return "M"+super.toString();
    }
}
