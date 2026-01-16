package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SmartRucksack extends Rucksack {

    public SmartRucksack(int maxWeight) {
        super(maxWeight);
    }

    public void packAll(List<Gegenstand> itemsToPack) {
        leeren();

        List<Gegenstand> sorted = new ArrayList<>(itemsToPack);
        sorted.sort(Comparator.comparingInt(Gegenstand::getGewicht).reversed());

        for (Gegenstand g : sorted) {
            if (getAktuellesGewicht() + g.getGewicht() <= maxGewicht) {
                inhalt.add(g);
            }
        }
    }
}