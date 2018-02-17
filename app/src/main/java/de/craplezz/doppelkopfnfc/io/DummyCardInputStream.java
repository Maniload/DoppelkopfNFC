package de.craplezz.doppelkopfnfc.io;

import de.craplezz.doppelkopfnfc.game.Card;
import de.craplezz.doppelkopfnfc.game.Game;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Overload
 * @version 1.0
 */
@RequiredArgsConstructor
public class DummyCardInputStream implements CardInputStream {

    private final Game game;
    private final ConsoleHandler consoleHandler;

    @Override
    public Card read() throws IOException {
        // Output cards
        List<String> cardStrings = new ArrayList<>();
        for (int i = 0; i < game.getDeck().getCards().size(); i++) {
            Card card = game.getDeck().getCards().get(i);
            cardStrings.add("[" + i + "] " + card.toString());
        }
        consoleHandler.info(String.join(", ", cardStrings));
        consoleHandler.info("Please enter a valid card you want to play:");

        int choice = Integer.parseInt(consoleHandler.waitForInput());

        return game.getDeck().getCards().get(choice);
    }

}
