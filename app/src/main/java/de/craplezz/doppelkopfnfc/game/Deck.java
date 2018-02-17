package de.craplezz.doppelkopfnfc.game;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Overload
 * @version 1.0
 */
@Getter
@RequiredArgsConstructor
public class Deck {

    private final List<Card> cards = new ArrayList<Card>() {{

        for (Card.Type type : Card.Type.values()) {
            // Add two of each because we are playing Doppelkopf
            add(new Card(type));
            add(new Card(type));
        }

    }};

}
