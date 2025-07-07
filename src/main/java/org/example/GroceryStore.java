package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class GroceryStore {
    private HashMap<String, Integer> products;

    public GroceryStore () {
        products = new HashMap<>();
    }

    public HashMap.Entry<String, Integer> addProduct (String productName, Integer productQuantity) {
        if (productName.isBlank() || productQuantity < 0) return null;
        if (checkProduct(productName) != null) return null;
        products.put(productName, productQuantity);
        return products.entrySet().stream().filter(e -> e.getKey().equalsIgnoreCase(productName)).toList().getFirst();
    }

    public HashMap.Entry<String, Integer> checkProduct(String productName) {
        List<Map.Entry<String, Integer>> result = products.entrySet().stream().filter(e -> e.getKey().equalsIgnoreCase(productName)).toList();
        return !result.isEmpty() ? result.getFirst(): null;
    }

    public HashMap.Entry<String, Integer> updateProduct(String productName, Integer productQuantity) {
        if (productName.isBlank() || productQuantity < 0) return null;
        if (checkProduct(productName) == null) return null;
        products.put(productName, productQuantity);
        return products.entrySet().stream().filter(e -> e.getKey().equalsIgnoreCase(productName)).toList().getFirst();
    }

    public boolean removeProduct(String productName) {
        if (checkProduct(productName) == null) return false;
        products.remove(productName);
        return true;
    }

    public HashMap<String, Integer> viewInventory() {
        return products;
    }

}
