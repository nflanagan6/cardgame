package csc439teamplatypus.cardgame.golfgame;

import csc439teamplatypus.cardgame.Card;
import csc439teamplatypus.cardgame.CardFace;

import javax.sound.midi.SysexMessage;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;


public class CLIView extends View {
    private int numOfPlayers;
    Scanner input;

    public CLIView() {
        this.input = new Scanner(System.in);
    }

    public int promptForDiscard() {

        boolean inputCompleted = false;

        while (!inputCompleted) {
            printPlayerHand(getCurrentPlayerHand());
            System.out.print("\nEnter the index of the card you would like to discard: ");
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
            printPlayerHand(getCurrentPlayerHand());
            System.out.print("\nEnter one of: ");
            for (int i = 0; i < 6; i++) {
                if (getCurrentPlayerHand()[i].getCardFace() == CardFace.DOWN) {
                    System.out.print(i + " ");
                }
            }
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
            printPlayerHand(getCurrentPlayerHand());
            System.out.println("\nYou drew the " + drawnCard.getNumber() + " of " + drawnCard.getSuit() + "s");
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
                    System.out.println("\nCard discarded");
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
     * Cards remaining in the deck and no player has all of their cards face up
     *
     */
    public void nextTurn() {
        if (cardsRemaining() && !checkCards()) {
            System.out.println("It's player " + (getCurrentPlayerNumber() + 1) + "'s turn!");
            Card[] hand = getCurrentPlayerHand();
            printPlayerHand(hand);
            printTopOfDiscardPile();

            boolean inputCompleted = false;

            while (!inputCompleted) {

                System.out.println("\nWould you like to draw a new card from the deck or take from the top " +
                        "of the discard pile?");
                System.out.print("Enter \"Deck\" or \"Discard\" (Type \"QUIT\" to exit the game, or \"Scores\" to view the scores): ");
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
                        printTopOfDiscardPile();
                        drawDiscard(getCurrentPlayerNumber(), promptForDiscard());
                        inputCompleted = true;
                    }
                    case "Scores" -> {
                        printPlayerScores();
                    }
                    default -> {
                        System.out.println("Could not parse input " + decision_chooseDrawSource);
                    }
                }
            }
            System.out.println("\n");
            nextTurn();
        }
        else if (getNumberOfPlayedHoles() == getNumberOfHoles() - 1) {
            System.out.println("The final scores of the game are: ");
            updateScores();
            printEndOfHole();
            endGame();
        }
        else {
            updateScores();
            System.out.print("Hole " + (getNumberOfPlayedHoles() + 1) + " is over!\n\n");
            printEndOfHole();
            nextHole();
            System.out.println("It is now Hole " + (getNumberOfPlayedHoles() + 1) + " of " + getNumberOfHoles() + "\n");
            nextTurn();
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
    }

    /**
     * Setter method for the input number of holes.
     * @param numberOfHoles - The number of holes that will be played
     */
    protected void setNumOfHoles(int numberOfHoles) {
        System.out.println("\tYou have chosen " + numberOfHoles + " holes!\n");
        setNumberOfHoles(numberOfHoles);
    }

    /** Prints each Card's suit and number in a Card[].
     *
     * @param hand Card[] of Cards to display
     */
    protected void printPlayerHand(Card[] hand) {
        System.out.println();
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
        System.out.println();
    }

    /** Prints the current players' scores in ascending order
     *
     */
    protected void printPlayerScores() {
        int[] playerRank = new int[numOfPlayers];
        int[] currentScores;
        for (int i = 0; i < playerRank.length; i++) {
            playerRank[i] = i + 1;
        }
        currentScores = getPlayerScores();

        for (int i = 0; i < currentScores.length - 1; i++) {
            for (int j = i + 1; j < currentScores.length; j++) {
                if (currentScores[i] > currentScores[j]) {

                    int temp = currentScores[j];
                    currentScores[j] = currentScores[i];
                    currentScores[i] = temp;

                    temp = playerRank[j];
                    playerRank[j] = playerRank[i];
                    playerRank[i] = temp;
                }
            }
        }

        System.out.println("\nScoreboard: Hole " + (getNumberOfPlayedHoles() + 1)
                + " of " + getNumberOfHoles());

        for (int i = 0; i < currentScores.length; i++) {
            System.out.println("Player " + playerRank[i] + ": " + currentScores[i]);
        }
    }

    /**
     * Prints when the turn is over
     */
    public void printEndOfHole() {
        int[] playerRank = new int[numOfPlayers];
        int[] currentScores;
        for (int i = 0; i < playerRank.length; i++) {
            playerRank[i] = i + 1;
        }

        currentScores = endOfHoleScores();

        for (int i = 0; i < currentScores.length - 1; i++) {
            for (int j = i + 1; j < currentScores.length; j++) {
                if (currentScores[i] > currentScores[j]) {

                    int temp = currentScores[j];
                    currentScores[j] = currentScores[i];
                    currentScores[i] = temp;

                    temp = playerRank[j];
                    playerRank[j] = playerRank[i];
                    playerRank[i] = temp;
                }
            }
        }

        System.out.println("Scoreboard: Hole " + (getNumberOfPlayedHoles() + 1)
                + " of " + getNumberOfHoles());

        for (int i = 0; i < currentScores.length; i++) {
            System.out.println("Player " + playerRank[i] + ": " + currentScores[i]);
        }
        System.out.println();

        if (getNumberOfPlayedHoles() == getNumberOfHoles() - 1 && (checkCards() || !cardsRemaining())) {
            for (int i = 0; i < currentScores.length; i++) {
                if (playerRank[i] == 1) {
                    System.out.println("The winner of the game is: Player " + playerRank[i]);
                }
            }
        }
    }

    /** Asks player to enter the number of people playing golf and starts the game
     *
     */
    public void startGame() {
        System.out.println("\t\t\tWelcome to Golf!");
        System.out.println("\tHow many people will be playing?");
        System.out.print("Please enter a number between 2 and 7: ");
        numOfPlayers = Integer.parseInt(input.next());
        setNumOfPlayers(numOfPlayers);
        System.out.println("How many holes would you like to play?");
        System.out.print("\tPlease enter 9 or 18: ");
        int numOfHoles = Integer.parseInt(input.next());
        setNumOfHoles(numOfHoles);
        nextTurn();
    }

    /** Compares user's input on their turn to the key command "QUIT" to give ability to quit game
     * @author Abbi Landers*/
    public void endGame() {
        System.out.println("Thank you for playing!");
        System.exit(0);
    }
}