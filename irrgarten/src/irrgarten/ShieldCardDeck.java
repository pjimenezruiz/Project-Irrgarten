/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 * @author Juan Molina Molina
 * @author Paula Jiménez Ruiz
 */
class ShieldCardDeck extends CardDeck<Shield>{
    @Override
    protected void addCards(){
        
        Shield shieldCard1 = new Shield (4,2);
        super.addCard(shieldCard1);
        
        Shield shieldCard2 = new Shield (3,3);
        super.addCard(shieldCard2);
        
        Shield shieldCard3 = new Shield (3,4);
        super.addCard(shieldCard3);
        
        Shield shieldCard4 = new Shield (2,4);
        super.addCard(shieldCard4);
        
        Shield shieldCard5 = new Shield (1,3);
        super.addCard(shieldCard5);
    }
}
