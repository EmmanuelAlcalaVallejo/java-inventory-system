package com.emmanuel.inventory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Main entry point for the Inventory Management System (CLI)
 * <p>
 *     This class manages the application sequence, driving the user interaction,
 *     the main menu life-cycle and the data persistence with CSV files.
 * </p>
 *
 * @author Emmanuel Alcalá
 * @version 1.0
 */
public class Main {

    /**
     * Execute the application main cycle.
     * <p>
     *
     *     Init the data structure, load the previous information and keep
     *     the interaction loop until the user decide quit.
     * </p>
     *
     * @param args Command line arguments (Not used in this version).
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Product> inventory = new ArrayList<>();

        // Intentamos cargar datos previos al inicio
        load(inventory);

        boolean cycle = true;

        do {
            menu();
            // Basic validation for prevent crash if the user don't type a number
            if (!sc.hasNextInt()) {
                System.out.println("Please type a valid number");
                sc.nextLine(); // Clean buffer
                continue;
            }

            int cases = sc.nextInt();
            sc.nextLine(); // Consume the remaining line jump

            switch (cases) {
                case 1:
                    System.out.println("Enter the id product: ");
                    String id = sc.nextLine();
                    System.out.println("Enter the product name: ");
                    String name = sc.nextLine();
                    System.out.println("Enter the product type: ");
                    String type = sc.nextLine();
                    System.out.println("Enter the product cost: ");
                    double cost = sc.nextDouble();
                    sc.nextLine();

                    inventory.add(new Product(id, name, type, cost));
                    System.out.println("Product added successfully.");
                    break;

                case 2:
                    System.out.println("        ---INVENTORY---         ");
                    if (inventory.isEmpty()) {
                        System.out.println("The inventory is empty.");
                    } else {
                        for (Product p : inventory) {
                            System.out.println(p);
                        }
                    }
                    break;

                case 3:
                    System.out.println("Enter the id product to search: ");
                    String searchId = sc.nextLine();
                    boolean found = false;

                    for (Product p : inventory) {
                        if (searchId.equals(p.getId())) {
                            found = true;
                            System.out.println("Product Found: " + p.getName());
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Product not found");
                    }
                    break;

                case 4:
                    System.out.println("Enter the id product to delete: ");
                    String deleteId = sc.nextLine();

                    //Lambda use for a cleaner elimination
                    boolean removed = inventory.removeIf(product -> product.getId().equals(deleteId));

                    if (removed) {
                        System.out.println("Product deleted");
                    } else {
                        System.out.println("Product not found");
                    }
                    break;

                case 5:
                    save(inventory);
                    cycle = false;
                    System.out.println("Closing application...");
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        } while (cycle);

        sc.close(); // Good practice: Close the scanner at the end.
    }

    /**
     * Save the inventory actual state in a CSV file ("inventory.csv").
     * <p>
     *     this method rewrite the existing file. If the file not exists,
     *     creates it automatically.
     * </p>
     *
     * @param inventory The products list to persist in the disk.
     */
    public static void save(List<Product> inventory) {
        Path path = Paths.get("inventory.csv");
        List<String> textData = new ArrayList<>();

        for (Product p : inventory) {
            // Formato CSV: ID,Nombre,Tipo,Costo
            String line = p.getId() + "," + p.getName() + "," + p.getType() + "," + p.getCost();
            textData.add(line);
        }
        try {
            Files.write(path, textData,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING);
            System.out.println("Save successfully");
        } catch (IOException e) {
            System.err.println("Critical Error: Could not save data to " + path);
            e.printStackTrace();
        }
    }

    /**
     * Load the data from the CSV file upon starting the application.
     * <p>
     *     Parse each "inventory.csv" file line and rebuild the objects {@link Product}.
     *     If the file doesn't exist, notify to user and start an empty database.
     *     </p>
     *
     * @param inventory The list where the loaded products will be saved.
     */
    public static void load(List<Product> inventory) {
        Path path = Paths.get("inventory.csv");

        if (Files.exists(path)) {
            try {
                List<String> lines = Files.readAllLines(path);
                for (String line : lines) {
                    String[] parts = line.split(",");

                    // Simple validation for prevent IndexOutOfBounds if the line is corrupt.
                    if (parts.length == 4) {
                        String id = parts[0];
                        String name = parts[1];
                        String type = parts[2];
                        double cost = Double.parseDouble(parts[3]);

                        inventory.add(new Product(id, name, type, cost));
                    }
                }
                System.out.println("Data loaded successfully (" + inventory.size() + " items).");
            } catch (IOException | NumberFormatException e) {
                System.err.println("Error loading data: File might be corrupted.");
            }
        } else {
            System.out.println("No previous data found. Creating new database.");
        }
    }

    /**
     * Show the available options in the console interface.
     */
    public static void menu() {
        System.out.println("\n+--------------Menu------------+");
        System.out.println("|  1.-  Add product            |");
        System.out.println("|  2.-  Show inventory         |");
        System.out.println("|  3.-  Search product by ID   |");
        System.out.println("|  4.-  Delete product by ID   |");
        System.out.println("|  5.-  Save & Quit            |");
        System.out.println("+------------------------------+");
        System.out.print("    Select an option: ");
    }
}