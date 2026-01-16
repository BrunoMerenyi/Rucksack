package org.example;

import java.util.List;

/**
 * Strategie für das Packen mehrerer Gegenstände auf einmal (0/1-Auswahl).
 */
public interface PackStrategie {

    PackErgebnis packe(List<Gegenstand> kandidaten, int maxGewicht);
}

