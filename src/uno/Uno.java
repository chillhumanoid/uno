package uno;

import java.util.Scanner;
import java.util.Random;

/**
 *
 * @author Jonathan Thorne Made from scratch to play Uno, will eventually add a
 * GUI, but for now it will be command line.
 */
public class Uno {

    static Random rn = new Random();
    static deal play1 = new deal();//for the user
    static deal comp1 = new deal();//for computer 1
    static deal comp2 = new deal();//for computer 2
    static deal comp3 = new deal(); //for computer 3
    static cardHandler deck = new cardHandler();
    static Scanner s = new Scanner(System.in);
    static deal discardPile = new deal();

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in); //scanner is for user input
        //card creator
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
        do {
            while (currentPlayer == 1) {
                skip = false;
                int cardPlayed = 0; //this is similar to a boolean. 1's and 0's ya know. 
                System.out.println(); //spacing
                printHand(play1); //show the user what is in their hand
                System.out.println(); //spacing
                System.out.println("Player 1");
                printDiscard(discardPile); //show the top card in the discard pile
                do {//do this while card played = 0
                    System.out.print("Which card do you want to play?: ");
                    int choice = s.nextInt(); //asks for an integer from the user
                    if(choice == (play1.getSize()+1)){
                        play1.addCard(deck);
                        cardPlayed = 1;
                    }else if (Card.getCardColor(play1.getCard(choice - 1)) == 'a') { //gets the color of the card that user chose
                        if (Card.getCardNumber(play1.getCard(choice - 1)) == 13) {//13 is the wild card
                            wildColor();
                            play1.removeCard(choice - 1);//remove the card from the player deck. it's no longer needed there
                        } else if (Card.getCardNumber(play1.getCard(choice - 1)) == 14) {
                            wildDrawFour();
                            play1.removeCard(choice - 1);
                        }
                        cardPlayed = 1;
                    } else if (Card.getCardColor(play1.getCard(choice - 1)) == Card.getCardColor(discardPile.getCard(discardPile.getSize() - 1)) || Card.getCardNumber(play1.getCard(choice - 1)) == Card.getCardNumber(discardPile.getLast())) {
                        discardPile.addCard(play1.getCard(choice - 1));
                        play1.removeCard(choice - 1);
                        switch (Card.getCardNumber(play1.getCard((choice - 1)))) {
                            case 10:
                                skip = true;
                                break;
                            case 11:
                                wildPlayer(2);
                                break;
                            case 12:
                                if (reverse) {
                                    reverse = false;
                                } else if (!reverse) {
                                    reverse = true;
                                } else {
                                    
                                }   break;
                            default:
                                break;
                        }
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
                }
                if (reverse == true && skip == true) {
                    currentPlayer = 3;
                }else if(reverse == true && skip == false){
                    currentPlayer = 4;
                } else if (reverse == false && skip == true) {
                    currentPlayer = 3;
                } else if (reverse == false && skip == false){
                    currentPlayer = 2;
                }
                break;
            }
            while (currentPlayer == 2) {
                skip = false;
                int cardPlayed = 0; //this is similar to a boolean. 1's and 0's ya know. 
                System.out.println(); //spacing
                printHand(comp1); //show the user what is in their hand
                System.out.println(); //spacing
                System.out.println("Player 2");
                printDiscard(discardPile); //show the top card in the discard pile
                do {//do this while card played = 0
                    System.out.print("Which card do you want to play?: ");
                    int choice = s.nextInt(); //asks for an integer from the user
                    if(choice == (comp1.getSize()+1)){
                        comp1.addCard(deck);
                        cardPlayed = 1;
                    }else if(Card.getCardColor(comp1.getCard(choice - 1)) == 'a') {
                        //gets the color of the card that user chose
                        if (13 == Card.getCardNumber(comp1.getCard(choice-1))) {//13 is the wild card
                            wildColor();
                            comp1.removeCard(choice - 1);//remove the card from the player deck. it's no longer needed there
                        } else if (Card.getCardNumber(comp1.getCard(choice - 1)) == 14) {
                            wildDrawFour2();
                            comp1.removeCard(choice - 1);
                        }
                        cardPlayed = 1;
                    } else if (Card.getCardColor(comp1.getCard(choice - 1)) == Card.getCardColor(discardPile.getCard(discardPile.getSize() - 1)) || Card.getCardNumber(comp1.getCard(choice - 1)) == Card.getCardNumber(discardPile.getLast())) {
                        discardPile.addCard(comp1.getCard(choice - 1));
                        comp1.removeCard(choice - 1);
                        switch (Card.getCardNumber(comp1.getCard(choice - 1))) {
                            case 10:
                                skip = true;
                                break;
                            case 11:
                                wildPlayer2(2);
                                break;
                            case 12:
                                if (reverse) {
                                    reverse = false;
                                } else if (!reverse) {
                                    reverse = true;
                                } else {
                                    
                                }   break;
                            default:
                                break;
                        }
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
                }
                if (reverse == true && skip == true) {
                    currentPlayer = 4;
                }else if(reverse == true && skip == false){
                    currentPlayer = 1;
                } else if (reverse == false && skip == true) {
                    currentPlayer = 4;
                } else if (reverse == false && skip == false){
                    currentPlayer = 3;
                }
                break;
            
            }
            while (currentPlayer == 3) {
                skip = false;
                int cardPlayed = 0; //this is similar to a boolean. 1's and 0's ya know. 
                System.out.println(); //spacing
                printHand(comp2); //show the user what is in their hand
                System.out.println(); //spacing
                System.out.println("Player 3");
                printDiscard(discardPile); //show the top card in the discard pile
                do {//do this while card played = 0
                    System.out.print("Which card do you want to play?: ");
                    int choice = s.nextInt(); //asks for an integer from the user
                    if(choice == (comp2.getSize()+1)){
                        comp2.addCard(deck);
                        cardPlayed = 1;
                    }else if(Card.getCardColor(comp2.getCard(choice - 1)) == 'a') { //gets the color of the card that user chose
                        comp2.removeCard(choice - 1);//remove the card from the player deck. it's no longer needed there
                        if (Card.getCardNumber(comp2.getCard(choice - 1)) == 13) {//13 is the wild card
                            wildColor();
                        } else if (Card.getCardNumber(comp2.getCard(choice - 1)) == 14) {
                            wildDrawFour3();
                        }
                        cardPlayed = 1;
                    } else if (Card.getCardColor(comp2.getCard(choice - 1)) == Card.getCardColor(discardPile.getCard(discardPile.getSize() - 1)) || Card.getCardNumber(comp2.getCard(choice - 1)) == Card.getCardNumber(discardPile.getLast())) {
                        discardPile.addCard(comp2.getCard(choice - 1));
                        comp2.removeCard(choice - 1);
                        switch (Card.getCardNumber(comp2.getCard(choice - 1))) {
                            case 10:
                                skip = true;
                                break;
                            case 11:
                                wildPlayer3(2);
                                break;
                            case 12:
                                if (reverse) {
                                    reverse = false;
                                } else if (!reverse) {
                                    reverse = true;
                                } else {
                                    
                                }   break;
                            default:
                                break;
                        }
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
                }
                if (reverse == true && skip == true) {
                    currentPlayer = 1;
                }else if(reverse == true && skip == false){
                    currentPlayer = 2;
                } else if (reverse == false && skip == true) {
                    currentPlayer = 1;
                } else if (reverse == false && skip == false){
                    currentPlayer = 4;
                }
                break;
            }
            while (currentPlayer == 4) {
                skip = false;
                int cardPlayed = 0; //this is similar to a boolean. 1's and 0's ya know. 
                System.out.println(); //spacing
                printHand(comp3); //show the user what is in their hand
                System.out.println(); //spacing
                System.out.println("Player 4");
                printDiscard(discardPile); //show the top card in the discard pile
                do {//do this while card played = 0
                    System.out.print("Which card do you want to play?: ");
                    int choice = s.nextInt(); //asks for an integer from the user
                    if(choice == (comp3.getSize()+1)){
                        comp3.addCard(deck);
                        cardPlayed = 1;
                    }else if (Card.getCardColor(comp3.getCard(choice - 1)) == 'a') { //gets the color of the card that user chose
                        if (Card.getCardNumber(comp3.getCard(choice - 1)) == 13) {//13 is the wild card
                            wildColor();
                            comp3.removeCard(choice - 1);//remove the card from the player deck. it's no longer needed there
                        } else if (Card.getCardNumber(comp3.getCard(choice - 1)) == 14) {
                            wildDrawFour4();
                            comp3.removeCard(choice - 1);
                        }
                        cardPlayed = 1;
                    } else if (Card.getCardColor(comp3.getCard(choice - 1)) == Card.getCardColor(discardPile.getCard(discardPile.getSize() - 1)) || Card.getCardNumber(comp3.getCard(choice - 1)) == Card.getCardNumber(discardPile.getLast())) {
                        discardPile.addCard(comp3.getCard(choice - 1));
                        comp3.removeCard(choice - 1);
                        if (Card.getCardNumber(comp3.getCard(choice - 1)) == 10) {
                            skip = true;
                        } else if (Card.getCardNumber(comp3.getCard(choice - 1)) == 11) {
                            wildPlayer4(2);
                        } else if (Card.getCardNumber(comp3.getCard(choice - 1)) == 12) {
                            if (reverse) {
                                reverse = false;
                            } else if (!reverse) {
                                reverse = true;
                            } else {

                            }
                        }
                        cardPlayed = 1;
                    } else {
                        System.out.println();
                        System.out.println("Please Select a valid card");
                    }
                } while (cardPlayed == 0);
                if (comp3.getSize() == 1) {
                    System.out.println();
                    System.out.println("Player 3 Calls Uno");
                }
                if (comp3.getSize() == 0) {
                    System.out.println();
                    System.out.println("Player 3 won");
                    gameEnded = true;
                }
                if (reverse == true && skip == true) {
                    currentPlayer = 2;
                }else if(reverse == true && skip == false){
                    currentPlayer = 3;
                } else if (reverse == false && skip == true) {
                    currentPlayer = 2;
                } else if (reverse == false && skip == false){
                    currentPlayer = 1;
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
    public static void wildPlayer(int i) {
        System.out.println();
        System.out.println("Which Player do you want to draw " + i + "?:");
        System.out.println("1. Player 2 Cards: " + comp1.getSize());
        System.out.println("2. Player 3 Cards: " + comp2.getSize());
        System.out.println("3. Player 4 Cards: " + comp3.getSize());
        s.nextLine();
        int choice = s.nextInt();
        if (choice == 1) {
            for (int y = 1; y <= i; y++) {
                comp1.addCard(deck);
            }
        } else if (choice == 2) {
            for (int y = 1; y <= i; y++) {
                comp2.addCard(deck);
            }
        } else if (choice == 3) {
            for (int y = 1; y <= i; y++) {
                comp3.addCard(deck);
            }
        } else {
            System.out.println();
            System.out.println("Please select a valid number(1-3)");
            wildPlayer(i);
        }
    }

    public static void wildPlayer2(int i) {
        System.out.println();
        System.out.println("Which Player do you want to draw " + i + "?:");
        System.out.println("1. Player 1 Cards: " + play1.getSize());
        System.out.println("2. Player 3 Cards: " + comp2.getSize());
        System.out.println("3. Player 4 Cards: " + comp3.getSize());
        s.nextLine();
        int choice = s.nextInt();
        if (choice == 1) {
            for (int y = 1; y <= i; y++) {
                play1.addCard(deck);
            }
        } else if (choice == 2) {
            for (int y = 1; y <= i; y++) {
                comp2.addCard(deck);
            }
        } else if (choice == 3) {
            for (int y = 1; y <= i; y++) {
                comp3.addCard(deck);
            }
        } else {
            System.out.println();
            System.out.println("Please select a valid number(1-3)");
            wildPlayer(i);
        }
    }
    public static void wildPlayer3(int i) {
        System.out.println();
        System.out.println("Which Player do you want to draw " + i + "?:");
        System.out.println("1. Player 1 Cards: " + play1.getSize());
        System.out.println("2. Player 2 Cards: " + comp1.getSize());
        System.out.println("3. Player 4 Cards: " + comp3.getSize());
        s.nextLine();
        int choice = s.nextInt();
        if (choice == 1) {
            for (int y = 1; y <= i; y++) {
                play1.addCard(deck);
            }
        } else if (choice == 2) {
            for (int y = 1; y <= i; y++) {
                comp1.addCard(deck);
            }
        } else if (choice == 3) {
            for (int y = 1; y <= i; y++) {
                comp3.addCard(deck);
            }
        } else {
            System.out.println();
            System.out.println("Please select a valid number(1-3)");
            wildPlayer(i);
        }
    }
        public static void wildPlayer4(int i) {
        System.out.println();
        System.out.println("Which Player do you want to draw " + i + "?:");
        System.out.println("1. Player 1 Cards: " + play1.getSize());
        System.out.println("2. Player 2 Cards: " + comp1.getSize());
        System.out.println("3. Player 3 Cards: " + comp2.getSize());
        s.nextLine();
        int choice = s.nextInt();
        if (choice == 1) {
            for (int y = 1; y <= i; y++) {
                play1.addCard(deck);
            }
        } else if (choice == 2) {
            for (int y = 1; y <= i; y++) {
                comp1.addCard(deck);
            }
        } else if (choice == 3) {
            for (int y = 1; y <= i; y++) {
                comp2.addCard(deck);
            }
        } else {
            System.out.println();
            System.out.println("Please select a valid number(1-3)");
            wildPlayer(i);
        }
    }


    public static void wildColor() {
        System.out.print("What color do you want the deck to be?: ");
        s.nextLine();
        String input = s.nextLine();
        if ("blue".equals(input.toLowerCase())) {
            discardPile.addCard(new Card(13, 'b'));
            printDiscard(discardPile);
        } else if ("red".equals(input.toLowerCase())) {
            discardPile.addCard(new Card(13, 'r'));
            printDiscard(discardPile);
        } else if ("green".equals(input.toLowerCase())) {
            discardPile.addCard(new Card(13, 'g'));
            printDiscard(discardPile);
        } else if ("yellow".equals(input.toLowerCase())) {
            discardPile.addCard(new Card(13, 'y'));
            printDiscard(discardPile);
        } else {
            System.out.println("Type: Blue, Green, Red or Yellow");
            wildColor();
        }
    }

    public static void wildDrawFour() {
        wildColor();
        wildPlayer(4);
    }
    public static void wildDrawFour2() {
        wildColor();
        wildPlayer2(4);
    }
    public static void wildDrawFour3() {
        wildColor();
        wildPlayer3(4);
    }
    public static void wildDrawFour4() {
        wildColor();
        wildPlayer4(4);
    }

    public static void printDiscard(deal discardPile) {
        System.out.print("Discard Pile: ");
        System.out.println(discardPile.getLast());
    }
}
