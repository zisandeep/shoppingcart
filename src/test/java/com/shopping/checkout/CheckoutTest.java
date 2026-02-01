package com.shopping.checkout;

import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class CheckoutTest {

    private static final double APPLE_PRICE = 0.60;
    private static final double ORANGE_PRICE = 0.25;

    Checkout checkout = new Checkout();

    @Test
    public void testCheckoutWithNoItems() {
        Checkout checkout = new Checkout();
        List<Product> items = new ArrayList<>();
        assertEquals(0, checkout.checkout(items));
    }

    @Test
    public void testCheckoutWithLargeDataset() {
        double cost = checkout.checkout(setupTestData());
        assertEquals(46666.75, cost);
    }

    @Test
    public void testCheckoutWithOneItemEach() {
        List<Product> items = new ArrayList<>();
        Product apple = new Product(1, "Apple", APPLE_PRICE, Offers.BOGOF);
        Product orange = new Product(2, "Orange", ORANGE_PRICE, Offers.TFT);
        items.add(apple);
        items.add(orange);
        double cost = checkout.checkout(items);
        assertEquals(0.85, cost);
    }

    @Test
    public void testCheckoutWithMultipleItems() {
        List<Product> items = new ArrayList<>();
        items.add(new Product(1, "Apple", APPLE_PRICE, Offers.BOGOF));
        items.add(new Product(1, "Apple", APPLE_PRICE, Offers.BOGOF));
        items.add(new Product(2, "Orange", ORANGE_PRICE, Offers.TFT));
        items.add(new Product(2, "Orange", ORANGE_PRICE, Offers.TFT));
        items.add(new Product(2, "Orange", ORANGE_PRICE, Offers.TFT));
        double cost = checkout.checkout(items);
        assertEquals(1.10, cost);
    }
    @Test
    public void testCheckoutWithWrongItemType() {
        List<Product> items = new ArrayList<>();
        items.add(new Product(3, "Banana", 0.20, null));
        assertThrows(IllegalArgumentException.class, () -> {
            checkout.checkout(items);
        });
    }

    private List<Product>  setupTestData(){
        List<Product> items = new ArrayList<>();
        for(int i=0; i<100000; i++){
            Product apple = new Product(1, "Apple", APPLE_PRICE, Offers.BOGOF);
            Product orange = new Product(2, "Orange", ORANGE_PRICE, Offers.TFT);
            items.add(apple);
            items.add(orange);
        }
        return items;
    }
}