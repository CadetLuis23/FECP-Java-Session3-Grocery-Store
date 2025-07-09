package org.example;

import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class GroceryStoreTest {
    static GroceryStore groceryStore;

    @BeforeAll
    static void setup () {
        groceryStore = new GroceryStore();
    }

    @Test
    @Order(0)
    void addProductWithValidNameAndQuantity() {
        String expectedKey = "Banana";
        Integer expectedValue = 30;
        Map.Entry<String, Integer> entry = groceryStore.addProduct("Banana", 30);
        assertEquals(expectedKey, entry.getKey());
        assertEquals(expectedValue, entry.getValue());
        System.out.println("[PASSED] Add product with valid name and quantity.");
    }

    @Test
    @Order(1)
    void addProductWithZeroQuantity() {
        String expectedKey = "Mango";
        Integer expectedValue = 0;
        Map.Entry<String, Integer> entry = groceryStore.addProduct("Mango", 0);
        assertEquals(expectedKey, entry.getKey());
        assertEquals(expectedValue, entry.getValue());
        System.out.println("[PASSED] Add product with zero quantity.");
    }

    @Test
    @Order(2)
    void addProductWithInvalidQuantity() {
        Map.Entry<String, Integer> entry = groceryStore.addProduct("Mango", -1);
        assertNull(entry);
        System.out.println("[PASSED] Add product with invalid quantity.");
    }

    @Test
    @Order(3)
    void addProductWithInvalidNameButValidQuantity() {
        Map.Entry<String, Integer> entry = groceryStore.addProduct("", 10);
        assertNull(entry);
        System.out.println("[PASSED] Add product with invalid name.");
    }

    @Test
    @Order(4)
    void addProductThatAlreadyExist() {
        String expectedKey = "Milk";
        Integer expectedValue = 50;
        groceryStore.addProduct("Milk", 10);
        Map.Entry<String, Integer> entry = groceryStore.addProduct("Milk", 50);
        assertEquals(expectedKey, entry.getKey());
        assertEquals(expectedValue, entry.getValue());
        System.out.println("[PASSED] Add product that already exist.");
    }

    @Test
    @Order(5)
    void checkExistingProduct() {
        String expectedKey = "Milk";
        Integer expectedValue = 20;
        groceryStore.updateProduct("Milk", 20);
        Map.Entry<String, Integer> entry = groceryStore.checkProduct("Milk");
        assertEquals(expectedKey, entry.getKey());
        assertEquals(expectedValue, entry.getValue());
        System.out.println("[PASSED] Check existing product.");
    }

    @Test
    @Order(6)
    void checkProductThatDoesNotExist() {
        Map.Entry<String, Integer> entry = groceryStore.checkProduct("Ice Cream");
        assertNull(entry);
        System.out.println("[PASSED] Check product that does not exist.");
    }

    @Test
    @Order(7)
    void updateProductWithInvalidName() {
        Map.Entry<String, Integer> entry = groceryStore.updateProduct("", 25);
        assertNull(entry);
        System.out.println("[PASSED] Update product with invalid name.");
    }

    @Test
    @Order(8)
    void updateProductWithInvalidQuantity() {
        Map.Entry<String, Integer> entry = groceryStore.updateProduct("Bread", -1);
        assertNull(entry);
        System.out.println("[PASSED] Update product with invalid quantity.");
    }

    @Test
    @Order(9)
    void updateProductWithValidQuantity() {
        String expectedKey = "Bread";
        Integer expectedValue = 25;
        groceryStore.addProduct("Bread", 5);
        Map.Entry<String, Integer> entry = groceryStore.updateProduct("Bread", 25);
        assertEquals(expectedKey, entry.getKey());
        assertEquals(expectedValue, entry.getValue());
        System.out.println("[PASSED] Update product with valid quantity.");
    }

    @Test
    @Order(10)
    void updateProductThatDoesNotExist() {
        Map.Entry<String, Integer> entry = groceryStore.updateProduct("Tofu", 10);
        assertNull(entry);
        System.out.println("[PASSED] Update product that does not exist.");
    }

    @Test
    @Order(11)
    void removeExistingProduct() {
        groceryStore.addProduct("Eggs", 30);
        boolean isProductRemoved = groceryStore.removeProduct("Eggs");
        Map.Entry<String, Integer> entry = groceryStore.checkProduct("Eggs");
        assertTrue(isProductRemoved);
        assertNull(entry);
        System.out.println("[PASSED] Remove existing product.");
    }

    @Test
    @Order(12)
    void removeProductThatDoesNotExist() {
        boolean isProductRemoved = groceryStore.removeProduct("Pizza");
        Map.Entry<String, Integer> entry = groceryStore.checkProduct("Pizza");
        assertFalse(isProductRemoved);
        assertNull(entry);
        System.out.println("[PASSED] Remove product that does not exist.");
    }

    @Test
    @Order(13)
    void checkInventory() {
        HashMap<String, Integer> inventory = groceryStore.viewInventory();
        assertNotNull(inventory);
        assertFalse(inventory.isEmpty());
        assertTrue(inventory.containsKey("Mango"));
        System.out.println("[PASSED] Check inventory.");
    }

}