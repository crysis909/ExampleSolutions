package org.tasks.advanced.Classes;

public record TemperatureUnit(String name, String symbol) {

    @Override
    public String toString() {
        return String.format("%s %s", name, symbol);
    }
}
