package de.craplezz.doppelkopfnfc.game;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Overload
 * @version 1.0
 */
@RequiredArgsConstructor
public class Player {

    @Getter
    private final String name;

    @Getter
    private final List<Card> wonCards = new ArrayList<>();

    @Getter
    @Setter
    private Party party;

    public void winCards(Collection<Card> cards) {
        wonCards.addAll(cards);
    }

    public int getPoints() {
        int points = 0;
        for (Card card : wonCards) {
            points += card.getType().getPoints();
        }
        return points;
    }

}
