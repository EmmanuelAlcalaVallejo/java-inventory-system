package com.emmanuel.inventory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //                  Scanner
        Scanner sc = new Scanner(System.in);

        //              Dynamic Array
        List<Product> inventory = new ArrayList<>();

        //              Default products in the inventory
        inventory.add(new Product("001", "Laptop", "Electronic", 12000.00));
        inventory.add(new Product("002", "Bread", "Food", 10.00));
        inventory.add(new Product("003", "Wash Machine", "Home", 50000.00));

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

                    // Search an object with the same id and then remove this object and adjust the list to the new length
                    boolean removed = inventory.removeIf(product -> product.getId().equals(id));

                    if(removed){
                        System.out.println("Product deleted");
                    }else{
                        System.out.println("Product not founded");
                    }

                    break;
                }

                case 5: {
                    //          Quit
                    cycle = false;
                    break;
                }

                default:{
                    break;
                }
            }
        } while (cycle);
    }

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