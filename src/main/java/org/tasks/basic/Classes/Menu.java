package org.tasks.basic.Classes;

import org.tasks.basic.Utils.TemperatureConverter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private static final List<Method> temperatureMethods = TemperatureConverter.getStaticMethods();
    private static final Scanner scanner = new Scanner(System.in);
    private static final StringBuilder builder = new StringBuilder();

    private int input = -1;
    private double temperature = -1.0;
    private final int minNumber = 0;
    private final int maxNumber = temperatureMethods.size();

    public void displayMenu() {
        for (int i = 0; i < maxNumber; i++) {
            Method method = temperatureMethods.get(i);
            builder.append(i + 1).append(". ");

            String methodName = methodNameToReadableString(method);

            System.out.println(methodName);

            builder.setLength(0);
        }
        System.out.println("0. Quit");
    }

    private String methodNameToReadableString(Method method) {
        String[] words = method.getName().split("(?=[A-Z])");

        for (String word : words) {
            builder.append(Character.toUpperCase(word.charAt(0))).append(word.substring(1)).append(" ");
        }

        return builder.toString().trim();
    }

    public int getUserInput() {
        System.out.printf("%nChoice an option between %d - %d: ", minNumber, maxNumber);
        boolean isValid = false;

        while (!isValid) {
            isValid = isInputValid();
        }

        if(input == 0) {
            System.out.println("Exiting Program");
            System.exit(0);
        }

        return input;
    }

    public void convertTemperature(int input) throws InvocationTargetException, IllegalAccessException {
        boolean isValid = false;
        Method temperatureMethod = temperatureMethods.get(input - 1);
        String methodName = methodNameToReadableString(temperatureMethod);

        System.out.println("Converting " + methodName);

        while (!isValid) {
            isValid = isTemperatureInputValid();
        }
        System.out.println(temperatureMethod.invoke(null, temperature));
    }

    private boolean isInputValid() {
        try {
            input = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.printf("Invalid %s%nTry again: ", e.getMessage());
            return false;
        }

        if (input < minNumber || input > maxNumber) {
            System.out.printf("Invalid Input %s, has to be a number between %d - %d%nTry again: ", input, minNumber, maxNumber);
            return false;
        }

        return true;
    }

    private boolean isTemperatureInputValid() {
        System.out.print("Enter Temperature: ");
        try {
            temperature = Double.parseDouble(scanner.nextLine());
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid temperature value.");
        }

        return false;
    }

    public void run() throws InvocationTargetException, IllegalAccessException {
        while (true) {
            displayMenu();
            int input = getUserInput();
            convertTemperature(input);
        }
    }
}
