package com.epam.view;

import com.epam.controller.Controller;
import com.epam.controller.ControllerImpl;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class View {

    private Controller controller;
    private Map<String, String> menu;
    private Map<String, Printable> methodsMenu;
    private static Scanner input = new Scanner(System.in);

    public View() {
        controller = new ControllerImpl();
        controller = new ControllerImpl();
        menu = new LinkedHashMap<>();
        menu.put("1", " 1 - validate 'tariffs.json' using schema");
        menu.put("2", " 2 - parse 'tariffs.json' to List using Jackson parser");
        menu.put("3", " 3 - parse 'tariffs.json' to List using Gson parser");
        menu.put("4", " 4 - write List<Tariff> to file 'tariffsNew.json' using Gson parser");
        menu.put("5", " 5 - sort List<Tariff> by name and write to file 'tariffsSortName.json'");
        menu.put("6", " 6 - sort List<Tariff> by payroll and write to file 'tariffsSortPayroll.json'");
        menu.put("Q", " q - exit");
        methodsMenu = new LinkedHashMap<>();
        methodsMenu.put("1", this::validate);
        methodsMenu.put("2", this::parseByJackson);
        methodsMenu.put("3", this::parseByGson);
        methodsMenu.put("4", this::writeToFile);
        methodsMenu.put("5", this::writeToFileSortName);
        methodsMenu.put("6", this::writeToFileSortPayroll);
    }

    private void validate() {
        controller.validate();
    }

    private void parseByJackson() {
        controller.parseByJackson();
    }

    private void parseByGson() {
        controller.parseByGson();
    }

    private void writeToFile() {
        controller.writeToFile();
    }

    private void writeToFileSortName() {
        controller.writeToFileSortName();
    }

    private void writeToFileSortPayroll() {
        controller.writeToFileSortPayroll();
    }

    private void outputMenu() {
        System.out.println("\n==================== MENU ====================");
        for (String str : menu.values()) {
            System.out.println(str);
        }
    }

    public void show() {
        String keyMenu;
        do {
            outputMenu();
            System.out.println("----------------------------------------------");
            System.out.println("Enter the menu point:");
            keyMenu = input.nextLine().toUpperCase();
            try {
                methodsMenu.get(keyMenu).print();
            } catch (Exception e) {
            }
        } while (!keyMenu.equals("Q"));
    }
}
