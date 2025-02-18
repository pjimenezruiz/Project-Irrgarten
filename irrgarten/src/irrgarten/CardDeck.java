/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Juan Molina Molina
 * @author Paula Jiménez Ruiz
 */
public abstract class CardDeck<T extends CombatElement> {
    
    private ArrayList<T> cardDeck;
    
    public CardDeck(){
        cardDeck = new ArrayList<T>();
    }
    
    protected void addCard(T card){
        cardDeck.add(card);
    }
    
    protected abstract void addCards();
    
    public T nextCard(){
        if (cardDeck.isEmpty()){
            addCards();
            Collections.shuffle(cardDeck);
        }
        T card = cardDeck.get(0);
        cardDeck.remove(0);
        return card;
    }
}
