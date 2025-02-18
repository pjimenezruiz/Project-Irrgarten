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
class Player extends LabyrinthCharacter{
    
    private static int MAX_WEAPONS=2;
    private static int MAX_SHIELDS=3;
    private static int INITIAL_HEALTH=10;
    private static int HITS2LOSE=3;
    
    private char number;
    private int consecutiveHits=0;
    
    private WeaponCardDeck weaponCardDeck;
    private ShieldCardDeck shieldCardDeck;
    
    private ArrayList<Weapon> weapons;
    private ArrayList<Shield> shields;
    
    public Player(char number, float intelligence, float strenght) {
    
        super("Player#"+number, intelligence, strenght, INITIAL_HEALTH);    
        this.number=number;
        weapons=new ArrayList<Weapon>();
        shields=new ArrayList<Shield>();
        weaponCardDeck = new WeaponCardDeck();
        shieldCardDeck = new ShieldCardDeck();
    }
    
    public Player(Player other) { 
        super(other);
        this.number=other.number;
        this.weapons=other.weapons;
        this.shields=other.shields;
        this.weaponCardDeck=other.weaponCardDeck;
        this.shieldCardDeck=other.shieldCardDeck;
    }
    
    public void resurrect() {
    
        setHealth(INITIAL_HEALTH);
        consecutiveHits=0;
        
        weapons=new ArrayList<Weapon>();
        shields=new ArrayList<Shield>();
    }
    
    public char getNumber() {
    
        return number;
    }
    
    public Directions move(Directions direction, ArrayList<Directions> validMoves) {

        int size=validMoves.size();
        boolean contained=validMoves.contains(direction);
        if((size>0) && (!contained)) {
        
            Directions firstElement=validMoves.get(0);
            return firstElement;
        }
        
        else {
        
            return direction;
        }
    } 
    
    @Override
    public float attack() {
    
        float fuerza=getStrength()+sumWeapons();
        return fuerza;
    }
    
    @Override
    public boolean defend(float receivedAttack) {
    
        return manageHit(receivedAttack);
    }
    
    public void receiveReward() {

        Dice dice=new Dice();
        int wReward=dice.weaponsReward();
        int sReward=dice.shieldsReward();
        
        for(int i=1; i<wReward; i++) {
        
            Weapon wnew=newWeapon();
            receiveWeapon(wnew);
        }
        
        for(int i=1; i<sReward; i++) {
        
            Shield snew=newShield();
            receiveShield(snew);
        }
        
        int extraHealth=dice.healthReward();
        super.setHealth(super.getHealth()+extraHealth);
        
    }
    
    @Override
    public String toString() {  
    
        return "P"+super.toString();
    }
    
    private void receiveWeapon(Weapon w) {

        for(int i=0; i<weapons.size(); i++) {
        
            Weapon wi=weapons.get(i);
            boolean discard=wi.discard();
            
            if(discard) {
            
                weapons.remove(wi);
            }
        }
        
        int size=weapons.size();
        
        if(size<MAX_WEAPONS) {
        
            weapons.add(w);
        }
    }
    
    private void receiveShield(Shield s) {

        for(int i=0; i<shields.size(); i++) {
        
            Shield si=shields.get(i);
            boolean discard=si.discard();
            
            if(discard) {
            
                shields.remove(si);
            }
        }
        
        int size=shields.size();
        
        if(size<MAX_SHIELDS) {
        
            shields.add(s);
        }
    }
    
    private Weapon newWeapon() {
        
        return weaponCardDeck.nextCard();
    }
    
    private Shield newShield() {
        
        return shieldCardDeck.nextCard();
    } 
    
    protected float sumWeapons() {
    
        float sum=0f;
        for(int i = 0; i < weapons.size(); i++) {  
        
            sum+=weapons.get(i).attack();
        }
            
        return sum;
    }
    
    protected float sumShields() {
    
        float sum=0f;
        for(int i = 0; i < shields.size(); i++) {  
        
            sum+=shields.get(i).protect();
        }
        
        return sum;
    }
    
    protected float defensiveEnergy() {
    
        float inteligencia=super.getIntelligence()+sumShields();
        return inteligencia;
    }
    
    private boolean manageHit(float receivedAttack) {

        boolean lose;
        float defense=defensiveEnergy();
        if(defense<receivedAttack) {
        
            gotWounded();
            incConsecutiveHits();
        }
        
        else {
        
            resetHits();
        }
        
        if((consecutiveHits==HITS2LOSE) || (dead())) {
        
            resetHits();
            lose=true;
        }
        
        else {
        
            lose=false;
        }
        
        return lose;
    }
    
    private void resetHits() {
    
        consecutiveHits=0;
    }
    
    private void incConsecutiveHits() {
    
        ++consecutiveHits;
    }
}
