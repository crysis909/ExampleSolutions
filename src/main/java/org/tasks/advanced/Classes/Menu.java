package org.tasks.advanced.Classes;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Menu {
    private static final Scanner scanner = new Scanner(System.in);

    private final List<TemperatureUnit> units = List.of(
            new TemperatureUnit("Celsius", "°C"),
            new TemperatureUnit("Fahrenheit", "°F"),
            new TemperatureUnit("Kelvin", "K"),
            new TemperatureUnit("Rankine", "°R")
    );

    private final List<TemperatureConversion> conversions = List.of(
            new TemperatureConversion(units.get(0), units.get(1), value -> (value * 1.8) + 32),
            new TemperatureConversion(units.get(0), units.get(2), value -> value + 273.15),
            new TemperatureConversion(units.get(0), units.get(3), value -> (value + 273.15) * 1.8),
            new TemperatureConversion(units.get(1), units.get(0), value -> (value - 32) / 1.8),
            new TemperatureConversion(units.get(1), units.get(2), value -> (value + 459.67) * 5 / 9),
            new TemperatureConversion(units.get(1), units.get(3), value -> value + 459.67),
            new TemperatureConversion(units.get(2), units.get(0), value -> value - 273.15),
            new TemperatureConversion(units.get(2), units.get(1), value -> value * 1.8 - 459.67),
            new TemperatureConversion(units.get(2), units.get(3), value -> value * 1.8),
            new TemperatureConversion(units.get(3), units.get(0), value -> (value - 491.67) / 1.8),
            new TemperatureConversion(units.get(3), units.get(1), value -> value - 459.67),
            new TemperatureConversion(units.get(3), units.get(2), value -> value / 1.8)
    );

    public void run() {
        while (true) {
            displayMenu(conversions);
            int choice = readChoice(conversions);

            if (choice == 0) {
                exitProgram();
            }

            TemperatureConversion conversion = conversions.get(choice - 1);
            double value = readTemperatureValue(conversion);

            double result = conversion.convert(value);

            displayResult(value, conversion.getFromUnit().symbol(), conversion.getToUnit().symbol(), result);
            System.out.println();
        }
    }

    private static void exitProgram() {
        System.out.println("Stopping Program....");
        scanner.close();
        System.exit(0);
    }

    private static double readTemperatureValue(TemperatureConversion conversion) {
        double value = 0.0;
        boolean validInput = false;
        TemperatureUnit fromUnit = conversion.getFromUnit();
        TemperatureUnit toUnit = conversion.getToUnit();

        while (!validInput) {
            System.out.println(fromUnit + " to " + toUnit);
            System.out.print("Enter " + fromUnit + ": ");
            try {
                value = Double.parseDouble(scanner.nextLine());
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid temperature value.");
            }
        }

        return value;
    }

    private static void displayResult(double value, String fromUnitSymbol, String toUnitSymbol, double result) {
        System.out.printf(Locale.US, "%.2f %s = %.2f %s%n", value, fromUnitSymbol, result, toUnitSymbol);
    }

    private static int readChoice(List<TemperatureConversion> conversions) {
        int choice = -1;

        while (choice < 0 || choice > conversions.size()) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }

            if (choice < 0 || choice > conversions.size()) {
                System.out.println("Invalid input. Please choose a valid option.");
            }
        }

        return choice;
    }



    private static void displayMenu(List<TemperatureConversion> conversions) {
        System.out.println("Choose a conversion:");

        for (int i = 0; i < conversions.size(); i++) {
            TemperatureConversion conversion = conversions.get(i);
            TemperatureUnit fromUnit = conversion.getFromUnit();
            TemperatureUnit toUnit = conversion.getToUnit();
            System.out.println((i + 1) + ". " + fromUnit + " to " + toUnit);
        }

        System.out.println("0. Quit");
        System.out.println();
    }
}
