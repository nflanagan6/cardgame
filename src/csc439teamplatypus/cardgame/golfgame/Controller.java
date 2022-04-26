package csc439teamplatypus.cardgame.golfgame;

import csc439teamplatypus.cardgame.Card;
import csc439teamplatypus.cardgame.CardFace;
import csc439teamplatypus.cardgame.CardNumber;
import csc439teamplatypus.cardgame.CardSuit;

import java.util.ArrayList;
import java.util.Random;

public class Controller {

    private ArrayList<Card> deck;
    private ArrayList<Card> discard;
    private Card[][] playerHands;
    private int numberOfPlayers;
    private int numberOfHoles;
    private final View view;
    private int numberOfPlayedTurns;
    private int numberOfPlayedHoles;
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

    /**
     * Adds a Card to the top of the discard pile and increments numberOfPlayedTurns by 1. Ensures the card
     * added to the discard pile is face-up
     *
     * @param cardToDiscard The Card to be placed on top of the discard pile
     * @author Nathan Flanagan
     */

    protected void discard(Card cardToDiscard) {
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
        Card temp = discard.remove(discard.size() - 1);
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

    protected Card drawFromDeck() {
        Card cardToReturn = deck.remove(rand.nextInt(deck.size()));
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

    /**
     * Returns the number of turns that have been completed so far in the game
     *
     * @return The number of completed turns
     * @author Nathan Flanagan
     */

    protected int getNumberOfPlayedTurns() {
        return numberOfPlayedTurns;
    }

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

    protected Card[] getPlayerHand(int playerNumber) {
        if (playerNumber >= playerHands.length || playerNumber < 0) {
            throw new IndexOutOfBoundsException();
        }

        return playerHands[playerNumber];
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

    protected void setNumberOfHoles(int numberOfHoles) {
        if (numberOfHoles != 9 || numberOfHoles != 18) {
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


    /**
     * Only intended for testing
     *
     * @return the Controller's deck
     * @author Nathan Flanagan
     */

    protected ArrayList<Card> getDeck() {
        return deck;
    }
    /**
     * Takes the deck after the turn is over and shuffles it and then deals a new hand for the players
     * @return shuffle is the newly formed deck
     */
    protected  ArrayList<Card> cardShuffle() {
        discard.clear();
        int count=0;
        int deckCount=0;
        ArrayList<Card> shuffle= new ArrayList<>();
        if (numberOfPlayers > 4) {
            createCardDeck(shuffle, 2);
            deckCount = 104;
        }
        else{
            createCardDeck(shuffle, 1);
            deckCount=52;
        }
        for (int i=0;i<deckCount;i++){
            if(deckCount==52){
                count=i+rand.nextInt(52-i);
                Card swap= shuffle.get(count);
                shuffle.set(count, deck.get(i));
                shuffle.set(i, swap);
            }
            else {
                count=i+rand.nextInt(104-i);
                Card swap= shuffle.get(count);
                shuffle.set(count, shuffle.get(i));
                shuffle.set(i, swap);
            }
        }

        setPlayerHands();
        return shuffle;
    }
}