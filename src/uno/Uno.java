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
    static Scanner si = new Scanner(System.in);
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
        discardPile.addCard(deck);//adds the top card of the deck to the discard pile "face up"
        if(Card.getCardNumber(discardPile.getLast()) == 14){
            discardPile.addCard(wildColor(14));
        }else if(Card.getCardNumber(discardPile.getLast())==13){
            discardPile.addCard(wildColor(13));
        }
        //deck.printDeck();
        int currentPlayer = 1; //current player = 1 (user)
        boolean reverse = false; // will be used to denote a reverse card played. 
        boolean skip = false; //will be used to denote a skip card played
        boolean gameEnded = false; //will be set to true when the first player runs out of cards. 
        boolean draw2 = false;
        boolean draw4 = false;
        boolean uno = false;
        do {
            while (currentPlayer == 1) {
                /*skip = false;
                draw2 = false;
                draw4 = false;
                int choice = 0;
                int cardPlayed = 0; //this is similar to a boolean. 1's and 0's ya know. 
                boolean drawCard = false;
                boolean unoCalled = false;
                do {//do this while card played = 0
                    uno = checkUno(play1);
                    //System.out.println(); //spacing
                    printHand(play1, drawCard, uno, unoCalled); //show the user what is in their hand
                    //System.out.println(); //spacing
                    printDiscard();
                    System.out.println("Player 1: ");
                    Card dCard = discardPile.getLast();
                    choice = getComputerChoice(dCard, play1, unoCalled);
                    int elem = choice - 1;
                    if (choice == (play1.getSize() + 1)) {
                        if(!drawCard){
                            //System.out.println();
                            System.out.println("Player 1 has drawn a card");
                            play1.addCard(deck);
                            drawCard = true;
                        }else if(drawCard){
                            //System.out.println();
                            System.out.println("Player 1 has ended their turn without playing a card");
                            cardPlayed = 1;
                        }
                    }else if(choice == (play1.getSize() + 2)){
                        if(!unoCalled){
                            ///System.out.println();
                            System.out.println("Player 1 Calls Uno");
                            unoCalled = true;
                        }else if(unoCalled){
                            //System.out.println();
                            //System.out.println("Please Select a Valid Card");
                        }
                    } else if (Card.getCardNumber(play1.getCard(elem)) == 13) {
                        discardPile.addCard(wildComputerColor(13, play1));
                        //System.out.println();
                        System.out.println("Player 1 has played a wild card");
                        play1.removeCard(elem);//remove the card from the player deck. it's no longer needed there
                        cardPlayed = 1;
                    } else if (Card.getCardNumber(play1.getCard(elem)) == 14) {
                        discardPile.addCard(wildComputerColor(14, play1));
                        //System.out.println();
                        System.out.println("Player 1 has played a wild draw 4");
                        draw4 = true;
                        play1.removeCard(choice - 1);
                        cardPlayed = 1;
                    } else if (Card.getCardColor(play1.getCard(elem)) == Card.getCardColor(discardPile.getLast()) || Card.getCardNumber(play1.getCard(choice - 1)) == Card.getCardNumber(discardPile.getLast())) {
                        discardPile.addCard(play1.getCard(elem));
                        switch (Card.getCardNumber(play1.getCard(elem))) {
                            case 10:
                                //System.out.println();
                                System.out.println("Player 1 has played a skip card");
                                skip = true;
                                break;
                            case 11:
                                //System.out.println();
                                System.out.println("Player 1 has played a draw 2 card");
                                draw2 = true;
                                break;
                            case 12:
                                //System.out.println();
                                System.out.println("Player 1 has played a reverse card");
                                if (reverse) {
                                    reverse = false;
                                } else if (!reverse) {
                                    reverse = true;
                                } else {

                                }
                                break;
                            case 9:
                                System.out.println("Player 1 has played a normal card");
                                break;
                            case 8:
                                System.out.println("Player 1 has played a normal card");
                                break;
                            case 7:
                                System.out.println("Player 1 has played a normal card");
                                break;
                            case 6:
                                System.out.println("Player 1 has played a normal card");
                                break;
                            case 5:
                                System.out.println("Player 1 has played a normal card");
                                break;
                            case 4:
                                System.out.println("Player 1 has played a normal card");
                                break;
                            case 3:
                                System.out.println("Player 1 has played a normal card");
                                break;
                            case 2:
                                System.out.println("Player 1 has played a normal card");
                                break;
                            case 1:
                                System.out.println("Player 1 has played a normal card");
                                break;
                            case 0:
                                System.out.println("Player 1 has played a normal card");
                                break;
                            default:
                                break;
                        }
                        //System.out.println();
                        //System.out.println("Computer 1 has played a card");
                        play1.removeCard(choice - 1);
                        cardPlayed = 1;
                    } else {
                        //System.out.println();
                        //System.out.println("Please Select a valid card");
                    }
                } while (cardPlayed == 0);
                if(play1.getSize() == 1 && !unoCalled){
                    System.out.println("Player 1 did not call uno. +2");
                    play1.addCard(deck);
                    play1.addCard(deck);
                }
                if (play1.getSize() == 0) {
                    System.out.println();
                    System.out.println("Player 1 won");
                    gameEnded = true;
                    System.exit(0);
                }*/
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
                    while(choice >= play1.getSize() || choice < 0){
                        System.out.println("Invalid Input");
                        choice = getCardNumber();
                    }
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
                    //System.out.println(); //spacing
                    //printHand(comp1, drawCard, uno, unoCalled); //show the user what is in their hand
                    //System.out.println(); //spacing
                    printDiscard();
                    System.out.println("Computer 1: ");
                    Card dCard = discardPile.getLast();
                    choice = getComputerChoice(dCard, comp1, unoCalled);
                    int elem = choice - 1;
                    if (choice == (comp1.getSize() + 1)) {
                        if(!drawCard){
                            //System.out.println();
                            System.out.println("Computer 1 has drawn a card");
                            comp1.addCard(deck);
                            drawCard = true;
                        }else if(drawCard){
                            //System.out.println();
                            System.out.println("Computer 1 has ended their turn without playing a card");
                            cardPlayed = 1;
                        }
                    }else if(choice == (comp1.getSize() + 2)){
                        if(!unoCalled){
                            ///System.out.println();
                            System.out.println("Computer 1 Calls Uno");
                            unoCalled = true;
                        }else if(unoCalled){
                            //System.out.println();
                            //System.out.println("Please Select a Valid Card");
                        }
                    } else if (Card.getCardNumber(comp1.getCard(elem)) == 13) {
                        discardPile.addCard(wildComputerColor(13, comp1));
                        //System.out.println();
                        System.out.println("Computer 1 has played a wild card");
                        comp1.removeCard(elem);//remove the card from the player deck. it's no longer needed there
                        cardPlayed = 1;
                    } else if (Card.getCardNumber(comp1.getCard(elem)) == 14) {
                        discardPile.addCard(wildComputerColor(14, comp1));
                        //System.out.println();
                        System.out.println("Computer 1 has played a wild draw 4");
                        draw4 = true;
                        comp1.removeCard(choice - 1);
                        cardPlayed = 1;
                    } else if (Card.getCardColor(comp1.getCard(elem)) == Card.getCardColor(discardPile.getLast()) || Card.getCardNumber(comp1.getCard(choice - 1)) == Card.getCardNumber(discardPile.getLast())) {
                        discardPile.addCard(comp1.getCard(elem));
                        switch (Card.getCardNumber(comp1.getCard(elem))) {
                            case 10:
                                //System.out.println();
                                System.out.println("Computer 1 has played a skip card");
                                skip = true;
                                break;
                            case 11:
                                //System.out.println();
                                System.out.println("Computer 1 has played a draw 2 card");
                                draw2 = true;
                                break;
                            case 12:
                                //System.out.println();
                                System.out.println("Computer 1 has played a reverse card");
                                if (reverse) {
                                    reverse = false;
                                } else if (!reverse) {
                                    reverse = true;
                                } else {

                                }
                                break;
                            case 9:
                                System.out.println("Computer 1 has played a normal card");
                                break;
                            case 8:
                                System.out.println("Computer 1 has played a normal card");
                                break;
                            case 7:
                                System.out.println("Computer 1 has played a normal card");
                                break;
                            case 6:
                                System.out.println("Computer 1 has played a normal card");
                                break;
                            case 5:
                                System.out.println("Computer 1 has played a normal card");
                                break;
                            case 4:
                                System.out.println("Computer 1 has played a normal card");
                                break;
                            case 3:
                                System.out.println("Computer 1 has played a normal card");
                                break;
                            case 2:
                                System.out.println("Computer 1 has played a normal card");
                                break;
                            case 1:
                                System.out.println("Computer 1 has played a normal card");
                                break;
                            case 0:
                                System.out.println("Computer 1 has played a normal card");
                                break;
                            default:
                                break;
                        }
                        //System.out.println();
                        //System.out.println("Computer 1 has played a card");
                        comp1.removeCard(choice - 1);
                        cardPlayed = 1;
                    } else {
                        //System.out.println();
                        //System.out.println("Please Select a valid card");
                    }
                } while (cardPlayed == 0);
                if(comp1.getSize() == 1 && !unoCalled){
                    System.out.println("Computer 1 did not call uno. +2");
                    comp1.addCard(deck);
                    comp1.addCard(deck);
                }
                if (comp1.getSize() == 0) {
                    System.out.println();
                    System.out.println("Computer 1 won");
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
                int cardPlayed = 0; //this is similar to a boolean. 1's and 0's ya know. 
                boolean drawCard = false;
                boolean unoCalled = false;
                do {//do this while card played = 0
                    uno = checkUno(comp2);
                    //System.out.println(); //spacing
                    //printHand(comp2, drawCard, uno, unoCalled); //show the user what is in their hand
                    //System.out.println(); //spacing
                    printDiscard();
                    System.out.println("Computer 2");
                    Card dCard = discardPile.getLast();
                    choice = getComputerChoice(dCard, comp2, unoCalled);
                    int elem = choice - 1;
                    if (choice == (comp2.getSize() + 1)) {
                        if(!drawCard){
                            //System.out.println();
                            System.out.println("Computer 2 has drawn a card");
                            comp2.addCard(deck);
                            drawCard = true;
                        }else if(drawCard){
                            //System.out.println();
                            System.out.println("Computer 2 has ended their turn without playing a card");
                            cardPlayed = 1;
                        }
                    }else if(choice == (comp2.getSize() + 2)){
                        if(!unoCalled){
                            //System.out.println();
                            System.out.println("Computer 2 Calls Uno");
                            unoCalled = true;
                        }else if(unoCalled){
                            //System.out.println();
                            //System.out.println("Please Select a Valid Card");
                        }
                    } else if (Card.getCardNumber(comp2.getCard(elem)) == 13) {
                        discardPile.addCard(wildComputerColor(13, comp2));
                        //System.out.println();
                        System.out.println("Computer 2 has played a wild card");
                        comp2.removeCard(elem);//remove the card from the player deck. it's no longer needed there
                        cardPlayed = 1;
                    } else if (Card.getCardNumber(comp2.getCard(elem)) == 14) {
                        discardPile.addCard(wildComputerColor(14, comp2));
                        //System.out.println();
                        System.out.println("Computer 2 has played a wild draw 4");
                        draw4 = true;
                        comp2.removeCard(choice - 1);
                        cardPlayed = 1;
                    } else if (Card.getCardColor(comp2.getCard(elem)) == Card.getCardColor(discardPile.getLast()) || Card.getCardNumber(comp2.getCard(choice - 1)) == Card.getCardNumber(discardPile.getLast())) {
                        discardPile.addCard(comp2.getCard(elem));
                        switch (Card.getCardNumber(comp2.getCard(elem))) {
                            case 10:
                                //System.out.println();
                                System.out.println("Computer 2 has played a skip card");
                                skip = true;
                                break;
                            case 11:
                                //System.out.println();
                                System.out.println("Computer 2 has played a draw 2 card");
                                draw2 = true;
                                break;
                            case 12:
                                //System.out.println();
                                System.out.println("Computer 2 has played a reverse card");
                                if (reverse) {
                                    reverse = false;
                                } else if (!reverse) {
                                    reverse = true;
                                } else {

                                }
                                break;
                            case 9:
                                System.out.println("Computer 2 has played a normal card");
                                break;
                            case 8:
                                System.out.println("Computer 2 has played a normal card");
                                break;
                            case 7:
                                System.out.println("Computer 2 has played a normal card");
                                break;
                            case 6:
                                System.out.println("Computer 2 has played a normal card");
                                break;
                            case 5:
                                System.out.println("Computer 2 has played a normal card");
                                break;
                            case 4:
                                System.out.println("Computer 2 has played a normal card");
                                break;
                            case 3:
                                System.out.println("Computer 2 has played a normal card");
                                break;
                            case 2:
                                System.out.println("Computer 2 has played a normal card");
                                break;
                            case 1:
                                System.out.println("Computer 2 has played a normal card");
                                break;
                            case 0:
                                System.out.println("Computer 2 has played a normal card");
                                break;
                            default:
                                break;
                        }
                        //System.out.println();
                        //System.out.println("Computer 2 has played a card");
                        comp2.removeCard(choice - 1);
                        cardPlayed = 1;
                    } else {
                        //System.out.println();
                        //System.out.println("Please Select a valid card");
                    }
                } while (cardPlayed == 0);
                if(comp2.getSize() == 1 && !unoCalled){
                    System.out.println("Computer 2 did not call uno. +2");
                    comp2.addCard(deck);
                    comp2.addCard(deck);
                }
                if (comp2.getSize() == 0) {
                    System.out.println();
                    System.out.println("Computer 2 won");
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
                int cardPlayed = 0; //this is similar to a boolean. 1's and 0's ya know. 
                boolean drawCard = false;
                boolean unoCalled = false;
                do {//do this while card played = 0
                    uno = checkUno(comp3);
                    //printHand(comp3, drawCard, uno, unoCalled); //show the user what is in their hand
                    printDiscard();
                    System.out.println("Computer 3");
                    Card dCard = discardPile.getLast();
                    choice = getComputerChoice(dCard, comp3, unoCalled);
                    int elem = choice - 1;
                    if (choice == (comp3.getSize() + 1)) {
                        if(!drawCard){
                            //System.out.println();
                            System.out.println("Computer 3 has drawn a card");
                            comp3.addCard(deck);
                            drawCard = true;
                        }else if(drawCard){
                            //System.out.println();
                            System.out.println("Computer 3 has ended their turn without playing a card");
                            cardPlayed = 1;
                        }
                    }else if(choice == (comp3.getSize() + 2)){
                        if(!unoCalled){
                            //System.out.println();
                            System.out.println("Computer 3 Calls Uno");
                            unoCalled = true;
                        }else if(unoCalled){
                            //System.out.println();
                           // System.out.println("Please Select a Valid Card");
                        }
                    } else if (Card.getCardNumber(comp3.getCard(elem)) == 13) {
                        discardPile.addCard(wildComputerColor(13, comp3));
                        //System.out.println();
                        System.out.println("Computer 3 has played a wild card");
                        comp3.removeCard(elem);//remove the card from the player deck. it's no longer needed there
                        cardPlayed = 1;
                    } else if (Card.getCardNumber(comp3.getCard(elem)) == 14) {
                        discardPile.addCard(wildComputerColor(14, comp3));
                        //System.out.println();
                        System.out.println("Computer 3 has played a wild draw 4");
                        draw4 = true;
                        comp3.removeCard(choice - 1);
                        cardPlayed = 1;
                    } else if (Card.getCardColor(comp3.getCard(elem)) == Card.getCardColor(discardPile.getLast()) || Card.getCardNumber(comp3.getCard(choice - 1)) == Card.getCardNumber(discardPile.getLast())) {
                        discardPile.addCard(comp3.getCard(elem));
                        switch (Card.getCardNumber(comp3.getCard(elem))) {
                            case 10:
                                //System.out.println();
                                System.out.println("Computer 3 has played a skip card");
                                skip = true;
                                break;
                            case 11:
                                //System.out.println();
                                System.out.println("Computer 3 has played a draw 2 card");
                                draw2 = true;
                                break;
                            case 12:
                                //System.out.println();
                                System.out.println("Computer 3 has played a reverse card");
                                if (reverse) {
                                    reverse = false;
                                } else if (!reverse) {
                                    reverse = true;
                                } else {

                                }
                                break;
                            case 9:
                                System.out.println("Computer 3 has played a normal card");
                                break;
                            case 8:
                                System.out.println("Computer 3 has played a normal card");
                                break;
                            case 7:
                                System.out.println("Computer 3 has played a normal card");
                                break;
                            case 6:
                                System.out.println("Computer 3 has played a normal card");
                                break;
                            case 5:
                                System.out.println("Computer 3 has played a normal card");
                                break;
                            case 4:
                                System.out.println("Computer 3 has played a normal card");
                                break;
                            case 3:
                                System.out.println("Computer 3 has played a normal card");
                                break;
                            case 2:
                                System.out.println("Computer 3 has played a normal card");
                                break;
                            case 1:
                                System.out.println("Computer 3 has played a normal card");
                                break;
                            case 0:
                                System.out.println("Computer 3 has played a normal card");
                                break;
                            default:
                                break;
                        }
                        //System.out.println();
                        //System.out.println("Computer 3 has played a card");
                        comp3.removeCard(choice - 1);
                        cardPlayed = 1;
                    } else {
                        //System.out.println();
                        System.out.println("Please Select a valid card");
                    }
                } while (cardPlayed == 0);
                if(comp3.getSize() == 1 && !unoCalled){
                    System.out.println("Computer 3 did not call uno. +2");
                    comp3.addCard(deck);
                    comp3.addCard(deck);
                }
                if (comp3.getSize() == 0) {
                    System.out.println();
                    System.out.println("Computer 3 won");
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
            System.out.println();
            System.out.println("Shuffling Deck");
            System.out.println();
            for(int i = 0; i < discardPile.getSize(); i++){
                if(Card.getCardNumber(discardPile.getLast())==13 && Card.getCardColor(discardPile.getLast())!='a'){
                    discardPile.removeCard(discardPile.getSize()-1);
                    discardPile.addCard(new Card(13, 'a'));
                }else if(Card.getCardNumber(discardPile.getLast())==14 && Card.getCardColor(discardPile.getLast())!='a'){
                    discardPile.removeCard(discardPile.getSize()-1);
                    discardPile.addCard(new Card(14, 'a'));
                }else{
                    deck.addCard(discardPile.getLast());
                    discardPile.removeCard(discardPile.getSize()-1);
                }
            }
            deck.shuffleDeck();
            deck.shuffleDeck();
        }else{
            
        }
        
    }
    public static int getComputerChoice(Card dCard, deal Computer, boolean unoCalled){
        int choice = 0;
        boolean hWild = false;
        boolean hSkip = false;
        boolean hReverse = false;
        boolean hDTwo = false;
        boolean hDFour = false;
        boolean hPlayable = false;
        hWild = hasWild(dCard, Computer);
        hSkip = hasSkip(dCard, Computer);
        hReverse = hasReverse(dCard, Computer);
        hDTwo = hasDrawTwo(dCard, Computer);
        hDFour = hasDrawFour(dCard, Computer);
        hPlayable = hasPlayable(dCard, Computer);
        if(Computer.getSize() == 2 && !unoCalled){
            choice = Computer.getSize() + 2;
            //System.out.println("Attempted call uno");
        }else if(hDTwo){
            choice = findDTwo(dCard, Computer);
            choice++;
            //System.out.println("Attempted draw2");
        }else if(hSkip){
            choice = findSkip(Computer, dCard);
            choice++;
            //System.out.println("Attempted skip");
        }else if(hReverse){
            choice = findReverse(dCard, Computer);
            choice++;
            //System.out.println("Attempted reverse");
        }else if(hPlayable){
            choice = findPlayable(dCard, Computer);
            choice++;
            //System.out.println("attempted card 1-9");
        }else if(hWild && !hDTwo && !hSkip && !hReverse && !hPlayable){
            choice = findWild(Computer);
            choice++;
            //System.out.println("attempted wild");
        }else if(hDFour && !hWild && !hDTwo && !hSkip && !hReverse && !hPlayable){
            choice = findDrawFour(Computer);
            choice++;
            //System.out.println("attempted wild draw 4");
        }else{
            choice = Computer.getSize() + 1;
            //System.out.println("Attempted draw card");
        }
        return choice;
    }
    public static int findWild(deal Computer){
        int elem = 0;
        for(int i=0; i < Computer.getSize(); i++){
            if(Card.getCardNumber(Computer.getCard(i))==13){
                elem = i;
            }
        }
        return elem;
    }
    public static int findDTwo(Card dCard, deal Computer){
        int elem = 0;
        for(int i = 0; i < Computer.getSize(); i++){
            if(Card.getCardNumber(Computer.getCard(i))==11 && Card.getCardColor(Computer.getCard(i))==Card.getCardColor(dCard))
                elem = i;
        }
        return elem;
    }
    public static int findSkip(deal Computer, Card dCard){
        int elem = 0;
        for(int i = 0; i < Computer.getSize(); i++){
            if(Card.getCardNumber(Computer.getCard(i))==10 && Card.getCardColor(Computer.getCard(i))==Card.getCardColor(dCard))
                elem = i;
        }
        return elem;
    }
    public static int findReverse(Card dCard, deal Computer){
        int elem = 0;
        for(int i = 0; i < Computer.getSize(); i++){
            if(Card.getCardNumber(Computer.getCard(i))==12 && Card.getCardColor(Computer.getCard(i))==Card.getCardColor(dCard))
                elem = i;
        }
        return elem;
    }
    public static int findDrawFour(deal Computer){
        int elem = 0;
        for(int i = 0; i < Computer.getSize(); i++){
            if(Card.getCardNumber(Computer.getCard(i)) == 14){
                elem = i;
            }
        }
        return elem;
    }
    public static int findPlayable(Card dCard, deal Computer){
        int elem = 0;
        for(int i = 0; i < Computer.getSize(); i++){
            if(Card.getCardNumber(Computer.getCard(i))<10 && Card.getCardColor(Computer.getCard(i))==Card.getCardColor(dCard))
                elem = i;
        }
        return elem;
    }
    public static boolean hasWild(Card dCard, deal Computer){
        boolean hWild = false;
        for(int i = 0; i < Computer.getSize(); i++){
            if(Card.getCardNumber(Computer.getCard(i))==13)
                hWild = true;
        }
        return hWild;
    }
    public static boolean hasSkip(Card dCard,deal Computer){
        boolean hSkip = false;
        for(int i = 0; i < Computer.getSize(); i++){
            if(Card.getCardNumber(Computer.getCard(i))==10 && Card.getCardColor(Computer.getCard(i))==Card.getCardColor(dCard))
                hSkip = true;
        }
        return hSkip;
    }
    public static boolean hasReverse(Card dCard, deal Computer){
        boolean hReverse = false;
        for(int i = 0; i < Computer.getSize(); i++){
            if(Card.getCardNumber(Computer.getCard(i))==12 && Card.getCardColor(Computer.getCard(i))==Card.getCardColor(dCard))
                hReverse = true;
        }
        return hReverse;
    }
    public static boolean hasDrawTwo(Card dCard, deal Computer){
        boolean hDTwo = false;
        for(int i = 0; i < Computer.getSize(); i++){
            if(Card.getCardNumber(Computer.getCard(i))==11 && Card.getCardColor(Computer.getCard(i))==Card.getCardColor(dCard))
                hDTwo = true;
        }
        return hDTwo;
    }
    public static boolean hasDrawFour(Card dCard, deal Computer){
        boolean hDrawFour = false;
        for(int i = 0; i < Computer.getSize(); i++){
            if(Card.getCardNumber(Computer.getCard(i))==14)
                hDrawFour = true;
        }
        return hDrawFour;
    }
    public static boolean hasPlayable(Card dCard, deal Computer){
        boolean hPlayable = false;
        for(int i = 0; i < Computer.getSize(); i++){
            if(Card.getCardNumber(Computer.getCard(i))<10 && Card.getCardColor(Computer.getCard(i))==Card.getCardColor(dCard))
                hPlayable = true;
        }
        return hPlayable;
    }
    public static Card wildComputerColor(int cardNumber, deal computer){
        int blue = 0;
        int red = 0;
        int yellow = 0;
        int green = 0;
        char cColor = 'a';
        for(int i = 0; i < computer.getSize(); i++){
            if(Card.getCardColor(computer.getCard(i)) == 'b'){
                blue++;
            }else if(Card.getCardColor(computer.getCard(i))=='r'){
                red++;
            }else if(Card.getCardColor(computer.getCard(i))=='y'){
                yellow++;
            }else if(Card.getCardColor(computer.getCard(i))=='g'){
                green++;
            }
        }
        if(blue > green && blue > yellow && blue > red){
            cColor = 'b';
        }else if(green > blue && green > yellow && green > red){
            cColor = 'g';
        }else if(yellow > green && yellow > blue && yellow > red){
            cColor = 'y';
        }else if(red > green && red > yellow && red > blue){
            cColor = 'r';
        }else{
            cColor = 'b';
        }
        return new Card(cardNumber, cColor);
    }
    public static int getCardNumber() {
        int choice = 0;
        do{
            try {
                System.out.print("Which card do you want to play: ");
                choice = si.nextInt();
                si.nextLine();
            } catch (InputMismatchException  e) {
                System.out.println("Invalid Input");
                si.nextLine();
            }
            
        }while(choice == 0);
        return choice;
    }
}
