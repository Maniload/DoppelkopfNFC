package de.craplezz.doppelkopfnfc.io;

import de.craplezz.doppelkopfnfc.game.Card;

import java.io.IOException;

/**
 * @author Overload
 * @version 1.0
 */
public interface CardInputStream {

    Card read() throws IOException;

}
