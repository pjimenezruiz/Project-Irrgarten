/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 * @author Juan Molina Molina
 * @author Paula Jiménez Ruiz
 */
class WeaponCardDeck extends CardDeck<Weapon>{
    @Override
    protected void addCards(){
        
        Weapon weaponCard1 = new Weapon (5,2);
        super.addCard(weaponCard1);
        
        Weapon weaponCard2 = new Weapon (4,3);
        super.addCard(weaponCard2);
        
        Weapon weaponCard3 = new Weapon (3,4);
        super.addCard(weaponCard3);
        
        Weapon weaponCard4 = new Weapon (2,4);
        super.addCard(weaponCard4);
        
        Weapon weaponCard5 = new Weapon (1,3);
        super.addCard(weaponCard5);
    }
}
