package de.craplezz.doppelkopfnfc.game;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Party {
    RE("Re"),
    CONTRA("Kontra");

    private final String name;

    public String getName() {
        return name;
    }

}