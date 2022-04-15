package csc439teamplatypus.cardgame.golfgame;

import csc439teamplatypus.cardgame.Card;
import csc439teamplatypus.cardgame.CardFace;

import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class CLIViewTest extends CLIView {
    Scanner input;
    PrintStream stream;

    public CLIViewTest() {
        this.input = new Scanner(System.in);
    }

    public int promptForDiscard(int cardToDis) {

        boolean inputCompleted = false;

        while (!inputCompleted) {

            System.out.print("Enter the index of the card you would like to discard: ");
            int cardToDiscard = input.nextInt();

            if (cardToDiscard < 0 || cardToDiscard > 5)
                System.out.println(cardToDiscard + " is not a valid index. Please enter a number between 0 & 5");

            else {
                inputCompleted = true;
                return cardToDiscard;
            }
        }

        return -1;
    }

    /**
     * Handles player decision on flipping or not flipping a face down card
     */
    public void chooseCardToFlip(int cardToFlipOrDis) {

        boolean inputCompleted = false;

        while (!inputCompleted) {

            System.out.println("Which card would you like to flip over? You can only flip over a face-down card.");
            System.out.print("Enter one of: ");
            for (int i = 0; i < 6; i++)
                if (getCurrentPlayerHand()[i].getCardFace() == CardFace.DOWN)
                    System.out.print(i + " ");
            System.out.print(": ");
            int cardToFlip = input.nextInt();

            if (getCurrentPlayerHand()[cardToFlip].getCardFace() == CardFace.DOWN) {

                flipCard(getCurrentPlayerNumber(), cardToFlip);
                inputCompleted = true;
                System.out.println("You turned over a " + getCurrentPlayerHand()[cardToFlip].getNumber()
                        + " of " + getCurrentPlayerHand()[cardToFlip].getSuit() + "s");
                System.out.println("Your new hand is: ");
                printPlayerHand(getCurrentPlayerHand());
            } else {

                System.out.println("Card number " + cardToFlip + " cannot be flipped over.");
            }
        }
    }


    /**
     * Handles deck interaction with player input, keep or discard card you drew
     */
    public void drawFromDeck(String deckDecision, int cardToFlip) {
        Card drawnCard = drawFromPile();
        boolean deckDrawInputCompleted = false;

        while (!deckDrawInputCompleted) {
            System.out.println("You drew the " + drawnCard.getNumber() + " of " + drawnCard.getSuit() + "s");
            System.out.print("Would you like to keep it? Enter \"Yes\" or \"No\": ");
            String decision_keepOrDiscard = input.next();

            switch (decision_keepOrDiscard) {
                case "Yes" -> {
                    int cardToDiscard = promptForDiscard();
                    Card cardToDis = getCurrentPlayerHand()[cardToDiscard];
                    getCurrentPlayerHand()[cardToDiscard] = drawnCard;
                    System.out.println("Your new hand is: ");
                    printPlayerHand(getCurrentPlayerHand());
                    discardUnheldCard(cardToDis);
                    deckDrawInputCompleted = true;
                }
                case "No" -> {
                    System.out.println("Card discarded");
                    chooseCardToFlip(cardToFlip);
                    discardUnheldCard(drawnCard);
                    deckDrawInputCompleted = true;
                }
            }
        }
    }

    //Overrides drawDiscard to be able to print off what the player's hand will be before ending their turn
    protected void drawDiscard(int playerNumber, int cardToReplace) {

        Card[] newHandToBe = new Card[6];

        for (int i = 0; i < 6; i++)
            newHandToBe[i] = getCurrentPlayerHand()[i];
        newHandToBe[cardToReplace] = viewTopOfDiscardPile();
        System.out.println("Your new hand is: ");
        printPlayerHand(newHandToBe);
        super.drawDiscard(playerNumber, cardToReplace);
    }

    /**
     * Handles next turn of golf
     */
    public void nextTurn(String inputDraw, String deckDecision, int cardToFlipOrDis) {
        if (cardsRemaining()) {
            System.out.println("It's player " + (getCurrentPlayerNumber() + 1) + "'s turn!");
            Card[] hand = getCurrentPlayerHand();
            printPlayerHand(hand);
            printTopOfDiscardPile();

            boolean inputCompleted = false;

            while (!inputCompleted) {

                System.out.println("Would you like to draw a new card from the deck or take from the top " +
                        "of the discard pile?");
                System.out.print("Enter \"Deck\" or \"Discard\" (Type \"QUIT\" to exit the game): ");
                switch (inputDraw) {
                    case "QUIT" -> {
                        endGame();
                    }
                    case "Deck" -> {
                        drawFromDeck(deckDecision, cardToFlipOrDis);
                        inputCompleted = true;
                    }
                    case "Discard" -> {
                        drawDiscard(getCurrentPlayerNumber(), promptForDiscard(cardToFlipOrDis));
                        inputCompleted = true;
                    }
                }
            }
            System.out.println("\n");
        }
        else {
            System.out.println("There are no more cards remaining");
            endGame();
        }
    }

    /**
     * Prints top of discard pile
     */
    protected void printTopOfDiscardPile() {
        Card topCard = viewTopOfDiscardPile();
        System.out.println("\nThe top card on the discard pile is the " + topCard.getNumber()
                + " of " + topCard.getSuit() + "s");
    }
    /**
     * Setter method for the input number of players sets players then goes to next turn
     *
     * @param numberOfPlayers
     */
    protected void setNumOfPlayers(int numberOfPlayers) {
        System.out.println("\tYou have chosen " + numberOfPlayers + " players!\n");
        setNumberOfPlayers(numberOfPlayers);
        setPlayerHand();
    }

    protected void printPlayerHand(Card hand[]) {
        for (int i = 0; i < hand.length; i++) {
            if (i == 3) {
                System.out.print("\n");
            }
            if (hand[i].getCardFace() == CardFace.DOWN) {
                if (i < 2 || (i > 2 && i < 5)) {
                    System.out.print("Face Down, ");
                } else {
                    System.out.print("Face Down");
                }
            } else {
                if (i < 2 || (i > 2 && i < 5)) {
                    System.out.print(hand[i].getNumber() + " of " + hand[i].getSuit() + "s, ");
                } else {
                    System.out.print(hand[i].getNumber() + " of " + hand[i].getSuit() + "s");
                }
            }
        }
    }

    /**
     * Initializer of golf game with text displaying you're in the game and asking how many players
     */
    public void startGame(int numOfPlayers) {
        System.out.println("\t\tWelcome to Golf!");
        System.out.println("\tHow many people will be playing?");
        System.out.print("Please enter a number between 2 and 7: ");
        setNumOfPlayers(numOfPlayers);
    }

    /** Compares user's input on their turn to the key command "QUIT" to give ability to quit game
     * @author Abbi Landers*/
    public void endGame() {
        System.out.println("Thank you for playing!");
        System.exit(0);
    }
}
