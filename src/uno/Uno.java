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
        int choice; //holds users choice in all cases where it's needed
        String input; //holds any string based input from user
        //card creator
        for (int i = 0; i <= 7; i++) { //adds 7 cards to each players hand. 
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
                printDiscard(discardPile); //show the top card in the discard pile
                do {//do this while card played = 0
                    System.out.print("Which card do you want to play?: ");
                    choice = s.nextInt(); //asks for an integer from the user

                    if (Card.getCardColor(play1.getCard(choice - 1)) == 'a') { //gets the color of the card that user chose
                        if (Card.getCardNumber(play1.getCard(choice - 1)) == 13) {//13 is the wild card
                            wildCard();
                            play1.removeCard(choice - 1);//remove the card from the player deck. it's no longer needed there
                            cardPlayed = 1;
                        } else if (Card.getCardNumber(play1.getCard(choice - 1)) == 14) {
                            int colorGot = 0; //again booleans would be cool.  butt fuck em. 
                            int playerGot = 0; //^ditto
                            play1.removeCard(choice - 1);
                            do {
                                System.out.print("What color do you want the deck to be?: ");
                                input = s.nextLine();
                                if ("blue".equals(input.toLowerCase())) {
                                    discardPile.addCard(new Card(13, 'b'));
                                    printDiscard(discardPile);
                                    colorGot = 1;
                                } else if ("red".equals(input.toLowerCase())) {
                                    discardPile.addCard(new Card(13, 'r'));
                                    printDiscard(discardPile);
                                    colorGot = 1;
                                } else if ("green".equals(input.toLowerCase())) {
                                    discardPile.addCard(new Card(13, 'g'));
                                    printDiscard(discardPile);
                                    colorGot = 1;
                                } else if ("yellow".equals(input.toLowerCase())) {
                                    discardPile.addCard(new Card(13, 'y'));
                                    printDiscard(discardPile);
                                    colorGot = 1;
                                } else {
                                    System.out.println("Type: Blue, Green, Red or Yellow");
                                }
                            } while (colorGot == 0);
                            do {
                                System.out.println();
                                System.out.println("Which Player do you want to draw 4?");
                                System.out.println("1. Computer 1 Cards: " + comp1.getSize());
                                System.out.println("2. Computer 2 Cards: " + comp2.getSize());
                                System.out.println("3. Computer 3 Cards: " + comp3.getSize());
                                choice = s.nextInt();
                                if (choice == 1) {
                                    comp1.addCard(deck);
                                    comp1.addCard(deck);
                                    comp1.addCard(deck);
                                    comp1.addCard(deck);
                                    playerGot = 1;
                                }
                                if (choice == 2) {
                                    comp2.addCard(deck);
                                    comp2.addCard(deck);
                                    comp2.addCard(deck);
                                    comp2.addCard(deck);
                                    playerGot = 1;
                                }
                                if (choice == 3) {
                                    comp3.addCard(deck);
                                    comp3.addCard(deck);
                                    comp3.addCard(deck);
                                    comp3.addCard(deck);
                                    playerGot = 1;
                                } else {
                                    System.out.println();
                                    System.out.println("Please select a valid number(1-3)");
                                }
                            } while (playerGot == 0);
                        }
                        cardPlayed = 1;
                    } else if (Card.getCardColor(play1.getCard(choice - 1)) == Card.getCardColor(discardPile.getCard(discardPile.getSize() - 1)) || Card.getCardNumber(play1.getCard(choice - 1)) == Card.getCardNumber(discardPile.getLast())) {
                        discardPile.addCard(play1.getCard(choice - 1));
                        play1.removeCard(choice - 1);
                        if (Card.getCardNumber(play1.getCard(choice - 1)) == 10) {
                            skip = true;
                            cardPlayed = 1;
                        } else if (Card.getCardNumber(play1.getCard(choice - 1)) == 11) {
                            int playerGot = 0;
                            do {
                                System.out.print("What player do you want to draw two?: ");
                                System.out.println("1. Computer 1 Cards: " + comp1.getSize());
                                System.out.println("2. Computer 2 Cards: " + comp2.getSize());
                                System.out.println("3. Computer 3 Cards: " + comp3.getSize());
                                choice = s.nextInt();
                                if (choice == 1) {
                                    comp1.addCard(deck);
                                    comp1.addCard(deck);
                                    playerGot = 1;
                                } else if (choice == 2) {
                                    comp1.addCard(deck);
                                    comp1.addCard(deck);
                                    playerGot = 1;
                                } else if (choice == 3) {
                                    comp2.addCard(deck);
                                    comp2.addCard(deck);
                                    playerGot = 1;
                                } else if (choice ==4) {
                                    comp3.addCard(deck);
                                    comp3.addCard(deck);
                                    playerGot = 1;
                                } else {
                                    System.out.println("Type: Blue, Green, Red or Yellow");
                                }
                            } while (playerGot == 0);
                            cardPlayed = 1;
                        } else if (Card.getCardNumber(play1.getCard(choice - 1)) == 12) {
                            if (reverse == true) {
                                reverse = false;
                            } else if (reverse == false) {
                                reverse = true;
                            } else {

                            }
                        }
                        play1.removeCard(choice - 1);
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
                }
                if (reverse == true) {
                    if (skip == true) {
                        currentPlayer = 3;
                    } else if (skip == false) {
                        currentPlayer = 4;
                    }
                } else if (reverse == false) {
                    if (skip == true) {
                        currentPlayer = 3;
                    } else if (skip == false) {
                        currentPlayer = 2;
                    }
                }
                break;
            }
            while (currentPlayer == 2) {
                skip = false;
                int cardPlayed = 0; //this is similar to a boolean. 1's and 0's ya know. 
                System.out.println(); //spacing    
                printDiscard(discardPile); //show the top card in the discard pile
                do {//do this while card played = 0
                    choice = rn.nextInt((comp1.getSize() - 1)+1) + 1;
                    if (Card.getCardColor(comp1.getCard(choice - 1)) == 'a') { //gets the color of the card that user chose
                        //a means any so it's 2 possible cards
                        if (Card.getCardNumber(comp1.getCard(choice - 1)) == 13) {//13 is the wild card
                            int colorGot = 0;//for the do-while loop
                            comp1.removeCard(choice - 1);//remove the card from the player deck. it's no longer needed there
                            do {//do the following while colorGot = 0
                                int c1CC = 0;
                                c1CC = rn.nextInt((4 - 1) + 1) + 1;
                                if (c1CC == 1) {//if the input is blue
                                    discardPile.addCard(new Card(13, 'b'));//adds the same card we deleted from the players hand, but with blue as the color
                                    printDiscard(discardPile); //displays what's on the discard pile
                                    colorGot = 1;//basically a boolean. really. i should use those more.
                                } else if (c1CC == 2) { //same thing as blue. read blue, replace blue with red. thats this
                                    discardPile.addCard(new Card(13, 'r'));
                                    printDiscard(discardPile);
                                    colorGot = 1;
                                } else if (c1CC == 3) {
                                    discardPile.addCard(new Card(13, 'g'));
                                    printDiscard(discardPile);
                                    colorGot = 1;
                                } else if (c1CC == 4) {
                                    discardPile.addCard(new Card(13, 'y'));
                                    printDiscard(discardPile);
                                    colorGot = 1;
                                }
                            } while (colorGot == 0); //yea again..booleans. use em. bitch.
                            cardPlayed = 1;
                        } else if (Card.getCardNumber(comp1.getCard(choice - 1)) == 14) {
                            int colorGot = 0; //again booleans would be cool.  butt fuck em. 
                            int playerGot = 0; //^ditto
                            int c1CC = 0;
                            c1CC = rn.nextInt((4 - 1) + 1) + 1;
                            comp1.removeCard(choice - 1);
                            do {
                                if (c1CC == 1) {
                                    discardPile.addCard(new Card(13, 'b'));
                                    printDiscard(discardPile);
                                    colorGot = 1;
                                } else if (c1CC == 2) {
                                    discardPile.addCard(new Card(13, 'r'));
                                    printDiscard(discardPile);
                                    colorGot = 1;
                                } else if (c1CC == 3) {
                                    discardPile.addCard(new Card(13, 'g'));
                                    printDiscard(discardPile);
                                    colorGot = 1;
                                } else if (c1CC == 4) {
                                    discardPile.addCard(new Card(13, 'y'));
                                    printDiscard(discardPile);
                                    colorGot = 1;
                                }
                            } while (colorGot == 0);
                            do {
                                choice = rn.nextInt((3 - 1) + 1) + 1;
                                if (choice == 1) {
                                    play1.addCard(deck);
                                    play1.addCard(deck);
                                    play1.addCard(deck);
                                    play1.addCard(deck);
                                    playerGot = 1;
                                }
                                if (choice == 2) {
                                    comp2.addCard(deck);
                                    comp2.addCard(deck);
                                    comp2.addCard(deck);
                                    comp2.addCard(deck);
                                    playerGot = 1;
                                }
                                if (choice == 3) {
                                    comp3.addCard(deck);
                                    comp3.addCard(deck);
                                    comp3.addCard(deck);
                                    comp3.addCard(deck);
                                    playerGot = 1;
                                }
                            } while (playerGot == 0);
                        }
                        cardPlayed = 1;
                    } else if (Card.getCardColor(comp1.getCard(choice - 1)) == Card.getCardColor(discardPile.getCard(discardPile.getSize() - 1)) || Card.getCardNumber(comp1.getCard(choice - 1)) == Card.getCardNumber(discardPile.getLast())) {
                        discardPile.addCard(comp1.getCard(choice - 1));
                        comp1.removeCard(choice - 1);
                        if (Card.getCardNumber(comp1.getCard(choice - 1)) == 10) {
                            skip = true;
                            cardPlayed = 1;
                        } else if (Card.getCardNumber(comp1.getCard(choice - 1)) == 11) {
                            int playerGot = 0;
                            do {
                                choice = rn.nextInt((3 - 1) + 1) + 1;
                                if (choice == 1) {
                                    play1.addCard(deck);
                                    play1.addCard(deck);
                                    playerGot = 1;
                                }
                                if (choice == 2) {
                                    comp2.addCard(deck);
                                    comp2.addCard(deck);
                                    playerGot = 1;
                                }
                                if (choice == 3) {
                                    comp3.addCard(deck);
                                    comp3.addCard(deck);
                                    playerGot = 1;
                                }
                            } while (playerGot == 0);
                            cardPlayed = 1;
                        } else if (Card.getCardNumber(comp1.getCard(choice - 1)) == 12) {
                            if (reverse == true) {
                                reverse = false;
                            } else if (reverse == false) {
                                reverse = true;
                            } else {

                            }
                            comp1.removeCard(choice - 1);
                            cardPlayed = 1;
                        }
                        cardPlayed = 1;
                    }
                } while (cardPlayed == 0);
                if (comp1.getSize() == 1) {
                    System.out.println();
                    System.out.println("Computer 1 Calls Uno");
                }
                if (comp1.getSize() == 0) {
                    System.out.println();
                    System.out.println("Computer 1 has won");
                }
                if (reverse == true) {
                    if (skip == true) {
                        currentPlayer = 4;
                    } else if (skip == false) {
                        currentPlayer = 1;
                    }
                } else if (reverse == false) {
                    if (skip == true) {
                        currentPlayer = 4;
                    } else if (skip == false) {
                        currentPlayer = 3;
                    }
                }
                break;
            }
            while (currentPlayer == 3) {
                skip = false;
                int cardPlayed = 0; //this is similar to a boolean. 1's and 0's ya know. 
                System.out.println(); //spacing
                printDiscard(discardPile); //show the top card in the discard pile
                do {//do this while card played = 0
                    choice = rn.nextInt((comp2.getSize() - 1) + 1) + 1;
                    if (Card.getCardColor(comp2.getCard(choice - 1)) == 'a') { //gets the color of the card that user chose
                        //a means any so it's 2 possible cards
                        if (Card.getCardNumber(comp2.getCard(choice - 1)) == 13) {//13 is the wild card
                            int colorGot = 0;//for the do-while loop
                            comp2.removeCard(choice - 1);//remove the card from the player deck. it's no longer needed there
                            do {//do the following while colorGot = 0
                                int c3CC = 0;
                                c3CC = rn.nextInt((4 - 1) + 1) + 1;
                                if (c3CC == 1) {//if the input is blue
                                    discardPile.addCard(new Card(13, 'b'));//adds the same card we deleted from the players hand, but with blue as the color
                                    printDiscard(discardPile); //displays what's on the discard pile
                                    colorGot = 1;//basically a boolean. really. i should use those more.
                                } else if (c3CC == 2) { //same thing as blue. read blue, replace blue with red. thats this
                                    discardPile.addCard(new Card(13, 'r'));
                                    printDiscard(discardPile);
                                    colorGot = 1;
                                } else if (c3CC == 3) {
                                    discardPile.addCard(new Card(13, 'g'));
                                    printDiscard(discardPile);
                                    colorGot = 1;
                                } else if (c3CC == 4) {
                                    discardPile.addCard(new Card(13, 'y'));
                                    printDiscard(discardPile);
                                    colorGot = 1;
                                }
                            } while (colorGot == 0); //yea again..booleans. use em. bitch.
                        } else if (Card.getCardNumber(comp2.getCard(choice - 1)) == 14) {
                            int colorGot = 0; //again booleans would be cool.  butt fuck em. 
                            int playerGot = 0; //^ditto
                            int c3CC = 0;
                            c3CC = rn.nextInt((4 - 1) + 1) + 1;
                            comp2.removeCard(choice - 1);
                            do {
                                if (c3CC == 1) {
                                    discardPile.addCard(new Card(13, 'b'));
                                    printDiscard(discardPile);
                                    colorGot = 1;
                                } else if (c3CC == 2) {
                                    discardPile.addCard(new Card(13, 'r'));
                                    printDiscard(discardPile);
                                    colorGot = 1;
                                } else if (c3CC == 3) {
                                    discardPile.addCard(new Card(13, 'g'));
                                    printDiscard(discardPile);
                                    colorGot = 1;
                                } else if (c3CC == 4) {
                                    discardPile.addCard(new Card(13, 'y'));
                                    printDiscard(discardPile);
                                    colorGot = 1;
                                }
                            } while (colorGot == 0);
                            do {
                                choice = rn.nextInt((3 - 1) + 1) + 1;
                                if (choice == 1) {
                                    play1.addCard(deck);
                                    play1.addCard(deck);
                                    play1.addCard(deck);
                                    play1.addCard(deck);
                                    playerGot = 1;
                                }
                                if (choice == 2) {
                                    comp1.addCard(deck);
                                    comp1.addCard(deck);
                                    comp1.addCard(deck);
                                    comp1.addCard(deck);
                                    playerGot = 1;
                                }
                                if (choice == 3) {
                                    comp3.addCard(deck);
                                    comp3.addCard(deck);
                                    comp3.addCard(deck);
                                    comp3.addCard(deck);
                                    playerGot = 1;
                                }
                            } while (playerGot == 0);
                        }
                        cardPlayed = 1;
                    } else if (Card.getCardColor(comp2.getCard(choice - 1)) == Card.getCardColor(discardPile.getCard(discardPile.getSize() - 1)) || Card.getCardNumber(comp2.getCard(choice - 1)) == Card.getCardNumber(discardPile.getLast())) {
                        discardPile.addCard(comp1.getCard(choice - 1));
                        if (Card.getCardNumber(comp2.getCard(choice - 1)) == 10) {
                            skip = true;
                        } else if (Card.getCardNumber(comp2.getCard(choice - 1)) == 11) {
                            int playerGot = 0;
                            do {
                                choice = rn.nextInt((3 - 1) + 1) + 1;
                                if (choice == 1) {
                                    play1.addCard(deck);
                                    play1.addCard(deck);
                                    playerGot = 1;
                                }
                                if (choice == 2) {
                                    comp1.addCard(deck);
                                    comp1.addCard(deck);
                                    playerGot = 1;
                                }
                                if (choice == 3) {
                                    comp3.addCard(deck);
                                    comp3.addCard(deck);
                                    playerGot = 1;
                                }
                            } while (playerGot == 0);
                        } else if (Card.getCardNumber(comp2.getCard(choice - 1)) == 12) {
                            if (reverse == true) {
                                reverse = false;
                            } else if (reverse == false) {
                                reverse = true;
                            } else {

                            }
                            comp2.removeCard(choice - 1);
                            cardPlayed = 1;
                        }
                    }
                } while (cardPlayed == 0);
                if (comp2.getSize() == 1) {
                    System.out.println();
                    System.out.println("Computer 2 Calls Uno");
                }
                if (comp2.getSize() == 0) {
                    System.out.println();
                    System.out.println("Computer 2 has won");
                    gameEnded = true;
                }
                if (reverse == true) {
                    if (skip == true) {
                        currentPlayer = 1;
                    } else if (skip == false) {
                        currentPlayer = 2;
                    }
                } else if (reverse == false) {
                    if (skip == true) {
                        currentPlayer = 1;
                    } else if (skip == false) {
                        currentPlayer = 4;
                    }
                }
            }

            while (currentPlayer == 4) {
                skip = false;
                int cardPlayed = 0; //this is similar to a boolean. 1's and 0's ya know. 
                System.out.println(); //spacing

                printDiscard(discardPile); //show the top card in the discard pile
                do {//do this while card played = 0
                    choice = rn.nextInt((comp3.getSize() - 1) + 1) + 1;
                    if (Card.getCardColor(comp3.getCard(choice - 1)) == 'a') { //gets the color of the card that user chose
                        //a means any so it's 2 possible cards
                        if (Card.getCardNumber(comp3.getCard(choice - 1)) == 13) {//13 is the wild card
                            int colorGot = 0;//for the do-while loop
                            comp3.removeCard(choice - 1);//remove the card from the player deck. it's no longer needed there
                            do {//do the following while colorGot = 0
                                int c3CC = 0;
                                c3CC = rn.nextInt((4 - 1) + 1) + 1;
                                if (c3CC == 1) {//if the input is blue
                                    discardPile.addCard(new Card(13, 'b'));//adds the same card we deleted from the players hand, but with blue as the color
                                    printDiscard(discardPile); //displays what's on the discard pile
                                    colorGot = 1;//basically a boolean. really. i should use those more.
                                } else if (c3CC == 2) { //same thing as blue. read blue, replace blue with red. thats this
                                    discardPile.addCard(new Card(13, 'r'));
                                    printDiscard(discardPile);
                                    colorGot = 1;
                                } else if (c3CC == 3) {
                                    discardPile.addCard(new Card(13, 'g'));
                                    printDiscard(discardPile);
                                    colorGot = 1;
                                } else if (c3CC == 4) {
                                    discardPile.addCard(new Card(13, 'y'));
                                    printDiscard(discardPile);
                                    colorGot = 1;
                                }
                            } while (colorGot == 0); //yea again..booleans. use em. bitch.
                        } else if (Card.getCardNumber(comp3.getCard(choice - 1)) == 14) {
                            int colorGot = 0; //again booleans would be cool.  butt fuck em. 
                            int playerGot = 0; //^ditto
                            int c3CC = 0;
                            c3CC = rn.nextInt((4 - 1) + 1) + 1;
                            comp3.removeCard(choice - 1);
                            do {
                                if (c3CC == 1) {
                                    discardPile.addCard(new Card(13, 'b'));
                                    printDiscard(discardPile);
                                    colorGot = 1;
                                } else if (c3CC == 2) {
                                    discardPile.addCard(new Card(13, 'r'));
                                    printDiscard(discardPile);
                                    colorGot = 1;
                                } else if (c3CC == 3) {
                                    discardPile.addCard(new Card(13, 'g'));
                                    printDiscard(discardPile);
                                    colorGot = 1;
                                } else if (c3CC == 4) {
                                    discardPile.addCard(new Card(13, 'y'));
                                    printDiscard(discardPile);
                                    colorGot = 1;
                                }
                            } while (colorGot == 0);
                            do {
                                choice = rn.nextInt((3 - 1) + 1) + 1;
                                if (choice == 1) {
                                    play1.addCard(deck);
                                    play1.addCard(deck);
                                    play1.addCard(deck);
                                    play1.addCard(deck);
                                    playerGot = 1;
                                }
                                if (choice == 2) {
                                    comp2.addCard(deck);
                                    comp2.addCard(deck);
                                    comp2.addCard(deck);
                                    comp2.addCard(deck);
                                    playerGot = 1;
                                }
                                if (choice == 3) {
                                    comp1.addCard(deck);
                                    comp1.addCard(deck);
                                    comp1.addCard(deck);
                                    comp1.addCard(deck);
                                    playerGot = 1;
                                }
                            } while (playerGot == 0);
                        }
                        cardPlayed = 1;
                    } else if (Card.getCardColor(comp3.getCard(choice - 1)) == Card.getCardColor(discardPile.getCard(discardPile.getSize() - 1)) || Card.getCardNumber(comp3.getCard(choice - 1)) == Card.getCardNumber(discardPile.getLast())) {
                        discardPile.addCard(comp3.getCard(choice - 1));
                        if (Card.getCardNumber(comp3.getCard(choice - 1)) == 10) {
                            skip = true;
                        } else if (Card.getCardNumber(comp3.getCard(choice - 1)) == 11) {
                            int playerGot = 0;
                            do {
                                choice = rn.nextInt((3 - 1) + 1) + 1;
                                if (choice == 1) {
                                    play1.addCard(deck);
                                    play1.addCard(deck);
                                    playerGot = 1;
                                }
                                if (choice == 2) {
                                    comp2.addCard(deck);
                                    comp2.addCard(deck);
                                    playerGot = 1;
                                }
                                if (choice == 3) {
                                    comp1.addCard(deck);
                                    comp1.addCard(deck);
                                    playerGot = 1;
                                }
                            } while (playerGot == 0);
                        } else if (Card.getCardNumber(comp3.getCard(choice - 1)) == 12) {
                            if (reverse == true) {
                                reverse = false;
                            } else if (reverse == false) {
                                reverse = true;
                            } else {

                            }
                            comp3.removeCard(choice - 1);
                            cardPlayed = 1;
                        }
                    }
                } while (cardPlayed == 0);
                if (comp3.getSize() == 1) {
                    System.out.println();
                    System.out.println("Computer 1 Calls Uno");
                }
                if (comp3.getSize() == 0) {
                    System.out.println();
                    System.out.println("Computer 1 has won");
                }
                if (reverse == true) {
                    if (skip == true) {
                        currentPlayer = 4;
                    } else if (skip == false) {
                        currentPlayer = 1;
                    }
                } else if (reverse == false) {
                    if (skip == true) {
                        currentPlayer = 4;
                    } else if (skip == false) {
                        currentPlayer = 3;
                    }
                }
            }
        } while (!gameEnded);
    }

    public static void printHand(deal play1) {
        int display = 0;
        for (int x = 0; x < play1.getSize() - 1; x++) {
            display = x + 1;
            System.out.println(display + ". " + play1.getCard(x));
        }
        display++;
        System.out.println(display + ". Draw Card");
    }
    public static void wildCard(){
        System.out.print("What color do you want the deck to be?: ");
        String input = s.nextLine();//accepts all input from the user
        if ("blue".equals(input.toLowerCase())) {//if the input is blue
            discardPile.addCard(new Card(13, 'b'));//adds the same card we deleted from the players hand, but with blue as the color
            printDiscard(discardPile); //displays what's on the discard pile
        } else if ("red".equals(input.toLowerCase())) { //same thing as blue. read blue, replace blue with red. thats this
            discardPile.addCard(new Card(13, 'r'));
            printDiscard(discardPile);
        } else if ("green".equals(input.toLowerCase())) { //same thing as blue. read blue, replace blue with green. thats this
            discardPile.addCard(new Card(13, 'g'));
            printDiscard(discardPile);
        } else if ("yellow".equals(input.toLowerCase())) { //same thing as blue. read blue, replace blue with yellow. thats this
            discardPile.addCard(new Card(13, 'y'));
            printDiscard(discardPile);
        } else {
            System.out.println("Type: Blue, Green, Red or Yellow"); //get passive-aggressive with that bitch
            wildCard(); //RECURSION
        }
    }
    public static void printDiscard(deal discardPile) {
        System.out.print("Discard Pile: ");
        System.out.println(discardPile.getLast());
    }
}
