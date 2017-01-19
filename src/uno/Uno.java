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
        int currentPlayer = 1; //current player = 1 (user)
        boolean reverse = false; // will be used to denote a reverse card played. 
        boolean skip = false; //will be used to denote a skip card played
        boolean gameEnded = false; //will be set to true when the first player runs out of cards. 
        boolean draw2 = false;
        boolean draw4 = false;
        do {
            while (currentPlayer == 1) {
                skip = false; //added so that when it next goes to player 1, those values are reset. 
                draw2 = false; //^
                draw4 = false; // ^^
                int cardPlayed = 0; //this is similar to a boolean. 1's and 0's ya know. 
                System.out.println(); //spacing
                int choice = 0;
                printHand(play1); //show the user what is in their hand
                System.out.println(); //spacing
                System.out.println("Player 1");
                printDiscard(); //show the top card in the discard pile
                do {//do this while card played = 0a
                    choice = getCardNumber();
                    int elem = choice - 1;
                    if (choice == (play1.getSize() + 1)) {
                        play1.addCard(deck);
                        cardPlayed = 1;
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
                if (play1.getSize() == 1) {
                    System.out.println();
                    System.out.println("Player 1 Calls Uno");
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
                break;
            }
            while (currentPlayer == 2) {
                skip = false;
                draw2 = false;
                draw4 = false;
                int choice = 0;
                int cardPlayed = 0; //this is similar to a boolean. 1's and 0's ya know. 
                System.out.println(); //spacing
                printHand(comp1); //show the user what is in their hand
                System.out.println(); //spacing
                System.out.println("Player 2");
                printDiscard(); //show the top card in the discard pile
                do {//do this while card played = 0
                    choice = getCardNumber();
                    int elem = choice - 1;
                    if (choice == (comp1.getSize() + 1)) {
                        comp1.addCard(deck);
                        cardPlayed = 1;
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
                if (comp1.getSize() == 1) {
                    System.out.println();
                    System.out.println("Player 2 Calls Uno");
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
                break;

            }
            while (currentPlayer == 3) {
                skip = false;
                draw2 = false;
                draw4 = false;
                int choice = 0;
                int cardPlayed = 0; //this is similar to a boolean. 1's and 0's ya know. 
                System.out.println(); //spacing
                printHand(comp2); //show the user what is in their hand
                System.out.println(); //spacing
                System.out.println("Player 3");
                printDiscard(); //show the top card in the discard pile
                do {//do this while card played = 0
                    choice = getCardNumber();
                    int elem = choice - 1;
                    if (choice == (comp2.getSize() + 1)) {
                        comp2.addCard(deck);
                        cardPlayed = 1;
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
                if (comp2.getSize() == 1) {
                    System.out.println();
                    System.out.println("Player 3 Calls Uno");
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
                break;
            }
            while (currentPlayer == 4) {
                skip = false;
                draw2 = false;
                draw4 = false;
                int choice = 0;
                int cardPlayed = 0; //this is similar to a boolean. 1's and 0's ya know. 
                System.out.println(); //spacing
                printHand(comp3); //show the user what is in their hand
                System.out.println(); //spacing
                System.out.println("Player 4");
                printDiscard(); //show the top card in the discard pile
                do {//do this while card played = 0
                    choice = getCardNumber();
                    int elem = choice - 1;
                    if (choice == (comp3.getSize() + 1)) {
                        comp3.addCard(deck);
                        cardPlayed = 1;
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
                if (comp3.getSize() == 1) {
                    System.out.println();
                    System.out.println("Player 4 Calls Uno");
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
                break;
            }
        } while (!gameEnded);
    }

    public static void printHand(deal play1) {
        int display = 0;
        for (int x = 0; x < play1.getSize(); x++) {
            display = x + 1;
            System.out.println(display + ". " + play1.getCard(x));
        }
        display++;
        System.out.println(display + ". Draw Card");
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
