package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GroceryStore store = new GroceryStore();
        Scanner sc = new Scanner(System.in);
        int input = 0;
        String productName = "";
        Integer productQuantity = -1;
        while(true) {
            System.out.println("--- Tindahan ni Aling Puring ---");
            System.out.print("""
                    1. View Inventory
                    2. Add Product
                    3. Check Product
                    4. Update Stock
                    5. Remove Product
                    6. Exit
                    """);
            System.out.print("Choose an option: ");
            input = sc.nextInt();
            sc.nextLine();

            switch(input) {
                case 1:
                    System.out.println("\nCurrent Inventory: ");
                    HashMap<String, Integer> inventory = store.viewInventory();
                    if (inventory.isEmpty()) {
                        System.out.println("No available product.");
                    } else {
                        for (Map.Entry<String, Integer> product: inventory.entrySet()) {
                            System.out.println(product.getKey() + ": " + product.getValue());
                        }
                    }
                    System.out.println();
                    break;
                case 2:
                    System.out.print("Enter product name: ");
                    productName = sc.nextLine();
                    System.out.print("Enter quantity: ");
                    productQuantity = sc.nextInt();
                    sc.nextLine();
                    if (store.checkProduct(productName) != null) {
                        System.out.println("[Error] Product already exists.");
                        break;
                    }
                    if (store.addProduct(productName, productQuantity) == null){
                        System.out.println("[Error] Product name or quantity invalid.");
                        break;
                    }
                    System.out.println("[Success] " + productName + " added to inventory.");
                    break;
                case 3:
                    System.out.print("Enter product name to check: ");
                    productName = sc.nextLine();
                    if (store.checkProduct(productName) == null) {
                        System.out.println("[Error] Product does not exists.");
                        break;
                    }
                    System.out.println(productName + " is in stock: " + store.checkProduct(productName).getValue());
                    break;
                case 4:
                    System.out.print("Enter product name to update: ");
                    productName = sc.nextLine();
                    System.out.print("Enter new stock quantity: ");
                    productQuantity = sc.nextInt();
                    sc.nextLine();
                    if (store.checkProduct(productName) == null) {
                        System.out.println("[Error] Product does not exists.");
                        break;
                    }
                    if (store.updateProduct(productName, productQuantity) == null){
                        System.out.println("[Error] Product name or quantity invalid.");
                        break;
                    }
                    System.out.println("[Success] " + productName + " stock updated.");
                    break;
                case 5:
                    System.out.print("Enter product name to remove: ");
                    productName = sc.nextLine();
                    if (store.checkProduct(productName) == null) {
                        System.out.println("[Error] Product does not exists.");
                        break;
                    }
                    if (!store.removeProduct(productName)){
                        System.out.println("[Error] Failed removing product.");
                        break;
                    }
                    System.out.println("[Success] " + productName + " removed.");
                    break;
                case 6:
                    System.out.println("Exiting...");
                    System.exit(0);
            }
        }
    }
}