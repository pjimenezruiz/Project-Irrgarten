/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 *
 * @author Juan Molina Molina
 * @author Paula Jiménez Ruiz
 */
abstract class LabyrinthCharacter {
    
    private String name;
    private float intelligence;
    private float strenght;
    private float health;
    private int row;
    private int col;
    
    public LabyrinthCharacter(String name, float intelligence, float strenght, float health) {
    
        this.name=name;
        this.intelligence=intelligence;
        this.strenght=strenght;
        this.health=health;
    }
    
    public LabyrinthCharacter(LabyrinthCharacter other) {  // Sintaxis importante
    
        this(other.name, other.intelligence, other.strenght, other.health);
        this.row = other.row;
        this.col = other.col;
    }
    
    public boolean dead() {
    
        if(health==0) {
            return true;
        }
        else
            return false;
    }
    
    public int getRow() {
    
        return row;
    }
    
    public int getCol() {
    
        return col;
    }
    
    protected float getIntelligence() {
    
        return intelligence;
    }
    
    protected float getStrength() {
    
        return strenght;
    }
    
    protected float getHealth() {
    
        return health;
    }
    
    protected void setHealth(float health) {
    
        this.health=health;
    }
    
    public void setPos(int row, int col) {
    
        this.row=row;
        this.col=col;
    }
    
    @Override
    public String toString() {
    
        return "["+name+", "+intelligence+", "+strenght+", "+health+"]";
    }
    
    protected void gotWounded() {
    
        --health;
    }
    
    public abstract float attack();
    
    public abstract boolean defend(float attack);
}
