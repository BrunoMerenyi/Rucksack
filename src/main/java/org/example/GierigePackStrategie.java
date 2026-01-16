package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GierigePackStrategie implements PackStrategie {

    @Override
    public PackErgebnis packe(List<Gegenstand> kandidaten, int maxGewicht) {
        if (kandidaten == null || kandidaten.isEmpty() || maxGewicht <= 0) {
            return new PackErgebnis(List.of(), 0);
        }

        List<Gegenstand> sortiert = new ArrayList<>(kandidaten);
        sortiert.sort(Comparator.comparingInt(Gegenstand::getGewicht).reversed());

        List<Gegenstand> chosen = new ArrayList<>();
        int sum = 0;
        for (Gegenstand g : sortiert) {
            if (g == null) {
                continue;
            }
            if (sum + g.getGewicht() <= maxGewicht) {
                chosen.add(g);
                sum += g.getGewicht();
            }
        }

        return new PackErgebnis(chosen, sum);
    }
}

