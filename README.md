# Rucksack-Problem (Simple Knapsack) – Projektdokumentation

## Ausgangslage
In diesem Projekt geht es um das bekannte **Rucksack-Problem (Simple Knapsack / 0-1 Knapsack)**. Ziel ist es, Gegenstände mit festen Gewichten so in einen Rucksack zu packen, dass ein vorgegebenes **Maximalgewicht** nicht überschritten wird.

Zusätzlich gibt es eine „smarte“ Variante: Man versucht aus einer Menge von Gegenständen eine Auswahl zu treffen, deren **Gesamtgewicht möglichst groß** ist, aber trotzdem **≤ Maximalgewicht** bleibt (im Idealfall exakt gleich dem Maximalgewicht).

Diese `README.md` ist die Dokumentation (kein separates Word-Dokument).

---

## Projektstruktur (aktueller Stand)

**Produktiv-Code (`src/main/java/org/example`)**
- `Gegenstand.java`
- `Rucksack.java`
- `PackStrategie.java`
- `GierigePackStrategie.java`
- `OptimalPackStrategie.java`
- `PackErgebnis.java`
- `Main.java`

**Tests (`src/test/java`)**
- `TestCheck.java`

---

## Was ist im Code umgesetzt?

### Aufgabe Knapsack (1/5)

#### Teilaufgabe 1 – Modell / Klassendiagramm
Gefordert sind mindestens die Klassen **Gegenstand** und **Rucksack**:
- Ein `Gegenstand` hat ein festes Gewicht (Gramm) und optional einen Namen.
- Zwei Gegenstände gelten als gleich, wenn sie dasselbe Gewicht haben.
- Ein `Rucksack` hat ein festes Maximalgewicht.
- Beim Packen darf das Maximalgewicht nicht überschritten werden.
- Man kann den Rucksack leeren und das aktuelle Gewicht abfragen.
- Alle Attribute sollen `protected` sein.

Im aktuellen Code sind die Attribute wie gefordert `protected`.

#### Teilaufgabe 2 – Implementierung
Umgesetzt sind:
- `Gegenstand` mit Validierung (Gewicht muss > 0 sein), Getter sowie `equals/hashCode` auf Basis des Gewichts.
- `Rucksack` mit Maximalgewicht, Inhalt, Methoden zum Packen einzelner Gegenstände, Leeren und Abfragen des aktuellen Gewichts.

#### Teilaufgabe 3 – Unit-Test (Maximalgewicht darf nicht überschritten werden)
Der Test befindet sich in `TestCheck#testGrenzfallMaxGewicht()`:
- Rucksack mit max. 500g
- Gegenstände 200g und 300g werden gepackt (passt genau)
- Ein weiterer Gegenstand (1g) darf nicht mehr gepackt werden

---

### Aufgabe Knapsack (2/5)

#### Teilaufgabe 4 – Erweiterung für „smarten“ Rucksack
Für das Packen einer ganzen Menge von Gegenständen verwendet das Projekt ein Strategie-Prinzip:
- `PackStrategie` definiert, wie aus Kandidaten eine Auswahl berechnet wird.
- Das Resultat wird als `PackErgebnis` zurückgegeben.

#### Teilaufgabe 5 – Programm erweitern
Dafür gibt es in `Rucksack` die Methode:
- `packeAlle(List<Gegenstand> kandidaten, PackStrategie strategie)`

Ablauf (vereinfacht):
1. Die Strategie berechnet ein `PackErgebnis`.
2. Der Rucksack wird geleert.
3. Der Rucksack wird gemäss Ergebnis wieder befüllt.

#### Teilaufgabe 6 – Unit-Test (optimales Gewicht)
Der Test befindet sich in `TestCheck#testOptimalPackStrategieFindetOptimalesGewicht()`.

Fallbeispiel (steht auch im Kommentar im Test):
- `maxGewicht = 10`
- Gegenstände: 6, 5, 5
- Optimales Ergebnis ist 10 (5 + 5)

Zusätzlich zeigt `TestCheck#testGreedyKannSuboptimalSein()`, dass eine gierige Strategie in diesem Beispiel nicht optimal ist.

---

### Aufgabe Knapsack Code Review (3/5)

#### Teilaufgabe 7 – Sitzungsprotokoll
Im Repository ist aktuell kein Review-Protokoll als Datei enthalten.

Wenn du es ergänzen willst, kannst du z.B. eine Datei anlegen wie:
- `docs/review-protokoll.md`

---

### Aufgabe Knapsack (4/5) – Redesign 1

#### Teilaufgabe 8 – Vorschlag 1 (abstrakte Klasse + Interface, zwei Subklassen)
Dieses Redesign (abstrakte Rucksack-Klasse + zwei Subklassen) ist im aktuellen Code nicht umgesetzt.

Im Projekt wird stattdessen bereits mit einem Strategie-Interface gearbeitet (siehe Redesign 2).

---

### Aufgabe Knapsack (5/5) – Redesign 2

#### Teilaufgabe 9 – Strategie zur Laufzeit austauschen
Inhaltlich ist das im aktuellen Code vorhanden:
- `PackStrategie` ist das Strategie-Interface.
- `GierigePackStrategie` und `OptimalPackStrategie` sind zwei Implementierungen.
- In `Rucksack.packeAlle(...)` wird die Strategie zur Laufzeit als Parameter übergeben.

Das entspricht dem Prinzip „Strategie austauschen“, auch wenn keine `setStrategie(...)` Methode verwendet wird.

---

## Hinweise / Bedienung

### Tests ausführen
```bash
mvn test
```

### Demo starten
`Main` enthält eine kleine Konsolen-Demo (Vergleich von Greedy vs. Optimal). Je nach IDE einfach die `Main`-Klasse starten.
