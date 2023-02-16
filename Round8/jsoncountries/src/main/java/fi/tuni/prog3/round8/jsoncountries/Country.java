package fi.tuni.prog3.round8.jsoncountries;

public class Country implements Comparable<Country> {
    private String name;
    private double area;
    private long population;
    private double gdp;

    public Country(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Country o) {
        return this.name.compareTo(o.name);
    }

    @Override
    public String toString() {
        return String.format("%s%n" +
                "  Area: %.1f km2%n" +
                "  Population: %d%n" +
                "  GDP: %.1f (2015 USD)%n", name, area, population, gdp);
    }

    public String getName() {
        return name;
    }

    public double getArea() {
        return area;
    }

    public long getPopulation() {
        return population;
    }

    public double getGdp() {
        return gdp;
    }

    public void setArea(Double a) {
        this.area = a;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public void setGdp(double gdp) {
        this.gdp = gdp;
    }

}
