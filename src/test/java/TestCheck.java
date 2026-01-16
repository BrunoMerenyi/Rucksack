import org.example.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestCheck {

    private static String fmt(PackErgebnis e) {
        if (e == null) {
            return "<null>";
        }
        return "gesamtGewicht=" + e.getGesamtGewicht() + ", anzahl=" + e.getGegenstaende().size() + ", items=" +
                e.getGegenstaende().stream()
                        .map(g -> (g.getName() != null ? g.getName() : "<no-name>") + "(" + g.getGewicht() + ")")
                        .toList();
    }

    @Test
    void simpleTest() {
        System.out.println("[simpleTest] start");
        assertTrue(true);
    }

    @Test
    void testMehrereGleicheGewichte() {
        System.out.println("[testMehrereGleicheGewichte] start");
        Rucksack r = new Rucksack(1000);
        Gegenstand a = new Gegenstand(200, "A");
        Gegenstand b = new Gegenstand(200, "B");

        assertTrue(r.packe(a));
        assertTrue(r.packe(b));

        System.out.println("[testMehrereGleicheGewichte] aktuellesGewicht=" + r.getAktuellesGewicht());
        assertEquals(400, r.getAktuellesGewicht());
    }

    @Test
    void testBerechneOptimalesGewicht() {
        SmartRucksack b = new SmartRucksack(25);

        List<Gegenstand> items = List.of(
                new Gegenstand(9, "A"),
                new Gegenstand(12, "B"),
                new Gegenstand(7, "C")
        );

        b.packAll(items);

        assertEquals(25, b.getAktuellesGewicht());
    }

    @Test
    void testGrenzfallMaxGewicht() {
        System.out.println("[testGrenzfallMaxGewicht] start");
        Rucksack r = new Rucksack(500);
        Gegenstand a = new Gegenstand(200);
        Gegenstand b = new Gegenstand(300);
        Gegenstand c = new Gegenstand(1);

        assertTrue(r.packe(a));
        assertTrue(r.packe(b));
        boolean thirdPacked = r.packe(c);
        System.out.println("[testGrenzfallMaxGewicht] packe(c)=" + thirdPacked + ", aktuellesGewicht=" + r.getAktuellesGewicht());
        assertFalse(thirdPacked);

        assertEquals(500, r.getAktuellesGewicht());
    }

    @Test
    void testLeerenUndWiederPacken() {
        System.out.println("[testLeerenUndWiederPacken] start");
        Rucksack r = new Rucksack(600);
        Gegenstand a = new Gegenstand(400);
        Gegenstand b = new Gegenstand(300);

        r.packe(a);
        boolean packedB = r.packe(b);
        System.out.println("[testLeerenUndWiederPacken] nach packe(a,b): packedB=" + packedB + ", gewicht=" + r.getAktuellesGewicht());
        assertEquals(400, r.getAktuellesGewicht());

        r.leeren();
        System.out.println("[testLeerenUndWiederPacken] nach leeren(): gewicht=" + r.getAktuellesGewicht());
        assertEquals(0, r.getAktuellesGewicht());

        assertTrue(r.packe(b));
        System.out.println("[testLeerenUndWiederPacken] nach packe(b): gewicht=" + r.getAktuellesGewicht());
        assertEquals(300, r.getAktuellesGewicht());
    }

    @Test
    void testUngueltigeEingaben() {
        System.out.println("[testUngueltigeEingaben] start");
        assertThrows(IllegalArgumentException.class, () -> new Gegenstand(0));
        assertThrows(IllegalArgumentException.class, () -> new Rucksack(0));

        Rucksack r = new Rucksack(500);
        boolean packedNull = r.packe(null);
        System.out.println("[testUngueltigeEingaben] packe(null)=" + packedNull);
        assertFalse(packedNull);
    }

    @Test
    void testVieleGegenstaende() {
        System.out.println("[testVieleGegenstaende] start");
        Rucksack r = new Rucksack(1000);
        for (int i = 0; i < 10; i++) {
            assertTrue(r.packe(new Gegenstand(100)));
        }
        System.out.println("[testVieleGegenstaende] aktuellesGewicht=" + r.getAktuellesGewicht());
        assertEquals(1000, r.getAktuellesGewicht());
    }

    @Test
    void testOptimalPackStrategieFindetOptimalesGewicht() {
        System.out.println("[testOptimalPackStrategieFindetOptimalesGewicht] start");
        Rucksack r = new Rucksack(10);
        List<Gegenstand> items = List.of(
                new Gegenstand(6, "A"),
                new Gegenstand(5, "B"),
                new Gegenstand(5, "C")
        );

        PackErgebnis ergebnis = r.packeAlle(items, new OptimalPackStrategie());
        System.out.println("[testOptimalPackStrategieFindetOptimalesGewicht] ergebnis=" + fmt(ergebnis));

        assertEquals(10, ergebnis.getGesamtGewicht());
        assertEquals(10, r.getAktuellesGewicht());
    }

    @Test
    void testGreedyKannSuboptimalSein() {
        System.out.println("[testGreedyKannSuboptimalSein] start");
        Rucksack r = new Rucksack(10);
        List<Gegenstand> items = List.of(
                new Gegenstand(6, "A"),
                new Gegenstand(5, "B"),
                new Gegenstand(5, "C")
        );

        PackErgebnis greedyErg = r.packeAlle(items, new GierigePackStrategie());
        System.out.println("[testGreedyKannSuboptimalSein] greedy=" + fmt(greedyErg));

        PackErgebnis optimalErg = r.packeAlle(items, new OptimalPackStrategie());
        System.out.println("[testGreedyKannSuboptimalSein] optimal=" + fmt(optimalErg));

        assertEquals(10, optimalErg.getGesamtGewicht());
        assertEquals(6, greedyErg.getGesamtGewicht());
    }

}
