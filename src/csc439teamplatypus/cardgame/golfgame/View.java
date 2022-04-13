package csc439teamplatypus.cardgame.golfgame;

import csc439teamplatypus.cardgame.Card;

public abstract class View {

    private final Controller controller;

    View() {

        this.controller = new Controller(this);
    }

    protected void discard(int playerNumber, int cardIndex) {

        getController().discard(getController().getPlayerHand(playerNumber)[cardIndex]);
    }
    protected abstract Card drawDiscard(Card cardToReplace);
    protected abstract Card drawFromPile();
    protected abstract void setNumberOfPlayers(int numberOfPlayers);
    protected abstract void viewTopOfDiscardPile();
    protected int getPlayerNumber() {

        return getController().getNumberOfPlayedTurns() % getController().getNumberOfPlayers();
    }
    protected Controller getController() {

        return this.controller;
    }

}
