package org.tasks.basic;

import org.tasks.basic.Classes.Menu;

import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        Menu menu = new Menu();
        menu.run();
    }
}
