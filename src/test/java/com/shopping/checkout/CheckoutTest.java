package com.shopping.checkout;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class CheckoutTest {

    private static final double APPLE_PRICE = 0.60;
    private static final double ORANGE_PRICE = 0.25;

    Checkout checkout = new Checkout();

    @BeforeAll
    public static void setUp(){

    }

    @Test
    public void testCheckoutWithNoItems() {
        Checkout checkout = new Checkout();
        List<String> items = new ArrayList<>();
        assertEquals(0, checkout.checkout(items));
    }

    @Test
    public void testCheckoutWithRightItems() {
        double cost = checkout.checkout(setupTestData());
        assertEquals(850, cost);
    }

    @Test
    public void testCheckoutWithOneItemEach() {
        List<String> items = new ArrayList<>();
        items.add("Apple");
        items.add("Orange");
        double cost = checkout.checkout(items);
        assertEquals(0.85, cost);
    }

    @Test
    public void testCheckoutWithWrongItemType() {
        List<String> items = new ArrayList<>();
        items.add("banana");
        assertThrows(IllegalArgumentException.class, () -> {
            checkout.checkout(items);
        });
    }

    private List<String>  setupTestData(){
        List<String> items = new ArrayList<>();
        for(int i=0; i<1000; i++){
            items.add("apple");
            items.add("orange");
        }
        return items;

    }
}