package de.craplezz.doppelkopfnfc.game;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Overload
 * @version 1.0
 */
public class Stack {

    // Linked hash map because we need to know the order
    private final Map<Player, Card> cards = new LinkedHashMap<>(4);

    public List<Card> getCards() {
        return new ArrayList<>(cards.values());
    }

//    @Override
//    public boolean canAdd(Player player, Card card) {
//        Iterator<Card> iterator = cards.values().iterator();
//        final Card firstCard = iterator.hasNext() ? iterator.next() : null;
//
//        // Player can add anything he wants
//        if (firstCard == null) {
//            return true;
//        }
//
//        final Card.Type type = card.getType();
//
//        // All cases where the first card is not a trump
//        if (!firstCard.getType().isTrump()) {
//            // Player adds a non trump of the same color
//            if (!type.isTrump() && type.getColor() == firstCard.getType().getColor()) {
//                return true;
//            }
//
//            // Check if the player could add a non trump of the same color
//            for (Card availableCard : player.getCards()) {
//                // Player could add a non trump of the same color
//                if (!availableCard.getType().isTrump() && availableCard.getType().getColor() == firstCard.getType().getColor()) {
//                    return false;
//                }
//            }
//        }
//        else {
//            // Player adds a trump on a trump
//            if (type.isTrump()) {
//                return true;
//            }
//
//            // Check if the player could add a trump
//            for (Card availableCard : player.getCards()) {
//                // Player could add a trump
//                if (availableCard.getType().isTrump()) {
//                    return false;
//                }
//            }
//        }
//
//        // Player can add any other card
//        return true;
//    }

    public void add(Player player, Card card) {
        cards.put(player, card);
    }

    public Player clear() {
        final Card firstCard = cards.values().iterator().next();
        Map.Entry<Player, Card> winningEntry = null;

        for (Map.Entry<Player, Card> entry : cards.entrySet()) {
            Card.Type type = entry.getValue().getType();

            if (winningEntry == null) {
                winningEntry = entry;
                continue;
            }

            Card.Type winningType = winningEntry.getValue().getType();

            // Here we need to check if the players card beats the highest at the moment
            if (!firstCard.getType().isTrump()) {
                // Player beats non trump with higher non trump of the same color
                if (!type.isTrump() && type.getColor() == winningType.getColor() && type.ordinal() > winningType.ordinal()) {
                    winningEntry = entry;
                }
                // Player beats card with (higher) trump
                else if (type.isTrump() && type.ordinal() > winningType.ordinal()) {
                    winningEntry = entry;
                }
            }
            // Player beats trump with higher trump
            else if (type.ordinal() > winningType.ordinal()) {
                winningEntry = entry;
            }
        }

        // The winner should always be non null, but we check it anyway
        if (winningEntry != null) {
            winningEntry.getKey().winCards(cards.values());

            return winningEntry.getKey();
        }

        return null;
    }

}
