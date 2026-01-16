package org.example;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {

        Gegenstand g1 = new Gegenstand(500, "Buch");
        Gegenstand g2 = new Gegenstand(300, "Flasche");
        Gegenstand g3 = new Gegenstand(500, "Laptop");

        System.out.println("g1 equals g2: " + g1.equals(g2));
        System.out.println("g1 equals g3: " + g1.equals(g3));

        Rucksack rucksack = new Rucksack(1000);

        System.out.println("Packe g1: " + rucksack.packe(g1))   ;
        System.out.println("Packe g2: " + rucksack.packe(g2));
        System.out.println("Aktuelles Gewicht: " + rucksack.getAktuellesGewicht());

        System.out.println("Packe g3: " + rucksack.packe(g3));

        rucksack.leeren();
        System.out.println("Nach dem Leeren: " + rucksack.getAktuellesGewicht());

        // Smarter Rucksack (packt mehrere auf einmal) – Vergleich von Strategien
        List<Gegenstand> kandidaten = List.of(
                new Gegenstand(6, "A"),
                new Gegenstand(5, "B"),
                new Gegenstand(5, "C")
        );

        Rucksack smart = new Rucksack(10);
        PackErgebnis greedy = smart.packeAlle(kandidaten, new GierigePackStrategie());
        System.out.println("Greedy Gesamtgewicht: " + greedy.getGesamtGewicht());

        PackErgebnis optimal = smart.packeAlle(kandidaten, new OptimalPackStrategie());
        System.out.println("Optimal Gesamtgewicht: " + optimal.getGesamtGewicht());
    }
}
