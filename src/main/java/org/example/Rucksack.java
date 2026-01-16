package org.example;

import java.util.ArrayList;
import java.util.List;

public class Rucksack {

    protected final int maxGewicht;
    protected final List<Gegenstand> inhalt;

    public Rucksack(int maxGewicht) {
        if (maxGewicht <= 0) {
            throw new IllegalArgumentException("Maximalgewicht muss positiv sein");
        }
        this.maxGewicht = maxGewicht;
        this.inhalt = new ArrayList<>();
    }

    public boolean packe(Gegenstand g) {
        if (g == null) {
            return false;
        }

        if (getAktuellesGewicht() + g.getGewicht() <= maxGewicht) {
            inhalt.add(g);
            return true;
        }
        return false;
    }

    public PackErgebnis packeAlle(List<Gegenstand> kandidaten, PackStrategie strategie) {
        if (strategie == null) {
            throw new IllegalArgumentException("Strategie darf nicht null sein");
        }

        PackErgebnis ergebnis = strategie.packe(kandidaten, this.maxGewicht);

        // Rucksack wird gemäss Ergebnis befüllt (ersetzt aktuellen Inhalt)
        this.inhalt.clear();
        for (Gegenstand g : ergebnis.getGegenstaende()) {
            // Defensive: garantiert nie über maxGewicht (falls Strategie fehlerhaft ist)
            if (g != null && getAktuellesGewicht() + g.getGewicht() <= maxGewicht) {
                this.inhalt.add(g);
            }
        }

        return new PackErgebnis(new ArrayList<>(this.inhalt), getAktuellesGewicht());
    }

    public void leeren() {
        inhalt.clear();
    }

    public int getAktuellesGewicht() {
        int summe = 0;
        for (Gegenstand g : inhalt) {
            summe += g.getGewicht();
        }
        return summe;
    }

    public int getMaxGewicht() {
        return maxGewicht;
    }
}
