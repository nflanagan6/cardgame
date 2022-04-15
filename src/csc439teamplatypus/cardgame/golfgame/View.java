package csc439teamplatypus.cardgame.golfgame;

import csc439teamplatypus.cardgame.Card;
import csc439teamplatypus.cardgame.*;

public abstract class View {

    private final Controller controller;

    /**
     * Initializes view for controller
     */
    View() {

        this.controller = new Controller(this);
    }

    /**uses player number at a given card index to discard the players hand at that index
     * @param playerNumber
     * @param cardIndex
     */
    protected void discardHeldCard(int playerNumber, int cardIndex) {

        controller.discard(controller.getPlayerHand(playerNumber)[cardIndex]);
    }

    /**handles case of unheld card being discarded
     * @param cardToDiscard
     */
    protected void discardUnheldCard(Card cardToDiscard) {

        controller.discard(cardToDiscard);
    }

    /**takes from the discard pile with the given player number and the card to replace
     * @param playerNumber
     * @param cardToReplace
     */
    protected void drawDiscard(int playerNumber, int cardToReplace) {

        controller.drawDiscard(playerNumber, cardToReplace);
    }

    /** Takes from draw pile a card and passes it into controller
     * @return Card from drawpile
     */
    protected Card drawFromPile() {

        return controller.drawFromPile();
    }

    /** checks the players hand if they have a card face down
     * @param playerNumber
     * @return true if card is face down otherwise false
     */
    protected boolean hasFaceDownCard(int playerNumber) {

        for (Card card : controller.getPlayerHand(playerNumber))
            if (card.getCardFace() == CardFace.DOWN)
                return true;

        return false;
    }

    /**initializes the number of players using controller and numOfPlayers
     * @param numberOfPlayers
     */
    protected void setNumberOfPlayers(int numberOfPlayers) {

        controller.setNumberOfPlayers(numberOfPlayers);
    }

    /** initialize method to show top of discard pile
     * @return top of discard pile
     */
    protected Card viewTopOfDiscardPile() {

        return controller.viewTopOfDiscardPile();
    }

    /** gives us player number from the remainder of turns, number of players
     * @return player number
     */
    protected int getCurrentPlayerNumber() {

        return controller.getNumberOfPlayedTurns() % controller.getNumberOfPlayers();
    }

    /** gives us the current players hand
     * @return playersCurrentHand
     */
    protected Card[] getCurrentPlayerHand() {

        return controller.getPlayerHand(getCurrentPlayerNumber());
    }

    /** checks if cards are remaining
     * @return true if there's cards remaining false otherwise
     */
    protected boolean cardsRemaining() {

        return controller.cardsRemaining();
    }

    /** flips a card based on the cardToFlip being passed
     * @param playerNumber
     * @param cardToFlip
     */
    protected void flipCard(int playerNumber, int cardToFlip) {

        controller.flipCard(playerNumber, cardToFlip);
    }

    /**initializes player hand based on controller
     *
     */
    protected void setPlayerHand() {
        controller.setPlayerHands();
    }
}
