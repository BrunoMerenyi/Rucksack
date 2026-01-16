package org.example;

public class Gegenstand {

    protected final int gewicht;
    protected final String name;

    public Gegenstand(int gewicht) {
        this(gewicht, null);
    }

    public Gegenstand(int gewicht, String name) {
        if (gewicht <= 0) {
            throw new IllegalArgumentException("Gewicht muss positiv sein");
        }
        this.gewicht = gewicht;
        this.name = name;
    }

    public int getGewicht() {
        return gewicht;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Gegenstand)) return false;
        Gegenstand other = (Gegenstand) o;
        return this.gewicht == other.gewicht;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(gewicht);
    }
}
