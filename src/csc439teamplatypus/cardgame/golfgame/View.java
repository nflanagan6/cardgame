package csc439teamplatypus.cardgame.golfgame;

import csc439teamplatypus.cardgame.Card;

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
    protected abstract void setNumberOfPlayers(int numberOfPlayers);
    protected Card viewTopOfDiscardPile() {

        return controller.viewTopOfDiscardPile();
    }
    protected int getCurrentPlayerNumber() {

        return controller.getNumberOfPlayedTurns() % controller.getNumberOfPlayers();
    }
    protected Card[] getCurrentPlayerHand() {

        return controller.getPlayerHand(getCurrentPlayerNumber());
    }

}
