package csc439teamplatypus.cardgame.golfgame;

import csc439teamplatypus.cardgame.Card;
import csc439teamplatypus.cardgame.CardNumber;
import csc439teamplatypus.cardgame.CardSuit;

import java.util.ArrayList;
import java.util.Random;

public class Controller {

    private ArrayList<Card> deck;
    private ArrayList<Card> discard;
    private Card[][] playerHands;
    private int numberOfPlayers;
    private final View view;
    private int numberOfPlayedTurns;

    protected Controller (View view) {

        this.view = view;
    }

    /** Adds the cards in numberOfDecks standard decks to deck
     *
     * @author Nathan Flanagan
     * @param deck The deck the Cards should be added to
     * @param numberOfDecks The number of standard decks that should be added to deck
     */

    private void createCardDeck(ArrayList<Card> deck, int numberOfDecks) {

        for (int i = numberOfDecks; i > 0; i--) {

            deck.add(new Card(CardSuit.CLUB, CardNumber.TWO));
            deck.add(new Card(CardSuit.CLUB, CardNumber.THREE));
            deck.add(new Card(CardSuit.CLUB, CardNumber.FOUR));
            deck.add(new Card(CardSuit.CLUB, CardNumber.FIVE));
            deck.add(new Card(CardSuit.CLUB, CardNumber.SIX));
            deck.add(new Card(CardSuit.CLUB, CardNumber.SEVEN));
            deck.add(new Card(CardSuit.CLUB, CardNumber.EIGHT));
            deck.add(new Card(CardSuit.CLUB, CardNumber.NINE));
            deck.add(new Card(CardSuit.CLUB, CardNumber.TEN));
            deck.add(new Card(CardSuit.CLUB, CardNumber.JACK));
            deck.add(new Card(CardSuit.CLUB, CardNumber.QUEEN));
            deck.add(new Card(CardSuit.CLUB, CardNumber.KING));
            deck.add(new Card(CardSuit.CLUB, CardNumber.ACE));

            deck.add(new Card(CardSuit.SPADE, CardNumber.TWO));
            deck.add(new Card(CardSuit.SPADE, CardNumber.THREE));
            deck.add(new Card(CardSuit.SPADE, CardNumber.FOUR));
            deck.add(new Card(CardSuit.SPADE, CardNumber.FIVE));
            deck.add(new Card(CardSuit.SPADE, CardNumber.SIX));
            deck.add(new Card(CardSuit.SPADE, CardNumber.SEVEN));
            deck.add(new Card(CardSuit.SPADE, CardNumber.EIGHT));
            deck.add(new Card(CardSuit.SPADE, CardNumber.NINE));
            deck.add(new Card(CardSuit.SPADE, CardNumber.TEN));
            deck.add(new Card(CardSuit.SPADE, CardNumber.JACK));
            deck.add(new Card(CardSuit.SPADE, CardNumber.QUEEN));
            deck.add(new Card(CardSuit.SPADE, CardNumber.KING));
            deck.add(new Card(CardSuit.SPADE, CardNumber.ACE));

            deck.add(new Card(CardSuit.DIAMOND, CardNumber.TWO));
            deck.add(new Card(CardSuit.DIAMOND, CardNumber.THREE));
            deck.add(new Card(CardSuit.DIAMOND, CardNumber.FOUR));
            deck.add(new Card(CardSuit.DIAMOND, CardNumber.FIVE));
            deck.add(new Card(CardSuit.DIAMOND, CardNumber.SIX));
            deck.add(new Card(CardSuit.DIAMOND, CardNumber.SEVEN));
            deck.add(new Card(CardSuit.DIAMOND, CardNumber.EIGHT));
            deck.add(new Card(CardSuit.DIAMOND, CardNumber.NINE));
            deck.add(new Card(CardSuit.DIAMOND, CardNumber.TEN));
            deck.add(new Card(CardSuit.DIAMOND, CardNumber.JACK));
            deck.add(new Card(CardSuit.DIAMOND, CardNumber.QUEEN));
            deck.add(new Card(CardSuit.DIAMOND, CardNumber.KING));
            deck.add(new Card(CardSuit.DIAMOND, CardNumber.ACE));

            deck.add(new Card(CardSuit.HEART, CardNumber.TWO));
            deck.add(new Card(CardSuit.HEART, CardNumber.THREE));
            deck.add(new Card(CardSuit.HEART, CardNumber.FOUR));
            deck.add(new Card(CardSuit.HEART, CardNumber.FIVE));
            deck.add(new Card(CardSuit.HEART, CardNumber.SIX));
            deck.add(new Card(CardSuit.HEART, CardNumber.SEVEN));
            deck.add(new Card(CardSuit.HEART, CardNumber.EIGHT));
            deck.add(new Card(CardSuit.HEART, CardNumber.NINE));
            deck.add(new Card(CardSuit.HEART, CardNumber.TEN));
            deck.add(new Card(CardSuit.HEART, CardNumber.JACK));
            deck.add(new Card(CardSuit.HEART, CardNumber.QUEEN));
            deck.add(new Card(CardSuit.HEART, CardNumber.KING));
            deck.add(new Card(CardSuit.HEART, CardNumber.ACE));
        }


    }

    /** Adds a Card to the top of the discard pile
     *
     * @author Nathan Flanagan
     * @param cardToDiscard The Card to be placed on top of the discard pile
     */

    protected void discard(Card cardToDiscard) {

        discard.add(cardToDiscard);
    }

    protected void drawDiscard(int playerNumber, int cardToReplace) {

        Card temp = discard.remove(discard.size() - 1);
        discard.add(playerHands[playerNumber][cardToReplace]);
        playerHands[playerNumber][cardToReplace] = temp;
    }

    /** Returns a Card off the deck
     *
     * @author Nathan Flanagan
     * @return A random card from the deck
     */

    protected Card drawFromPile() {

        Random rand = new Random();
        return deck.remove(rand.nextInt(deck.size()));
    }

    protected int getNumberOfPlayedTurns() {

        return getNumberOfPlayers();
    }

    protected int getNumberOfPlayers() {

        return numberOfPlayers;
    }

    protected Card[] getPlayerHand(int playerNumber) {

        return playerHands[playerNumber];
    }


    /** Accepts the number of players (must be between 2 and 17, inclusively) and instantiates deck, discard,
     * playerHands, numberOfPlayers, and the numberOfPlayedTurns
     *
     * @author Nathan Flanagan
     * @param numberOfPlayers The number of people playing Golf. Must be inclusively between 2 and 17
     */

    protected void setNumberOfPlayers(int numberOfPlayers) {

        if (numberOfPlayers < 2 || numberOfPlayers > 17)
            throw new IllegalArgumentException("The number of players must be between 2 and 17 (inclusively");

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
     * @return the Controllers deck
     */

    protected ArrayList<Card> getDeck() {
        return deck;
    }
}
