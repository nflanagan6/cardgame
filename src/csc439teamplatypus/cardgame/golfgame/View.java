package csc439teamplatypus.cardgame.golfgame;

import csc439teamplatypus.cardgame.Card;
import csc439teamplatypus.cardgame.*;

public abstract class View {

    private final Controller controller;

    View() {

        this.controller = new Controller(this);
    }

    protected void discardHeldCard(int playerNumber, int cardIndex) {

        controller.discard(controller.getPlayerHand(playerNumber)[cardIndex]);
    }
    protected void discardUnheldCard(Card cardToDiscard) {

        controller.discard(cardToDiscard);
    }
    protected void drawDiscard(int playerNumber, int cardToReplace) {

        controller.drawDiscard(playerNumber, cardToReplace);
    }
    protected Card drawFromPile() {

        return controller.drawFromPile();
    }

    protected boolean hasFaceDownCard(int playerNumber) {

        for (Card card : controller.getPlayerHand(playerNumber))
            if (card.getCardFace() == CardFace.DOWN)
                return true;

        return false;
    }
    protected void setNumberOfPlayers(int numberOfPlayers) {

        controller.setNumberOfPlayers(numberOfPlayers);
    }
    protected Card viewTopOfDiscardPile() {

        return controller.viewTopOfDiscardPile();
    }
    protected int getCurrentPlayerNumber() {

        return controller.getNumberOfPlayedTurns() % controller.getNumberOfPlayers();
    }
    protected Card[] getCurrentPlayerHand() {

        return controller.getPlayerHand(getCurrentPlayerNumber());
    }
    protected boolean cardsRemaining() {

        return controller.cardsRemaining();
    }

    protected void flipCard (int playerNumber, int cardToFlip) {

        controller.flipCard(playerNumber, cardToFlip);
    }

    protected void setPlayerHand() {
        controller.setPlayerHands();
    }
}
