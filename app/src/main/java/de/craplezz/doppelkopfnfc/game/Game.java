package de.craplezz.doppelkopfnfc.game;

import de.craplezz.doppelkopfnfc.io.CardInputStream;
import de.craplezz.doppelkopfnfc.io.ConsoleHandler;
import de.craplezz.doppelkopfnfc.io.DummyCardInputStream;
import lombok.Getter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Overload
 * @version 1.0
 */
@Getter
public class Game {

    private final ConsoleHandler consoleHandler = new ConsoleHandler();
    private final CardInputStream cardInputStream;

    private final List<Player> players;
    private final Deck deck = new Deck();
    private final List<Stack> finishedStacks = new ArrayList<>();

    public Game(List<Player> players) {
        this.players = players;

        this.cardInputStream = new DummyCardInputStream(this, consoleHandler);
    }

    public void start() throws IllegalArgumentException {
        consoleHandler.info("-----------------------------------------------------");
        consoleHandler.info("|                                                   |");
        consoleHandler.info("|                    Doppelkopf                     |");
        consoleHandler.info("|                                                   |");
        consoleHandler.info("-----------------------------------------------------");

        nextStack(players.get(0));
    }

    public void nextStack(Player player) {
        Stack stack = new Stack();

        // First let the first player play
        try {
            letPlay(stack, player);

            int firstPlayerIndex = players.indexOf(player);

            // Then let the other players respond
            for (int i = 1; i < 4; i++) {
                int playerIndex = firstPlayerIndex + i;
                if (playerIndex >= players.size()) {
                    playerIndex -= players.size();
                }

                letPlay(stack, players.get(playerIndex));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Player winner = stack.clear();

        for (Player playingPlayer : players) {
            consoleHandler.info(playingPlayer.getName() + ": " + playingPlayer.getPoints() + " points.");
        }

        consoleHandler.info();

        finishedStacks.add(stack);

        if (finishedStacks.size() < 10) {
            nextStack(winner);
        }
        else {
            finish();
        }
    }

    private void letPlay(Stack stack, Player player) throws IOException {
        consoleHandler.info(player.getName() + ", it's your turn!");

        Card card = cardInputStream.read();

        // Handle parties
        if (card.getType() == Card.Type.QUEEN_OF_CLUBS) {
            // Player is already Re, he's playing a blind wedding
            if (player.getParty() == Party.RE) {
                for (Player otherPlayer : players) {
                    if (otherPlayer.getParty() != Party.RE) {
                        otherPlayer.setParty(Party.CONTRA);
                    }
                }
            } else {
                player.setParty(Party.RE);
            }

            // Both Re players are known, all teams are known now
            if (getPlayers(Party.RE).size() == 2) {
                for (Player otherPlayer : players) {
                    if (otherPlayer.getParty() != Party.RE) {
                        otherPlayer.setParty(Party.CONTRA);
                    }
                }
            }
        }

        deck.getCards().remove(card);
        stack.add(player, card);
        consoleHandler.info(player.getName() + " played " + card.getType().toString());
    }

    public void finish() {
        Party winningParty = null;
        int winningPoints = -1;

        for (Party party : Party.values()) {
            int points = getPlayers(party).stream().mapToInt(Player::getPoints).sum();

            consoleHandler.info(party.getName() + ": " + points + " points.");

            if (winningParty == null || points > winningPoints) {
                winningParty = party;
            }
        }

        if (winningParty != null) {
            consoleHandler.info(winningParty.getName() + " won.");
        }
        else {
            consoleHandler.info("Nobody won? (What happened?)");
        }
    }

    public List<Player> getPlayers(Party party) {
        return players.stream().filter((player) -> player.getParty() == party).collect(Collectors.toList());
    }

}
