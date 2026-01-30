package com.shopping.checkout;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Assumption: the items list is something like ["Apple", "Orange", "Apple"]
 */
public class Checkout {

    private static final double APPLE_PRICE = 0.60;
    private static final double ORANGE_PRICE = 0.25;


    public double checkout(List<String> items) {
        if(items.isEmpty()){
            return 0;
        }
        Map<String, Integer> productTypeMap = countByItemType(items);
        return calculateTotalPrice(productTypeMap);
    }

    /**
     * separates each item type and counts them
     * @param items list of items
     * @return map of item type and their counts
     */
    private Map<String, Integer> countByItemType(List<String> items) {
        Map<String, Integer> productTypeMap = new HashMap<>();
        for(String item: items){
            if(! productTypeMap.containsKey(item)){
                productTypeMap.put(item, 1);
            } else {
                int count = productTypeMap.get(item);
                productTypeMap.put(item, ++count);
            }
        }
        return productTypeMap;
    }

    private double calculateTotalPrice(Map<String, Integer> productTypeMap) {
        double totalPrice = 0;
        for(String item: productTypeMap.keySet()){
            if(item.equalsIgnoreCase(ProductType.ORANGE.toString())) {
                totalPrice += productTypeMap.get(item)*ORANGE_PRICE;
            } else if(item.equalsIgnoreCase(ProductType.APPLE.toString())) {
                totalPrice += productTypeMap.get(item)*APPLE_PRICE;
            } else {
                throw new IllegalArgumentException("No matching item found");
            }

        }
        return totalPrice;
    }
}
