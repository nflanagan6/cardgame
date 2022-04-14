package csc439teamplatypus.cardgame.golfgame;

import csc439teamplatypus.cardgame.*;
import java.util.ArrayList;
import java.util.Random;

public class Controller {

    private ArrayList<Card> deck;
    private ArrayList<Card> discard;
    private Card[][] playerHands;
    private int numberOfPlayers;
    private final View view;
    private int numberOfPlayedTurns;
    Random rand = new Random();

    protected Controller (View view) {

        this.view = view;
    }

    /** Returns whether there are Cards left to draw from in the deck
     *
     * @author Nathan Flanagan
     * @return Whether the size of the deck is > 0
     */

    protected boolean cardsRemaining() {

        return deck.size() > 0;
    }

    /** Adds the cards in numberOfDecks standard decks to deck
     *
     * @author Nathan Flanagan
     * @param deck The deck the Cards should be added to
     * @param numberOfDecks The number of standard decks that should be added to deck
     */

    private void createCardDeck(ArrayList<Card> deck, int numberOfDecks) {

        for (int i = numberOfDecks; i > 0; i--) {

            deck.add(new Card(CardSuit.CLUB, CardNumber.TWO, CardFace.DOWN));
            deck.add(new Card(CardSuit.CLUB, CardNumber.THREE, CardFace.DOWN));
            deck.add(new Card(CardSuit.CLUB, CardNumber.FOUR, CardFace.DOWN));
            deck.add(new Card(CardSuit.CLUB, CardNumber.FIVE, CardFace.DOWN));
            deck.add(new Card(CardSuit.CLUB, CardNumber.SIX, CardFace.DOWN));
            deck.add(new Card(CardSuit.CLUB, CardNumber.SEVEN, CardFace.DOWN));
            deck.add(new Card(CardSuit.CLUB, CardNumber.EIGHT, CardFace.DOWN));
            deck.add(new Card(CardSuit.CLUB, CardNumber.NINE, CardFace.DOWN));
            deck.add(new Card(CardSuit.CLUB, CardNumber.TEN, CardFace.DOWN));
            deck.add(new Card(CardSuit.CLUB, CardNumber.JACK, CardFace.DOWN));
            deck.add(new Card(CardSuit.CLUB, CardNumber.QUEEN, CardFace.DOWN));
            deck.add(new Card(CardSuit.CLUB, CardNumber.KING, CardFace.DOWN));
            deck.add(new Card(CardSuit.CLUB, CardNumber.ACE, CardFace.DOWN));

            deck.add(new Card(CardSuit.SPADE, CardNumber.TWO, CardFace.DOWN));
            deck.add(new Card(CardSuit.SPADE, CardNumber.THREE, CardFace.DOWN));
            deck.add(new Card(CardSuit.SPADE, CardNumber.FOUR, CardFace.DOWN));
            deck.add(new Card(CardSuit.SPADE, CardNumber.FIVE, CardFace.DOWN));
            deck.add(new Card(CardSuit.SPADE, CardNumber.SIX, CardFace.DOWN));
            deck.add(new Card(CardSuit.SPADE, CardNumber.SEVEN, CardFace.DOWN));
            deck.add(new Card(CardSuit.SPADE, CardNumber.EIGHT, CardFace.DOWN));
            deck.add(new Card(CardSuit.SPADE, CardNumber.NINE, CardFace.DOWN));
            deck.add(new Card(CardSuit.SPADE, CardNumber.TEN, CardFace.DOWN));
            deck.add(new Card(CardSuit.SPADE, CardNumber.JACK, CardFace.DOWN));
            deck.add(new Card(CardSuit.SPADE, CardNumber.QUEEN, CardFace.DOWN));
            deck.add(new Card(CardSuit.SPADE, CardNumber.KING, CardFace.DOWN));
            deck.add(new Card(CardSuit.SPADE, CardNumber.ACE, CardFace.DOWN));

            deck.add(new Card(CardSuit.DIAMOND, CardNumber.TWO, CardFace.DOWN));
            deck.add(new Card(CardSuit.DIAMOND, CardNumber.THREE, CardFace.DOWN));
            deck.add(new Card(CardSuit.DIAMOND, CardNumber.FOUR, CardFace.DOWN));
            deck.add(new Card(CardSuit.DIAMOND, CardNumber.FIVE, CardFace.DOWN));
            deck.add(new Card(CardSuit.DIAMOND, CardNumber.SIX, CardFace.DOWN));
            deck.add(new Card(CardSuit.DIAMOND, CardNumber.SEVEN, CardFace.DOWN));
            deck.add(new Card(CardSuit.DIAMOND, CardNumber.EIGHT, CardFace.DOWN));
            deck.add(new Card(CardSuit.DIAMOND, CardNumber.NINE, CardFace.DOWN));
            deck.add(new Card(CardSuit.DIAMOND, CardNumber.TEN, CardFace.DOWN));
            deck.add(new Card(CardSuit.DIAMOND, CardNumber.JACK, CardFace.DOWN));
            deck.add(new Card(CardSuit.DIAMOND, CardNumber.QUEEN, CardFace.DOWN));
            deck.add(new Card(CardSuit.DIAMOND, CardNumber.KING, CardFace.DOWN));
            deck.add(new Card(CardSuit.DIAMOND, CardNumber.ACE, CardFace.DOWN));

            deck.add(new Card(CardSuit.HEART, CardNumber.TWO, CardFace.DOWN));
            deck.add(new Card(CardSuit.HEART, CardNumber.THREE, CardFace.DOWN));
            deck.add(new Card(CardSuit.HEART, CardNumber.FOUR, CardFace.DOWN));
            deck.add(new Card(CardSuit.HEART, CardNumber.FIVE, CardFace.DOWN));
            deck.add(new Card(CardSuit.HEART, CardNumber.SIX, CardFace.DOWN));
            deck.add(new Card(CardSuit.HEART, CardNumber.SEVEN, CardFace.DOWN));
            deck.add(new Card(CardSuit.HEART, CardNumber.EIGHT, CardFace.DOWN));
            deck.add(new Card(CardSuit.HEART, CardNumber.NINE, CardFace.DOWN));
            deck.add(new Card(CardSuit.HEART, CardNumber.TEN, CardFace.DOWN));
            deck.add(new Card(CardSuit.HEART, CardNumber.JACK, CardFace.DOWN));
            deck.add(new Card(CardSuit.HEART, CardNumber.QUEEN, CardFace.DOWN));
            deck.add(new Card(CardSuit.HEART, CardNumber.KING, CardFace.DOWN));
            deck.add(new Card(CardSuit.HEART, CardNumber.ACE, CardFace.DOWN));
        }


    }

    /** Adds a Card to the top of the discard pile and increments numberOfPlayedTurns by 1. Ensures the card
     * added to the
     *
     * @author Nathan Flanagan
     * @param cardToDiscard The Card to be placed on top of the discard pile
     */

    protected void discard(Card cardToDiscard) {

        cardToDiscard.setCardFace(CardFace.UP);
        discard.add(cardToDiscard);
        numberOfPlayedTurns++;
    }

    /** Replaces the Card at playerHands[playerNumber][cardToReplace] with the Card off the top of the
     * discard pile. Increments numberOfPlayedTurns by 1.
     * @param playerNumber The current player
     * @param cardToReplace The index of the card the current player wishes to replace
     */

    protected void drawDiscard(int playerNumber, int cardToReplace) {

        playerHands[playerNumber][cardToReplace].setCardFace(CardFace.UP);
        Card temp = discard.remove(discard.size() - 1);
        discard.add(playerHands[playerNumber][cardToReplace]);
        playerHands[playerNumber][cardToReplace] = temp;
        numberOfPlayedTurns++;
    }

    /** Returns a Card face-up off the deck
     *
     * @author Nathan Flanagan
     * @return A random card from the deck
     */

    protected Card drawFromPile() {
        Card cardToReturn = deck.remove(rand.nextInt(deck.size()));
        cardToReturn.setCardFace(CardFace.UP);
        return cardToReturn;
    }

    /** Flips the Card at playerHands[playerNumber][cardToFlip] if it is face-down. Throws an
     * IllegalArgumentException if already face-up
     *
     * @author Nathan Flanagan
     * @param playerNumber The player whose Card should be flipped
     * @param cardToFlip The index of the Card that should be turned face-up
     */

    protected void flipCard(int playerNumber, int cardToFlip) {

        if (playerHands[playerNumber][cardToFlip].getCardFace() != CardFace.DOWN)
            throw new IllegalArgumentException("You can only flip over a card that is face-down");

        else {

            playerHands[playerNumber][cardToFlip].setCardFace(CardFace.UP);
            numberOfPlayedTurns++;
        }
    }

    /** Returns the number of turns that have been completed so far in the game
     *
     * @author Nathan Flanagan
     * @return The number of completed turns
     */

    protected int getNumberOfPlayedTurns() {

        return numberOfPlayedTurns;
    }

    /** Returns the number of players in the game
     *
     * @author Nathan Flanagan
     * @return The number of players in the game
     */

    protected int getNumberOfPlayers() {

        return numberOfPlayers;
    }

    /** Returns a player's current hand
     *
     * @author Nathan Flanagan
     * @param playerNumber The player whose hand should be returned
     * @return The player's hand
     */

    protected Card[] getPlayerHand(int playerNumber) {

        if (playerNumber >= playerHands.length)
            throw new IndexOutOfBoundsException();

        return playerHands[playerNumber];
    }

    /** Accepts the number of players (must be between 2 and 7, inclusively) and instantiates deck, discard,
     * playerHands, numberOfPlayers, and the numberOfPlayedTurns
     *
     * @author Nathan Flanagan
     * @param numberOfPlayers The number of people playing Golf. Must be inclusively between 2 and 7
     */

    protected void setNumberOfPlayers(int numberOfPlayers) {

        if (numberOfPlayers < 2 || numberOfPlayers > 7)
            throw new IllegalArgumentException("The number of players must be between 2 and 7 (inclusively");

        else {

            this.numberOfPlayers = numberOfPlayers;
            playerHands = new Card[numberOfPlayers][6];
            deck = new ArrayList<>();
            if (numberOfPlayers > 4)
                createCardDeck(deck, 2);
            else
                createCardDeck(deck, 1);
            discard = new ArrayList<>();
            numberOfPlayedTurns = 0;
        }
    }

    /** Returns the last Card placed on the discard pile without moving it off the pile
     *
     * @author Nathan Flanagan
     * @return The top Card on the discard pile
     */

    protected Card viewTopOfDiscardPile() {

        return discard.get(discard.size() - 1);
    }

    /** Only intended for testing
     *
     * @author Nathan Flanagan
     * @return the Controller's deck
     */

    protected ArrayList<Card> getDeck() {
        return deck;
    }

    /** Sets each players hand by dealing them cards one at a time until they have a full hand.
     * Then it takes two of those cards and makes them face up
     * @author Ethan Hunt
     */
    protected void setPlayerHands() {

        for (int i = 0; i < 6; i ++) {
            for (int j = 0; j < numberOfPlayers; j++) {
                playerHands[j][i] = drawFromPile();
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
    }
}