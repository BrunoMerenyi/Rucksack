package org.example;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SmartRucksackTest {

    @Test
    void calculatesOptimalWeightForExample() {
        SmartRucksack b = new SmartRucksack(25);

        List<Gegenstand> items = List.of(
                new Gegenstand(9, "A"),
                new Gegenstand(12, "B"),
                new Gegenstand(7, "C")
        );

        b.packAll(items);

        assertEquals(25, b.getAktuellesGewicht());
    }
}