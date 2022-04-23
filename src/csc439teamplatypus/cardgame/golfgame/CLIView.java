package csc439teamplatypus.cardgame.golfgame;

import csc439teamplatypus.cardgame.Card;
import csc439teamplatypus.cardgame.CardFace;
import java.util.Scanner;


public class CLIView extends View {

    Scanner input;

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

    /** Prompts the player to select a face-down Card to flip over and displays their new hand
     *
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
                System.out.println("You turned over a " + getCurrentPlayerHand()[cardToFlip].getNumber()
                + " of " + getCurrentPlayerHand()[cardToFlip].getSuit() + "s");
                System.out.println("Your new hand is: ");
                printPlayerHand(getCurrentPlayerHand());
            } else {

                System.out.println("Card number " + cardToFlip + " cannot be flipped over.");
            }
        }
    }


    /** Draws a Card from the deck and asks the player whether they want to keep it. If they choose to keep it,
     * they're asked which card it should replace.
     *
     */
    public void drawFromDeck() {
        Card drawnCard = drawCardFromDeck();
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
                    chooseCardToFlip();
                    discardUnheldCard(drawnCard);
                    deckDrawInputCompleted = true;
                }
                default -> {
                    System.out.println("Could not parse input. Enter \"Yes\" or \"No\": ");
                }
            }
        }
    }

    //Overrides drawDiscard to be able to print off what the player's hand will be before ending their turn
    @Override
    protected void drawDiscard(int playerNumber, int cardToReplace) {

        Card[] newHandToBe = new Card[6];

        for (int i = 0; i < 6; i++)
            newHandToBe[i] = getCurrentPlayerHand()[i];
        newHandToBe[cardToReplace] = viewTopOfDiscardPile();
        System.out.println("Your new hand is: ");
        printPlayerHand(newHandToBe);
        super.drawDiscard(playerNumber, cardToReplace);
    }

    /** Prompts the player to complete their turn and moves on to the next player's turn while there are
     * Cards remaining in the deck
     *
     */
    public void nextTurn() {
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
                String decision_chooseDrawSource = input.next();
                switch (decision_chooseDrawSource) {
                    case "QUIT" -> {
                        endGame();
                    }
                    case "Deck" -> {
                        drawFromDeck();
                        inputCompleted = true;
                    }
                    case "Discard" -> {
                        drawDiscard(getCurrentPlayerNumber(), promptForDiscard());
                        inputCompleted = true;
                    }
                    default -> {
                        System.out.println("Could not parse input " + decision_chooseDrawSource);
                    }
                }
            }
            System.out.println("\n");
            nextTurn();
        }
        else {
            System.out.println("There are no more cards remaining");
            endGame();
        }
    }

    /** Displays the top card on the discard pile
     *
     */
    protected void printTopOfDiscardPile() {
        Card topCard = viewTopOfDiscardPile();
        System.out.println("\nThe top card on the discard pile is the " + topCard.getNumber()
                + " of " + topCard.getSuit() + "s");
    }
    /**
     * Setter method for the input number of players. Sets players, then goes to next turn
     *
     * @param numberOfPlayers The number of people playing the game
     */
    protected void setNumOfPlayers(int numberOfPlayers) {
        System.out.println("\tYou have chosen " + numberOfPlayers + " players!\n");
        setNumberOfPlayers(numberOfPlayers);
        setPlayerHand();
        nextTurn();
    }

    /** Prints each Card's suit and number in a Card[].
     *
     * @param hand Card[] of Cards to display
     */
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

    /** Asks player to enter the number of people playing golf and starts the game
     *
     */
    public void startGame() {
        System.out.println("\t\tWelcome to Golf!");
        System.out.println("\tHow many people will be playing?");
        System.out.print("Please enter a number between 2 and 7: ");
        int numOfPlayers = Integer.parseInt(input.next());
        setNumOfPlayers(numOfPlayers);
    }

    /** Compares user's input on their turn to the key command "QUIT" to give ability to quit game
     * @author Abbi Landers*/
    public void endGame() {
        System.out.println("Thank you for playing!");
        System.exit(0);
    }
}
