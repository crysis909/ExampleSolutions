package org.tasks.advanced.Classes;

import java.util.function.Function;

public class TemperatureConversion {
    private final TemperatureUnit fromUnit;
    private final TemperatureUnit toUnit;
    private final Function<Double, Double> conversionFunction;

    public TemperatureConversion(TemperatureUnit fromUnit, TemperatureUnit toUnit, Function<Double, Double> conversionFunction) {
        this.fromUnit = fromUnit;
        this.toUnit = toUnit;
        this.conversionFunction = conversionFunction;
    }

    public TemperatureUnit getFromUnit() {
        return fromUnit;
    }

    public TemperatureUnit getToUnit() {
        return toUnit;
    }

    public double convert(double value) {
        return conversionFunction.apply(value);
    }

    @Override
    public String toString() {
        return String.format("%s to %s", fromUnit, toUnit);
    }
}
