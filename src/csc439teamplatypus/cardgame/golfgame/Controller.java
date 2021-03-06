package csc439teamplatypus.cardgame.golfgame;

import csc439teamplatypus.cardgame.Card;
import csc439teamplatypus.cardgame.CardFace;
import csc439teamplatypus.cardgame.CardNumber;
import csc439teamplatypus.cardgame.CardSuit;

import java.util.ArrayList;
import java.util.Random;
public class Controller {

    private ArrayList<GolfCard> deck;
    private ArrayList<GolfCard> discard;
    private GolfCard[][] playerHands;
    private int[] playerScores;
    private int numberOfPlayers;
    private int numberOfHoles;
    private final View view;
    private int numberOfPlayedTurns;
    private int numberOfPlayedHoles;
    private boolean allCardsUp = true;
    Random rand = new Random();

    /** Creates a new Controller
     *
     * @param view
     */
    protected Controller(View view) {
        this.view = view;
    }

    /**
     * Returns whether there are Cards left to draw from in the deck
     *
     * @return Whether the size of the deck is > 0
     * @author Nathan Flanagan
     */
    protected boolean cardsRemaining() {
        return deck.size() > 0;
    }

    /**
     * Adds the cards in numberOfDecks standard decks to deck
     *
     * @param deck          The deck the Cards should be added to
     * @param numberOfDecks The number of standard decks that should be added to deck
     * @author Nathan Flanagan
     */
    private void createCardDeck(ArrayList<GolfCard> deck, int numberOfDecks) {
        if (!(deck.size() == 0)) {
            deck.clear();
        }
        for (int i = numberOfDecks; i > 0; i--) {

            deck.add(new GolfCard(CardSuit.CLUB, CardNumber.TWO, CardFace.DOWN));
            deck.add(new GolfCard(CardSuit.CLUB, CardNumber.THREE, CardFace.DOWN));
            deck.add(new GolfCard(CardSuit.CLUB, CardNumber.FOUR, CardFace.DOWN));
            deck.add(new GolfCard(CardSuit.CLUB, CardNumber.FIVE, CardFace.DOWN));
            deck.add(new GolfCard(CardSuit.CLUB, CardNumber.SIX, CardFace.DOWN));
            deck.add(new GolfCard(CardSuit.CLUB, CardNumber.SEVEN, CardFace.DOWN));
            deck.add(new GolfCard(CardSuit.CLUB, CardNumber.EIGHT, CardFace.DOWN));
            deck.add(new GolfCard(CardSuit.CLUB, CardNumber.NINE, CardFace.DOWN));
            deck.add(new GolfCard(CardSuit.CLUB, CardNumber.TEN, CardFace.DOWN));
            deck.add(new GolfCard(CardSuit.CLUB, CardNumber.JACK, CardFace.DOWN));
            deck.add(new GolfCard(CardSuit.CLUB, CardNumber.QUEEN, CardFace.DOWN));
            deck.add(new GolfCard(CardSuit.CLUB, CardNumber.KING, CardFace.DOWN));
            deck.add(new GolfCard(CardSuit.CLUB, CardNumber.ACE, CardFace.DOWN));

            deck.add(new GolfCard(CardSuit.SPADE, CardNumber.TWO, CardFace.DOWN));
            deck.add(new GolfCard(CardSuit.SPADE, CardNumber.THREE, CardFace.DOWN));
            deck.add(new GolfCard(CardSuit.SPADE, CardNumber.FOUR, CardFace.DOWN));
            deck.add(new GolfCard(CardSuit.SPADE, CardNumber.FIVE, CardFace.DOWN));
            deck.add(new GolfCard(CardSuit.SPADE, CardNumber.SIX, CardFace.DOWN));
            deck.add(new GolfCard(CardSuit.SPADE, CardNumber.SEVEN, CardFace.DOWN));
            deck.add(new GolfCard(CardSuit.SPADE, CardNumber.EIGHT, CardFace.DOWN));
            deck.add(new GolfCard(CardSuit.SPADE, CardNumber.NINE, CardFace.DOWN));
            deck.add(new GolfCard(CardSuit.SPADE, CardNumber.TEN, CardFace.DOWN));
            deck.add(new GolfCard(CardSuit.SPADE, CardNumber.JACK, CardFace.DOWN));
            deck.add(new GolfCard(CardSuit.SPADE, CardNumber.QUEEN, CardFace.DOWN));
            deck.add(new GolfCard(CardSuit.SPADE, CardNumber.KING, CardFace.DOWN));
            deck.add(new GolfCard(CardSuit.SPADE, CardNumber.ACE, CardFace.DOWN));

            deck.add(new GolfCard(CardSuit.DIAMOND, CardNumber.TWO, CardFace.DOWN));
            deck.add(new GolfCard(CardSuit.DIAMOND, CardNumber.THREE, CardFace.DOWN));
            deck.add(new GolfCard(CardSuit.DIAMOND, CardNumber.FOUR, CardFace.DOWN));
            deck.add(new GolfCard(CardSuit.DIAMOND, CardNumber.FIVE, CardFace.DOWN));
            deck.add(new GolfCard(CardSuit.DIAMOND, CardNumber.SIX, CardFace.DOWN));
            deck.add(new GolfCard(CardSuit.DIAMOND, CardNumber.SEVEN, CardFace.DOWN));
            deck.add(new GolfCard(CardSuit.DIAMOND, CardNumber.EIGHT, CardFace.DOWN));
            deck.add(new GolfCard(CardSuit.DIAMOND, CardNumber.NINE, CardFace.DOWN));
            deck.add(new GolfCard(CardSuit.DIAMOND, CardNumber.TEN, CardFace.DOWN));
            deck.add(new GolfCard(CardSuit.DIAMOND, CardNumber.JACK, CardFace.DOWN));
            deck.add(new GolfCard(CardSuit.DIAMOND, CardNumber.QUEEN, CardFace.DOWN));
            deck.add(new GolfCard(CardSuit.DIAMOND, CardNumber.KING, CardFace.DOWN));
            deck.add(new GolfCard(CardSuit.DIAMOND, CardNumber.ACE, CardFace.DOWN));

            deck.add(new GolfCard(CardSuit.HEART, CardNumber.TWO, CardFace.DOWN));
            deck.add(new GolfCard(CardSuit.HEART, CardNumber.THREE, CardFace.DOWN));
            deck.add(new GolfCard(CardSuit.HEART, CardNumber.FOUR, CardFace.DOWN));
            deck.add(new GolfCard(CardSuit.HEART, CardNumber.FIVE, CardFace.DOWN));
            deck.add(new GolfCard(CardSuit.HEART, CardNumber.SIX, CardFace.DOWN));
            deck.add(new GolfCard(CardSuit.HEART, CardNumber.SEVEN, CardFace.DOWN));
            deck.add(new GolfCard(CardSuit.HEART, CardNumber.EIGHT, CardFace.DOWN));
            deck.add(new GolfCard(CardSuit.HEART, CardNumber.NINE, CardFace.DOWN));
            deck.add(new GolfCard(CardSuit.HEART, CardNumber.TEN, CardFace.DOWN));
            deck.add(new GolfCard(CardSuit.HEART, CardNumber.JACK, CardFace.DOWN));
            deck.add(new GolfCard(CardSuit.HEART, CardNumber.QUEEN, CardFace.DOWN));
            deck.add(new GolfCard(CardSuit.HEART, CardNumber.KING, CardFace.DOWN));
            deck.add(new GolfCard(CardSuit.HEART, CardNumber.ACE, CardFace.DOWN));
        }
    }

    /**
     * Adds a Card to the top of the discard pile and increments numberOfPlayedTurns by 1. Ensures the card
     * added to the discard pile is face-up
     *
     * @param cardToDiscard The Card to be placed on top of the discard pile
     * @author Nathan Flanagan
     */

    protected void discard(GolfCard cardToDiscard) {
        cardToDiscard.setCardFace(CardFace.UP);
        discard.add(cardToDiscard);
        numberOfPlayedTurns++;
    }

    /**
     * Replaces the Card at playerHands[playerNumber][cardToReplace] with the Card off the top of the
     * discard pile. Increments numberOfPlayedTurns by 1.
     *
     * @param playerNumber  The current player
     * @param cardToReplace The index of the card the current player wishes to replace
     */
    protected void drawDiscard(int playerNumber, int cardToReplace) {
        playerHands[playerNumber][cardToReplace].setCardFace(CardFace.UP);
        GolfCard temp = discard.remove(discard.size() - 1);
        discard.add(playerHands[playerNumber][cardToReplace]);
        playerHands[playerNumber][cardToReplace] = temp;
        numberOfPlayedTurns++;
    }

    /**
     * Returns a Card face-up off the deck
     *
     * @return A random card from the deck
     * @author Nathan Flanagan
     */
    protected GolfCard drawFromDeck() {
        GolfCard cardToReturn = deck.remove(rand.nextInt(deck.size()));
        cardToReturn.setCardFace(CardFace.UP);
        return cardToReturn;
    }

    /**
     * Flips the Card at playerHands[playerNumber][cardToFlip] if it is face-down. Throws an
     * IllegalArgumentException if already face-up
     *
     * @param playerNumber The player whose Card should be flipped
     * @param cardToFlip   The index of the Card that should be turned face-up
     * @author Nathan Flanagan
     */
    protected void flipCard(int playerNumber, int cardToFlip) {
        if (playerHands[playerNumber][cardToFlip].getCardFace() != CardFace.DOWN)
            throw new IllegalArgumentException("You can only flip over a card that is face-down");

        else {

            playerHands[playerNumber][cardToFlip].setCardFace(CardFace.UP);
        }
    }

    /** Returns the total number of holes that will be played in the game
     *
     * @return numberOfHoles
     * @author Nathan Flanagan
     */
    protected int getNumberOfHoles() {
        return numberOfHoles;
    }

    /**
     * Returns the number of turns that have been completed so far in the game
     *
     * @return The number of completed turns
     * @author Nathan Flanagan
     */
    protected int getNumberOfPlayedTurns() {
        return numberOfPlayedTurns;
    }

    /** Returns the number of holes that have already been played
     *
     * @return numberOfPlayedHoles
     * @author Ethan Hunt
     */
    protected int getNumberOfPlayedHoles() {
        return numberOfPlayedHoles;
    }

    /**
     * Returns the number of players in the game
     *
     * @return The number of players in the game
     * @author Nathan Flanagan
     */
    protected int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    /**
     * Returns a player's current hand
     *
     * @param playerNumber The player whose hand should be returned
     * @return The player's hand
     * @author Nathan Flanagan
     */
    protected GolfCard[] getPlayerHand(int playerNumber) {
        if (playerNumber >= playerHands.length || playerNumber < 0) {
            throw new IndexOutOfBoundsException();
        }

        return playerHands[playerNumber];
    }

    /** Returns an int[] containing all players' current scores
     *
     * @return int array of current scores
     */
    protected int[] getPlayerScores() {
        int[] updatedPlayerScores = new int[numberOfPlayers];

        System.arraycopy(playerScores, 0, updatedPlayerScores, 0, playerScores.length);

        for (int i = 0; i < numberOfPlayers; i++) {
            int currentHoleScore = 0;
            for (int x = 0, y = 3; x <= 2 && y <= 5; x++, y++) {
                if (!(playerHands[i][x].getCardFace() == CardFace.UP && playerHands[i][y].getCardFace() == CardFace.UP
                        && playerHands[i][x].getNumber() == playerHands[i][y].getNumber())) {
                    currentHoleScore += playerHands[i][x].getCardFace() == CardFace.UP ? playerHands[i][x].getGolfValue() : 0;
                    currentHoleScore += playerHands[i][y].getCardFace() == CardFace.UP ? playerHands[i][y].getGolfValue() : 0;
                }
            }

            updatedPlayerScores[i] += currentHoleScore;
        }

        return updatedPlayerScores;
    }

    /**
     * Getter method for just returning player scores.
     * @return playerScores scores at given time
     */
    protected int[] getEndOfHoleScores() {
        return playerScores;
    }

    /**
     * Updates player scores by running through for loops and assigning score based on golf card game values.
     */
    protected void updatePlayerScores() {
        for (int i = 0; i < numberOfPlayers; i++) {
            for (int j = 0; j < 6; j++) {
                playerHands[i][j].setCardFace(CardFace.UP);
            }
        }
        for (int i = 0; i < numberOfPlayers; i++) {
            int currentHoleScore = 0;
            for (int x = 0, y = 3; x <= 2 && y <= 5; x++, y++) {
                if (!(playerHands[i][x].getCardFace() == CardFace.UP && playerHands[i][y].getCardFace() == CardFace.UP
                        && playerHands[i][x].getNumber() == playerHands[i][y].getNumber())) {
                    currentHoleScore += playerHands[i][x].getCardFace() == CardFace.UP ? playerHands[i][x].getGolfValue() : 0;
                    currentHoleScore += playerHands[i][y].getCardFace() == CardFace.UP ? playerHands[i][y].getGolfValue() : 0;
                }
            }

            playerScores[i] = playerScores[i] + currentHoleScore;
        }
    }

    /**
     * Accepts the number of players (must be between 2 and 7, inclusively) and instantiates deck, discard,
     * playerHands, numberOfPlayers, and the numberOfPlayedTurns
     *
     * @param numberOfPlayers The number of people playing Golf. Must be inclusively between 2 and 7
     * @author Nathan Flanagan
     */
    protected void setNumberOfPlayers(int numberOfPlayers) {
        if (numberOfPlayers < 2 || numberOfPlayers > 7) {
            throw new IllegalArgumentException("The number of players must be between 2 and 7 (inclusively)");
        } else {
            this.numberOfPlayers = numberOfPlayers;
            playerHands = new GolfCard[numberOfPlayers][6];
            deck = new ArrayList<>();
            if (numberOfPlayers > 4)
                createCardDeck(deck, 2);
            else
                createCardDeck(deck, 1);
            discard = new ArrayList<>();
            numberOfPlayedTurns = 0;
            playerScores = new int[numberOfPlayers];
            for (int i = 0; i < numberOfPlayers; i++) {
                playerScores[i] = 0;
            }
        }
    }

    /**
     * Simple setter method for number of holes, handles case where it's not 9 or 18.
     * @param numberOfHoles 9 or 18
     */
    protected void setNumberOfHoles(int numberOfHoles) {
        if (numberOfHoles != 9 && numberOfHoles != 18) {
            throw new IllegalArgumentException("The number of holes entered must be either 9 or 18");
        }
        else {
            this.numberOfHoles = numberOfHoles;
            numberOfPlayedHoles = 0;
        }
    }

    /**
     * Returns the last Card placed on the discard pile without moving it off the pile
     *
     * @return The top Card on the discard pile
     * @author Nathan Flanagan
     */
    protected Card viewTopOfDiscardPile() {

        return discard.get(discard.size() - 1);
    }

    /**
     * Sets each player's hand by dealing them cards one at a time until they have a full hand,
     * then it takes two of those cards and makes them face up. Also takes one card off the deck and places
     * it on the discard pile (face-up) without incrementing numberOfPlayedTurns
     *
     * @author Ethan Hunt
     */
    protected void setPlayerHands() {

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < numberOfPlayers; j++) {
                playerHands[j][i] = drawFromDeck();
                playerHands[j][i].setCardFace(CardFace.DOWN);
            }
        }

        for (int i = 0; i < numberOfPlayers; i++) {
            int num1 = rand.nextInt(6);
            int num2 = rand.nextInt(6);
            while (num1 == num2) {
                num2 = rand.nextInt(6);
            }

            playerHands[i][num1].setCardFace(CardFace.UP);
            playerHands[i][num2].setCardFace(CardFace.UP);
        }

        // Card added to discard manually instead of through discard() to avoid incrementing numberOfPlayedTurns
        // since flipping a card over onto the discard pile at the beginning of the game is not the end of a turn
        discard.add(drawFromDeck());
    }


    /** Checks if a player has all cards up. Returns false if they do not, and true if they do.*/
    protected boolean checkAllUp() {
        int allCardsUp = 0;
        for (int i = 0; i < numberOfPlayers; i++) {
            for (int j = 0; j < 6; j++) {
                if(getPlayerHand(i)[j].getCardFace() == CardFace.DOWN) {
                    continue;
                }
                else {
                    allCardsUp++;
                }
            }
            if (allCardsUp == 6) {
                return true;
            }
            else {
                allCardsUp = 0;
            }
        }
        return false;
    }

    /**
     * Only intended for testing
     *
     * @return the Controller's deck
     * @author Nathan Flanagan
     */
    protected ArrayList<GolfCard> getDeck() {
        return deck;
    }

    /**
     * Increments the holes and resets the number of played turns.
     */
    protected void incHole() {
        numberOfPlayedHoles++;
        if (numberOfPlayers > 4) {
            createCardDeck(deck, 2);
        }
        else {
            createCardDeck(deck, 1);
        }
        discard.clear();
        setPlayerHands();
        numberOfPlayedTurns = 0;
    }
}