package csc439teamplatypus.cardgame.golfgame;

import csc439teamplatypus.cardgame.Card;
import csc439teamplatypus.cardgame.CardFace;

import java.util.Scanner;


/**
 * CLIView displays player input in parallel with controller
 */
public class CLIView extends View {

    Scanner input;
    int turn = 0;

    public CLIView() {
        this.input = new Scanner(System.in);
    }

    public int promptForDiscard() {

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
    public void chooseCardToFlip() {

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
            } else {

                System.out.println("Card number " + cardToFlip + " cannot be flipped over.");
            }

        }
    }

    /**
     * Handles deck interaction with player input, keep or discard card you drew
     */
    public void chooseDrawSource() {

        boolean inputCompleted = false;

        while (!inputCompleted) {

            System.out.println("Would you like to draw from the deck or take the card off the top of the discard pile?");
            System.out.print("Enter \"Deck\" or \"Discard\" (without the quotes: )");
            String decision_deckOrDiscard = input.next();

            switch (decision_deckOrDiscard) {

                case "Deck" -> {

                    Card drawnCard = drawFromPile();
                    System.out.println("You drew the " + drawnCard.getNumber() + " of " + drawnCard.getSuit());
                    System.out.print("Would you like to keep it? Enter \"Yes\" or \"No\": ");
                    String decision_keepOrDiscard = input.next();

                    switch (decision_keepOrDiscard) {

                        case "Yes" -> {

                            int cardToDiscard = promptForDiscard();
                            discardHeldCard(getCurrentPlayerNumber(), cardToDiscard);
                            getCurrentPlayerHand()[cardToDiscard] = drawnCard;
                            inputCompleted = true;
                        }

                        case "No" -> {

                            discardUnheldCard(drawnCard);
                            System.out.println("Card discarded");
                            inputCompleted = true;
                        }
                    }
                }

                case "Discard" -> {

                    drawDiscard(getCurrentPlayerNumber(), promptForDiscard());
                    inputCompleted = true;
                }

                default -> {
                    System.out.println("Could not parse input: " + decision_deckOrDiscard);
                }
            }
        }
    }


    /**
     * Handles next turn of golf
     */
    public void nextTurn() {
        if (cardsRemaining()) {
            System.out.println("It's player " + (getCurrentPlayerNumber() + 1) + "'s turn!");
            Card[] hand=getCurrentPlayerHand();
            printPlayerHand(hand);
            printTopOfDiscardPile();

            boolean inputCompleted = false;

            while (!inputCompleted) {


                if (hasFaceDownCard(getCurrentPlayerNumber())) {

                    System.out.println("Would you like to flip one of your cards over or draw a new card?");
                    System.out.print("Enter \"Draw\" or \"Flip\": ");
                    String decision_DrawOrFlip = input.next();

                    switch (decision_DrawOrFlip) {

                        case "Draw" -> {

                            chooseDrawSource();
                            inputCompleted = true;
                        }

                        case "Flip" -> {

                            chooseCardToFlip();
                            inputCompleted = true;
                        }

                        default -> {
                            System.out.println("Could not parse input " + decision_DrawOrFlip);
                        }
                    }
                } else {

                    chooseDrawSource();
                    inputCompleted = true;
                }
            }
            System.out.println("\n");
            nextTurn();
        }
        else {
            System.out.print("There are no more cards remaining");
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
        System.out.println("\tYou have chosen " + numberOfPlayers + " players!");
        setNumberOfPlayers(numberOfPlayers);
        setPlayerHand();
        nextTurn();
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
    public void startGame() {
        System.out.println("\t\tWelcome to Golf!");
        System.out.println("\tHow many people will be playing?");
        System.out.print("Please enter a number between 2 and 7: ");
        int numOfPlayers = Integer.parseInt(input.next());
        setNumOfPlayers(numOfPlayers);
    }
}
