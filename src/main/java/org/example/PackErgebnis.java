package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Ergebnis einer Packstrategie: welche Gegenstände gewählt wurden
 * und welche Summen daraus resultieren.
 */
public class PackErgebnis {

    protected final List<Gegenstand> gegenstaende;
    protected final int gesamtGewicht;

    public PackErgebnis(List<Gegenstand> gegenstaende, int gesamtGewicht) {
        if (gegenstaende == null) {
            this.gegenstaende = List.of();
        } else {
            this.gegenstaende = Collections.unmodifiableList(new ArrayList<>(gegenstaende));
        }
        this.gesamtGewicht = gesamtGewicht;
    }

    public List<Gegenstand> getGegenstaende() {
        return gegenstaende;
    }

    public int getGesamtGewicht() {
        return gesamtGewicht;
    }
}
