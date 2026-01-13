package com.emmanuel.inventory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //                  Scanner
        Scanner sc = new Scanner(System.in);

        //              Dynamic Array
        List<Product> inventory = new ArrayList<>();

        load(inventory);

        //              Default products in the inventory
        /*inventory.add(new Product("001", "Laptop", "Electronic", 12000.00));
        inventory.add(new Product("002", "Bread", "Food", 10.00));
        inventory.add(new Product("003", "Wash Machine", "Home", 50000.00));*/

        //              Save the inventoy in a csv document


        boolean cycle = true;

        do {
            menu();
            int cases = sc.nextInt();
            sc.nextLine();

            switch (cases) {
                case 1: {
                    //              Add products in the inventory
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
                    break;
                }

                case 2: {
                    //              View of the actual Inventory
                    System.out.println("        ---INVENTORY---         ");
                    for (Product p : inventory) {
                        System.out.println(p);
                    }
                    break;
                }

                case 3:{
                    //              Search a product by ID
                    System.out.println("Enter the id product to search: ");
                    String id = sc.nextLine();
                    boolean found = false;

                    for(Product p : inventory){
                        if(id.equals(p.getId())) {
                            found = true;
                            System.out.println(p.getName());
                            break;
                        }
                    }
                    if(!found){
                        System.out.println("Product not founded");
                    }
                    break;
                }

                case 4:{
                    //              Delete product by ID
                    System.out.println("Enter the id product to delete: ");
                    String id = sc.nextLine();

                    // Search an object with the same id, then remove this object and adjust the list to the new length
                    boolean removed = inventory.removeIf(product -> product.getId().equals(id));

                    if(removed){
                        System.out.println("Product deleted");
                    }else{
                        System.out.println("Product not founded");
                    }

                    break;
                }

                case 5: {
                    //          Save and Quit
                    save(inventory);
                    cycle = false;
                    break;
                }

                default:{
                    break;
                }
            }
        } while (cycle);
    }

    public static void save(List<Product> inventory){
        //Create a direction called "inventory.csv"
        Path path = Paths.get("inventory.csv");

        //Convert from the Object to String
        List<String> textData = new ArrayList<>();

        for(Product p : inventory){
            String line = p.getId() + "," + p.getName() + "," + p.getType() + "," + p.getCost();
            textData.add(line);
        }
        try {
            //Save the document
            Files.write(path, textData,
                    StandardOpenOption.CREATE, //Create if dont exist
                    StandardOpenOption.TRUNCATE_EXISTING); //Delete if exist
            System.out.println("Save successfully");
        } catch (Exception e) {
            //Print The Cause of the error
            System.out.println("Error saving file");
            e.printStackTrace();
        }
    }

    public static void load(List<Product> inventory) {
        Path path = Paths.get("inventory.csv");

        if (Files.exists(path)){
            try{
                List<String> lines = Files.readAllLines(path);
                for (String line : lines) {
                    String[] parts = line.split(",");

                    String id = parts[0];
                    String name = parts[1];
                    String type = parts[2];

                    double cost = Double.parseDouble(parts[3]);

                    Product p = new Product(id,name,type,cost);
                    inventory.add(p);
                }
                System.out.println("Data load successfully");
            } catch (IOException e) {
                System.out.println("Error to load data");
            }
        }else {
            System.out.println("Dont find the file, create a new empty data base");
        }
    }

    // Main Menu
    public static void menu(){
        System.out.println("+--------------Menu------------+");
        System.out.println("|  1.-  Add product            |");
        System.out.println("|  2.-  Show inventory         |");
        System.out.println("|  3.-  Search product by ID   |");
        System.out.println("|  4.-  Delete product by ID   |");
        System.out.println("|  5.-  Quit                   |");
        System.out.println("+------------------------------+");
        System.out.println("    Select an option: ");
    }
}
