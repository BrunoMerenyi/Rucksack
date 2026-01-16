package org.example;

import java.util.List;

public interface PackStrategie {

    PackErgebnis packe(List<Gegenstand> kandidaten, int maxGewicht);
}
