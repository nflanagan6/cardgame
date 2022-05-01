package csc439teamplatypus.cardgame.golfgame;

import csc439teamplatypus.cardgame.Card;
import csc439teamplatypus.cardgame.*;

public abstract class View {

    private final Controller controller;

    /** Creates a new View() with a default-constructed Controller to play golf
     *
     */
    View() {
        this.controller = new Controller(this);
    }

    /** Moves playerNumber's Card at cardIndex to the discard pile and moves to the next turn.
     * @param playerNumber The player whose Card should be discarded
     * @param cardIndex The index of the Card to be discarded
     */
    protected void discardHeldCard(int playerNumber, int cardIndex) {
        controller.discard((GolfCard) controller.getPlayerHand(playerNumber)[cardIndex]);
    }

    /** Moves cardToDiscard to the discard pile and moves to the next turn
     * @param cardToDiscard The card that should be discarded
     */
    protected void discardUnheldCard(Card cardToDiscard) {
        controller.discard((GolfCard) cardToDiscard);
    }

    /** Swaps playerNumber's Card at cardIndex with the top Card on the discard pile and moves to the next turn
     * @param playerNumber The player whose Card should be discarded
     * @param cardToReplace The Card that should be swapped with the top Card on the discard pile
     */
    protected void drawDiscard(int playerNumber, int cardToReplace) {
        controller.drawDiscard(playerNumber, cardToReplace);
    }

    /** Draws a Card from the deck and turns it face-up
     * @return a Card from the deck
     */
    protected Card drawCardFromDeck() {
        return controller.drawFromDeck();
    }

    /** Checks whether a player has a face-down card in their hand
     * @param playerNumber The player whose hand should be check
     * @return true if playerNumber has a face-down card, false if not
     */
    protected boolean hasFaceDownCard(int playerNumber) {
        for (Card card : controller.getPlayerHand(playerNumber))
            if (card.getCardFace() == CardFace.DOWN)
                return true;

        return false;
    }

    /** Sets the number of players
     * @param numberOfPlayers The number of people who will be playing the game
     */
    protected void setNumberOfPlayers(int numberOfPlayers) {
        controller.setNumberOfPlayers(numberOfPlayers);
    }

    /** Returns the top Card on the discard pile without removing it
     * @return the top Card on the discard pile
     */
    protected Card viewTopOfDiscardPile() {
        return controller.viewTopOfDiscardPile();
    }

    /** Returns the player whose turn it is
     * @return The current players index
     */
    protected int getCurrentPlayerNumber() {
        return controller.getNumberOfPlayedTurns() % controller.getNumberOfPlayers();
    }

    /** Returns the Cards in the current players hand
     * @return the player's current hand as a Card[]
     */
    protected Card[] getCurrentPlayerHand() {
        return controller.getPlayerHand(getCurrentPlayerNumber());
    }

    /** Returns each player's score as an int[]
     *
     * @return An int[] containing each player's score
     */
    protected int[] getPlayerScores() {
        return controller.getPlayerScores();
    }

    /** Returns the total number of holes that will be played
     *
     * @return the total number of holes in the game
     */
    protected int getNumberOfHoles() {
        return controller.getNumberOfHoles();
    }

    /** Returns the total number of holes that have already been played
     *
     * @return the number of played holes
     */
    protected int getNumberOfPlayedHoles() {
        return controller.getNumberOfPlayedHoles();
    }

    /** Checks whether there are Cards left in the deck
     * @return true if there are Cards in the deck and false otherwise
     */
    protected boolean cardsRemaining() {
        return controller.cardsRemaining();
    }

    /** Flips playerNumber's Card at index cardToFlip if it is face-down
     * @param playerNumber The player whose Card should be flipped
     * @param cardToFlip The Card that should be flipped
     */
    protected void flipCard(int playerNumber, int cardToFlip) {
        controller.flipCard(playerNumber, cardToFlip);
    }

    /** Deals all players 6 Cards, 2 of which are flipped face-up, and flips over one Card from the deck onto
     * the discard pile
     *
     */
    protected void setPlayerHand() {
        controller.setPlayerHands();
    }

    /** Checks if any player has all of their cards face up*/
    protected boolean checkCards() {
        return controller.checkAllUp();
    }

    /** Updates player scores at the end of each hole */
    protected void updateScores() {
        controller.updatePlayerScores();
    }

    protected void setNumberOfHoles(int numberOfHoles) {
        controller.setNumberOfHoles(numberOfHoles);
    }

    /**
     * Updates the next hole
     */
    protected void nextHole() {
        controller.incHole();
    }

    /**
     * Uses controllers logic to get the end of hole score.
     * @return endHoleScore
     */
    protected int[] endOfHoleScores() {
        return controller.getEndOfHoleScores();
    }
}