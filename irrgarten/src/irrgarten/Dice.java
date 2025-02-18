/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;
import java.util.Random;
import java.util.ArrayList;

/**
<<<<<<< HEAD
 *
=======
>>>>>>> 1fe4584be61467713d9bee7c1973916b27fcf79a
 * @author Juan Molina Molina
 * @author Paula Jiménez Ruiz
 */
public class Dice {
    
    private static int MAX_USES = 5;
    private static float MAX_INTELLIGENCE = 10.0f;
    private static float MAX_STRENGTH = 10.0f;
    private static float RESURRECT_PROB = 0.3f;
    private static int WEAPONS_REWARD = 2;
    private static int SHIELDS_REWARD = 3;
    private static int HEALTH_REWARD = 5;
    private static int MAX_ATTACK = 3;
    private static int MAX_SHIELD = 2;
    private Random generator = new Random();
    
    public int randomPos (int max) {
        
        if(max>=0) {
            int pos = generator.nextInt(max);
            return pos;
        }
        else
            return 0;
    }
    
    int whoStarts(int nplayers){
        
        if(nplayers>=0) {
            int player = generator.nextInt(nplayers);
            return player;
        }
        else
            return 0;
    }
    
    public float randomIntelligence(){
        
        float intelligence = generator.nextFloat() * MAX_INTELLIGENCE;
        while(intelligence==MAX_INTELLIGENCE) {
        
            intelligence = generator.nextFloat() * MAX_INTELLIGENCE;
        }
        return intelligence;
    }
    
    public float randomStrength(){
        
        float strenght = generator.nextFloat() * MAX_STRENGTH;
        while(strenght==MAX_STRENGTH) {
        
            strenght = generator.nextFloat() * MAX_STRENGTH;
        }
        return strenght;
    }
    
    public boolean resurrectPlayer(){
        
        if(generator.nextFloat() <= RESURRECT_PROB) {
        
            return true;
        }
        
        else {
        
            return false;
        }
    }
    
    public int weaponsReward(){
        
        int wreward = generator.nextInt(WEAPONS_REWARD+1);
        return wreward;
    }
    
    public int shieldsReward(){
        
        int sreward = generator.nextInt(SHIELDS_REWARD+1);
        return sreward;
    }
    
    public int healthReward(){
        
        int hreward = generator.nextInt(HEALTH_REWARD+1);
        return hreward;
    }
    
    public float weaponPower(){
        
        float wpower = generator.nextFloat() * MAX_ATTACK;
        while(wpower==MAX_ATTACK) {
        
            wpower = generator.nextFloat() * MAX_ATTACK;
        }
        return wpower;
    }
    
    public float shieldPower(){
        
        float spower = generator.nextFloat() * MAX_SHIELD;
        while(spower==MAX_SHIELD) {
        
            spower = generator.nextFloat() * MAX_SHIELD;
        }
        return spower;
    }
    
    public int usesLeft(){
        
        int uses = generator.nextInt(MAX_USES+1);
        return uses;
    }
    
    public float intensity(float competence){
        
        float intens = generator.nextFloat() * competence;
        while(intens==competence) {
        
            intens = generator.nextFloat() * competence;
        }
        return intens;
    }
    
    public boolean discardElement(int usesLeft){
        
        if(usesLeft == MAX_USES) {
        
            return false;
        }
        
        else if (usesLeft==0) {
        
            return true;
        }
        
        else {
            if (generator.nextFloat() <= ( (float) usesLeft / MAX_USES))
                return false;
            else
                return true;
        }   
    }
    
    public Directions nextStep(Directions preference, ArrayList<Directions> validMoves, float intelligence) { //PREGUNTAR
        
        if (generator.nextFloat() <= ( (float) intelligence / MAX_INTELLIGENCE))
                return preference;
        else {
            if (validMoves.size()>=1){
                int i=generator.nextInt(validMoves.size()-1);
                return validMoves.get(i);
            }
            else 
                return validMoves.get(0);
        }
    }
}