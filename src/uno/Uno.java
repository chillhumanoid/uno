package uno;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random;

/**
 *
 * @author Jonathan Thorne Made from scratch to play Uno, will eventually add a
 * GUI, but for now it will be command line.
 *
 * all code made from scratch.
 */
public class Uno {

    static Random rn = new Random();
    static deal play1 = new deal();//for the user
    static deal comp1 = new deal();//for computer 1
    static deal comp2 = new deal();//for computer 2
    static deal comp3 = new deal(); //for computer 3
    static cardHandler deck = new cardHandler();
    static Scanner s = new Scanner(System.in);
    static Scanner si = new Scanner(System.in).useDelimiter(" *");
    static deal discardPile = new deal();

    public static void main(String[] args) {
        //card creator
        deck.shuffleDeck();
        for (int i = 0; i <= 6; i++) { //adds 7 cards to each players hand. 
            play1.addCard(deck);
            comp1.addCard(deck);
            comp2.addCard(deck);
            comp3.addCard(deck);
        }
        discardPile.addCard(deck.getLast());//adds the top card of the deck to the discard pile "face up"
        if(Card.getCardNumber(discardPile.getLast())== 14){
            discardPile.addCard(deck.getLast());
        }else if(Card.getCardNumber(discardPile.getLast())==13){
            discardPile.addCard(wildColor(13));
        }
        int currentPlayer = 1; //current player = 1 (user)
        boolean reverse = false; // will be used to denote a reverse card played. 
        boolean skip = false; //will be used to denote a skip card played
        boolean gameEnded = false; //will be set to true when the first player runs out of cards. 
        boolean draw2 = false;
        boolean draw4 = false;
        boolean uno = false;
        do {
            while (currentPlayer == 1) {
                skip = false; //added so that when it next goes to player 1, those values are reset. 
                draw2 = false; //^
                draw4 = false; // ^^
                boolean unoCalled = false;
                int cardPlayed = 0; //this is similar to a boolean. 1's and 0's ya know. 
                System.out.println(); //spacing
                int choice = 0;
                Boolean drawCard = false;
                do {//do this while card played = 0
                    uno = checkUno(play1);
                    System.out.println(); //spacing
                    printHand(play1, drawCard, uno, unoCalled); //show the user what is in their hand
                    System.out.println(); //spacing
                    printDiscard();
                    System.out.println("Player 1");
                    choice = getCardNumber();
                    int elem = choice - 1;
                    if (choice == (play1.getSize() + 1)) {
                        if(!drawCard){
                            play1.addCard(deck);
                            drawCard = true;
                        }else if(drawCard){
                            cardPlayed = 1;
                        }
                    }else if(choice == (play1.getSize()+2)){
                        if(!unoCalled){
                            System.out.println();
                            System.out.println("Player 1 Calls Uno");
                            unoCalled = true;
                        }else if(unoCalled){
                            System.out.println();
                            System.out.println("Please Select a Valid Card");
                        }
                    } else if (Card.getCardColor(play1.getCard(elem)) == 'a' && Card.getCardNumber(play1.getCard(elem)) == 13) { //gets the color of the card that user chose
                        discardPile.addCard(wildColor(13));
                        play1.removeCard(elem);//remove the card from the player deck
                        cardPlayed = 1;
                    } else if (Card.getCardColor(play1.getCard(elem)) == 'a' && Card.getCardNumber(play1.getCard(elem)) == 14) { //wild draw 4. 
                        discardPile.addCard(wildColor(14));
                        draw4 = true;
                        play1.removeCard(elem);
                        cardPlayed = 1;
                    } else if (Card.getCardColor(play1.getCard(elem)) == Card.getCardColor(discardPile.getLast()) || Card.getCardNumber(play1.getCard(elem)) == Card.getCardNumber(discardPile.getLast())) {
                        discardPile.addCard(play1.getCard(elem));
                        switch (Card.getCardNumber(play1.getCard((elem)))) {
                            case 10:
                                skip = true;
                                break;
                            case 11:
                                draw2 = true;
                                break;
                            case 12:
                                if (reverse) {
                                    reverse = false;
                                } else if (!reverse) {
                                    reverse = true;
                                } else {

                                }
                                break;
                            default:
                                break;
                        }
                        play1.removeCard(elem);
                        cardPlayed = 1;
                    } else {
                        System.out.println();
                        System.out.println("Please Select a valid card");
                    }
                } while (cardPlayed == 0);
                if(play1.getSize() == 1 && !unoCalled){
                    System.out.println("Player 1 did not call uno. +2");
                    play1.addCard(deck);
                    play1.addCard(deck);
                }
                if(play1.getSize() > 1 && unoCalled){
                    System.out.println("Player 1 falsely called uno. +2");
                    play1.addCard(deck);
                    play1.addCard(deck);
                }
                if (play1.getSize() == 0) {
                    System.out.println();
                    System.out.println("Player 1 won");
                    gameEnded = true;
                    System.exit(0);
                }
                if (reverse && skip || !reverse && skip) {
                    currentPlayer = 3;
                    if (draw2) {
                        draw2(comp2);
                    }
                    if (draw4) {
                        draw4(comp2);
                    }
                } else if (reverse && !skip) {
                    currentPlayer = 4;
                    if (draw2) {
                        draw2(comp3);
                    }
                    if (draw4) {
                        draw4(comp3);
                    }
                } else if (!reverse && !skip) {
                    currentPlayer = 2;
                    if (draw2) {
                        draw2(comp1);
                    }
                    if (draw4) {
                        draw4(comp1);
                    }
                }
                checkDraw(deck, discardPile);
                break;
                
            }
            while (currentPlayer == 2) {
                skip = false;
                draw2 = false;
                draw4 = false;
                int choice = 0;
                int cardPlayed = 0; //this is similar to a boolean. 1's and 0's ya know. 
                boolean drawCard = false;
                boolean unoCalled = false;
                do {//do this while card played = 0
                    uno = checkUno(comp1);
                    System.out.println(); //spacing
                    printHand(comp1, drawCard, uno, unoCalled); //show the user what is in their hand
                    System.out.println(); //spacing
                    System.out.println("Player 2");
                    printDiscard();
                    choice = getCardNumber();
                    int elem = choice - 1;
                    if (choice == (comp1.getSize() + 1)) {
                        if(!drawCard){
                            comp1.addCard(deck);
                            drawCard = true;
                        }else if(drawCard){
                            cardPlayed = 1;
                        }
                    }else if(choice == (comp1.getSize() + 2)){
                        if(!unoCalled){
                            System.out.println();
                            System.out.println("Player 2 Calls Uno");
                            unoCalled = true;
                        }else if(unoCalled){
                            System.out.println();
                            System.out.println("Please Select a Valid Card");
                        }
                    } else if (Card.getCardColor(comp1.getCard(elem)) == 'a' && Card.getCardNumber(comp1.getCard(elem)) == 13) {
                        discardPile.addCard(wildColor(13));
                        comp1.removeCard(elem);//remove the card from the player deck. it's no longer needed there
                        cardPlayed = 1;
                    } else if (Card.getCardColor(comp1.getCard(elem)) == 'a' && Card.getCardNumber(comp1.getCard(elem)) == 14) {
                        discardPile.addCard(wildColor(14));
                        draw4 = true;
                        comp1.removeCard(choice - 1);
                        cardPlayed = 1;
                    } else if (Card.getCardColor(comp1.getCard(elem)) == Card.getCardColor(discardPile.getLast()) || Card.getCardNumber(comp1.getCard(choice - 1)) == Card.getCardNumber(discardPile.getLast())) {
                        discardPile.addCard(comp1.getCard(elem));
                        switch (Card.getCardNumber(comp1.getCard(elem))) {
                            case 10:
                                skip = true;
                                break;
                            case 11:
                                draw2 = true;
                                break;
                            case 12:
                                if (reverse) {
                                    reverse = false;
                                } else if (!reverse) {
                                    reverse = true;
                                } else {

                                }
                                break;
                            default:
                                break;
                        }
                        comp1.removeCard(choice - 1);
                        cardPlayed = 1;
                    } else {
                        System.out.println();
                        System.out.println("Please Select a valid card");
                    }
                } while (cardPlayed == 0);
                if(comp1.getSize() == 1 && !unoCalled){
                    System.out.println("Player 2 did not call uno. +2");
                    comp1.addCard(deck);
                    comp1.addCard(deck);
                }
                if(comp1.getSize() > 1 && unoCalled){
                    System.out.println("Player 2 falsely called uno. +2");
                    comp1.addCard(deck);
                    comp1.addCard(deck);
                }
                if (comp1.getSize() == 0) {
                    System.out.println();
                    System.out.println("Player 2 won");
                    gameEnded = true;
                    System.exit(0);
                }
                if (reverse && skip || !reverse && skip) {
                    currentPlayer = 4;
                    if (draw2) {
                        draw2(comp3);
                    }
                    if (draw4) {
                        draw4(comp3);
                    }
                } else if (reverse && !skip) {
                    currentPlayer = 1;
                    if (draw2) {
                        draw2(play1);
                    }
                    if (draw4) {
                        draw4(play1);
                    }
                } else if (!reverse && !skip) {
                    currentPlayer = 3;
                    if (draw2) {
                        draw2(comp2);
                    }
                    if (draw4) {
                        draw4(comp2);
                    }
                }
                checkDraw(deck, discardPile);
                break;

            }
            while (currentPlayer == 3) {
                skip = false;
                draw2 = false;
                draw4 = false;
                int choice = 0;
                boolean drawCard = false;
                boolean unoCalled = false;
                int cardPlayed = 0; //this is similar to a boolean. 1's and 0's ya know. 
                do {//do this while card played = 0
                    uno = checkUno(comp2);
                    System.out.println(); //spacing
                    printHand(comp2, drawCard, uno, unoCalled);
                    System.out.println();
                    printDiscard();
                    System.out.println("Player 3");
                    choice = getCardNumber();
                    int elem = choice - 1;
                    if (choice == (comp2.getSize() + 1)) {
                        if(!drawCard){
                            comp2.addCard(deck);
                            drawCard = true;
                        }else if(drawCard){
                            cardPlayed = 1;
                        }
                    }else if(choice == (comp2.getSize()+2)){
                        if(!unoCalled){
                            System.out.println();
                            System.out.println("Player 3 Calls Uno");
                            unoCalled = true;
                        }else if(unoCalled){
                            System.out.println();
                            System.out.println("Please Select a Valid Card");
                        }
                    } else if (Card.getCardColor(comp2.getCard(elem)) == 'a' && Card.getCardNumber(comp2.getCard(elem)) == 13) { //gets the color of the card that user chose
                        comp2.removeCard(elem);//remove the card from the player deck. it's no longer needed there
                        discardPile.addCard(wildColor(13));
                        cardPlayed = 1;
                    } else if (Card.getCardColor(comp2.getCard(elem)) == 'a' && Card.getCardNumber(comp2.getCard(elem)) == 14) {
                        comp2.removeCard(elem);
                        discardPile.addCard(wildColor(14));
                        draw4 = true;
                        cardPlayed = 1;
                    } else if (Card.getCardColor(comp2.getCard(elem)) == Card.getCardColor(discardPile.getLast()) || Card.getCardNumber(comp2.getCard(elem)) == Card.getCardNumber(discardPile.getLast())) {
                        discardPile.addCard(comp2.getCard(choice - 1));
                        switch (Card.getCardNumber(comp2.getCard(choice - 1))) {
                            case 10:
                                skip = true;
                                break;
                            case 11:
                                draw2 = true;
                                break;
                            case 12:
                                if (reverse) {
                                    reverse = false;
                                } else if (!reverse) {
                                    reverse = true;
                                } else {

                                }
                                break;
                            default:
                                break;
                        }
                        comp2.removeCard(choice - 1);
                        cardPlayed = 1;
                    } else {
                        System.out.println();
                        System.out.println("Please Select a valid card");
                    }
                } while (cardPlayed == 0);
                if(comp2.getSize() == 1 && !unoCalled){
                    System.out.println("Player 3 did not call uno. +2");
                    comp2.addCard(deck);
                    comp2.addCard(deck);
                }
                if(comp2.getSize() > 1 && unoCalled){
                    System.out.println("Player 3 falsely called uno. +2");
                    comp2.addCard(deck);
                    comp2.addCard(deck);
                }
                if (comp2.getSize() == 0) {
                    System.out.println();
                    System.out.println("Player 3 won");
                    gameEnded = true;
                    System.exit(0);
                }
                if (reverse && skip || !reverse && skip) {
                    currentPlayer = 1;
                    if (draw2) {
                        draw2(play1);
                    }
                    if (draw4) {
                        draw4(play1);
                    }
                } else if (reverse && !skip) {
                    currentPlayer = 2;
                    if (draw2) {
                        draw2(comp1);
                    }
                    if (draw4) {
                        draw4(comp1);
                    }
                } else if (!reverse && !skip) {
                    currentPlayer = 4;
                    if (draw2) {
                        draw2(comp3);
                    }
                    if (draw4) {
                        draw4(comp3);
                    }
                }
                checkDraw(deck, discardPile);
                break;
            }
            while (currentPlayer == 4) {
                skip = false;
                draw2 = false;
                draw4 = false;
                int choice = 0;
                boolean unoCalled = false;
                boolean drawCard = false;
                int cardPlayed = 0; //this is similar to a boolean. 1's and 0's ya know. 
                do {//do this while card played = 0
                    uno = checkUno(comp3);
                    System.out.println();
                    printHand(comp3, drawCard, uno, unoCalled);
                    System.out.println();
                    printDiscard();
                    System.out.println("Player 4");
                    choice = getCardNumber();
                    int elem = choice - 1;
                    if (choice == (comp3.getSize() + 1)) {
                        if(!drawCard){
                            comp3.addCard(deck);
                            drawCard = true;
                        }else if(drawCard){
                            cardPlayed = 1;
                        }
                    }else if(choice == (comp3.getSize() + 2)){
                        if(!unoCalled){
                            System.out.println();
                            System.out.println("Player 4 calls Uno");
                            unoCalled = true;
                        }else if(unoCalled){
                            System.out.println();
                            System.out.println("Please select a valid option");
                        }
                    } else if (Card.getCardColor(comp3.getCard(elem)) == 'a' && Card.getCardNumber(comp3.getCard(elem)) == 13) { //gets the color of the card that user chose
                        comp3.removeCard(elem);//remove the card from the player deck. it's no longer needed there
                        discardPile.addCard(wildColor(13));
                        cardPlayed = 1;
                    } else if (Card.getCardColor(comp3.getCard(elem)) == 'a' && Card.getCardNumber(comp3.getCard(elem)) == 14) {
                        comp3.removeCard(elem);
                        discardPile.addCard(wildColor(13));
                        draw4 = true;
                        cardPlayed = 1;
                    } else if (Card.getCardColor(comp3.getCard(elem)) == Card.getCardColor(discardPile.getLast()) || Card.getCardNumber(comp3.getCard(elem)) == Card.getCardNumber(discardPile.getLast())) {
                        discardPile.addCard(comp3.getCard(elem));
                        switch (Card.getCardNumber(comp3.getCard(elem))) {
                            case 10:
                                skip = true;
                                break;
                            case 11:
                                draw2 = true;
                                break;
                            case 12:
                                if (reverse) {
                                    reverse = false;
                                } else if (!reverse) {
                                    reverse = true;
                                } else {

                                }
                                break;
                            default:
                                break;
                        }
                        comp3.removeCard(elem);
                        cardPlayed = 1;
                    } else {
                        System.out.println();
                        System.out.println("Please Select a valid card");
                    }
                } while (cardPlayed == 0);
                if(comp3.getSize() == 1 && !unoCalled){
                    System.out.println("Player 4 did not call uno. +2");
                    comp3.addCard(deck);
                    comp3.addCard(deck);
                }
                if(comp3.getSize() > 1 && unoCalled){
                    System.out.println("Player 4 falsely called uno. +2");
                    comp3.addCard(deck);
                    comp3.addCard(deck);
                }
                if (comp3.getSize() == 0) {
                    System.out.println();
                    System.out.println("Player 4 won");
                    gameEnded = true;
                    System.exit(0);
                }
                if (reverse && skip || !reverse && skip) {
                    currentPlayer = 2;
                    if (draw2) {
                        draw2(comp1);
                    }
                    if (draw4) {
                        draw4(comp1);
                    }
                } else if (reverse && !skip) {
                    currentPlayer = 3;
                    if (draw2) {
                        draw2(comp2);
                    }
                    if (draw4) {
                        draw4(comp2);
                    }
                } else if (!reverse && !skip) {
                    currentPlayer = 1;
                    if (draw2) {
                        draw2(play1);
                    }
                    if (draw4) {
                        draw4(play1);
                    }
                }
                checkDraw(deck, discardPile);
                break;
            }
        } while (!gameEnded);
    }

    public static void printHand(deal play1, boolean drawCard, boolean uno, boolean unoCalled) {
        int display = 0;
        for (int x = 0; x < play1.getSize(); x++) {
            display = x + 1;
            System.out.println(display + ". " + play1.getCard(x));
        }
        display++;
        if(!drawCard){
            System.out.println(display + ". Draw Card");
        }else if(drawCard){
            System.out.println(display + ". End Turn");
        }
        display++;
        if(uno && !unoCalled){
            System.out.println(display + ". Uno");
        }else if(uno && unoCalled){
            System.out.println("Uno Called");
        }else if(!uno){
            
        }
    }
    public static boolean checkUno(deal play){
        boolean uno = false;
        if(play.getSize() == 2){
            uno = true;
        }else{
            uno = false;
        }
        return uno;
    }
    public static Card wildColor(int cardNumber) {
        System.out.print("What color do you want the deck to be?: ");
        //s.next();
        String input = s.nextLine();
        char color = 'a';
        input = input.toLowerCase();
        switch (input.charAt(0)) {
            case 'b':
                color = 'b';
                break;
            case 'r':
                color = 'r';
                break;
            case 'g':
                color = 'g';
                break;
            case 'y':
                color = 'y';
                break;
            default:
                System.out.println("Type: (b)lue, (g)reen, (r)ed or (y)ellow");
                wildColor(cardNumber);
                break;
        }
        return new Card(cardNumber, color);
    }

    public static void draw2(deal play) {
        for (int i = 0; i <= 1; i++) {
            play.addCard(deck);
        }
    }

    public static void draw4(deal play) {
        for (int i = 0; i <= 3; i++) {
            play.addCard(deck);
        }
    }

    public static void printDiscard() {
        System.out.print("Discard Pile: ");
        System.out.println(discardPile.getLast());
    }
    public static void checkDraw(cardHandler deck, deal discardPile){
        if(deck.getSize() <= 4){
            for(int i = 0; i < discardPile.getSize(); i++){
                deck.addCard(discardPile.getLast());
                discardPile.removeLast();
            }
            deck.shuffleDeck();
            deck.shuffleDeck();
        }else{
            
        }
        
    }
    public static int getCardNumber() {
        int choice = 0;
        do{
            try {
                System.out.print("Which card do you want to play: ");
                choice = si.nextInt();
                si.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input");
                si.nextLine();
            }
        }while(choice == 0);
        return choice;
    }
}
