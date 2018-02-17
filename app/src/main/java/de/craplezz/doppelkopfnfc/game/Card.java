package de.craplezz.doppelkopfnfc.game;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

/**
 * @author Overload
 * @version 1.0
 */
@Getter
@RequiredArgsConstructor
public class Card {

    // Unique card so we can have two identical looking cards, that are different
    private final UUID uniqueId = UUID.randomUUID();

    private final Type type;

    @Override
    public String toString() {
        return String.valueOf(type);
    }

    /*
    Order is sorting order
     */
    @RequiredArgsConstructor
    public enum Type {

        KING_OF_HEARTS      (   4,  Color.HEARTS,   false,  12, 2   ),
        KING_OF_SPADES      (   4,  Color.SPADES,   false,  12, 3   ),
        KING_OF_CLUBS       (   4,  Color.CLUBS,    false,  12, 0   ),

        TEN_OF_SPADES       (   10, Color.SPADES,   false,  9,  3   ),
        TEN_OF_CLUBS        (   10, Color.CLUBS,    false,  9,  0   ),

        ACE_OF_HEARTS       (   11, Color.HEARTS,   false,  0,  2   ),
        ACE_OF_SPADES       (   11, Color.SPADES,   false,  0,  3   ),
        ACE_OF_CLUBS        (   11, Color.CLUBS,    false,  0,  0   ),

        KING_OF_DIAMONDS    (   4,  Color.DIAMOND,  true,   12, 1   ),
        TEN_OF_DIAMONDS     (   10, Color.DIAMOND,  true,   9,  1   ),
        ACE_OF_DIAMONDS     (   11, Color.DIAMOND,  true,   0,  1   ),

        JACK_OF_DIAMONDS    (   2,  Color.DIAMOND,  true,   10, 1   ),
        JACK_OF_HEARTS      (   2,  Color.HEARTS,   true,   10, 2   ),
        JACK_OF_SPADES      (   2,  Color.SPADES,   true,   10, 3   ),
        JACK_OF_CLUBS       (   2,  Color.CLUBS,    true,   10, 0   ),

        QUEEN_OF_DIAMONDS   (   3,  Color.DIAMOND,  true,   11, 1   ),
        QUEEN_OF_HEARTS     (   3,  Color.HEARTS,   true,   11, 2   ),
        QUEEN_OF_SPADES     (   3,  Color.SPADES,   true,   11, 3   ),
        QUEEN_OF_CLUBS      (   3,  Color.CLUBS,    true,   11, 0   ),

        TEN_OF_HEARTS       (   10, Color.HEARTS,   true,    9, 2   );

        private final int points;
        private final Color color;
        private final boolean trump;

        @Getter
        private final int x, y;

        public int getPoints() {
            return points;
        }

        public Color getColor() {
            return color;
        }

        public boolean isTrump() {
            return trump;
        }

    }

    public enum Color {
        DIAMOND, HEARTS, SPADES, CLUBS
    }

}
