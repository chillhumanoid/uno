/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uno;

import java.util.ArrayList;

/**
 *
 * @author jonat
 */
public class deal extends cardHandler{
    private ArrayList<Card> hand;
    public deal(){
        hand = new ArrayList<>();
    }
    public void addCard(){
        hand.add(cards.get(cards.size()-1));
        cards.remove(cards.size()-1);
    }
    public void addCard(uno.Card addCard){
        hand.add(addCard);
    }
    public void removeCard(int elem){
        hand.remove(elem);
    }
    public Card getCard(int elem){
        return hand.get(elem);
    }
    public int getSize(){
        return hand.size();
    }
    public void printArray(){
        System.out.println(hand.toString());
    }
    public Card getLast(){
        return hand.get(hand.size()-1);
    }
}
