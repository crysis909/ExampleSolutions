package org.tasks.basic.Utils;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class TemperatureConverter {
    public static double celsiusToFahrenheit(double celsius) {
        return (celsius * 9.0 / 5.0) + 32.0;
    }

    public static double celsiusToKelvin(double celsius) {
        return celsius + 273.15;
    }

    public static double celsiusToRankine(double celsius) {
        return (celsius + 273.15) * 9.0 / 5.0;
    }

    public static double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32.0) * 5.0 / 9.0;
    }

    public static double fahrenheitToKelvin(double fahrenheit) {
        return (fahrenheit + 459.67) * 5.0 / 9.0;
    }

    public static double fahrenheitToRankine(double fahrenheit) {
        return fahrenheit + 459.67;
    }

    public static double kelvinToCelsius(double kelvin) {
        return kelvin - 273.15;
    }

    public static double kelvinToFahrenheit(double kelvin) {
        return kelvin * 9.0 / 5.0 - 459.67;
    }

    public static double kelvinToRankine(double kelvin) {
        return kelvin * 9.0 / 5.0;
    }

    public static double rankineToCelsius(double rankine) {
        return (rankine - 491.67) * 5.0 / 9.0;
    }

    public static double rankineToFahrenheit(double rankine) {
        return rankine - 459.67;
    }

    public static double rankineToKelvin(double rankine) {
        return rankine * 5.0 / 9.0;
    }


    public static List<Method> getStaticMethods() {
        List<Method> staticMethods = new ArrayList<>();
        Class<?> myClass = TemperatureConverter.class;
        Method[] methods = myClass.getDeclaredMethods();
        for (Method method : methods) {
            if (Modifier.isStatic(method.getModifiers()) && !method.getName().equals("getStaticMethods")) {
                staticMethods.add(method);
            }
        }
        return staticMethods;
    }
}