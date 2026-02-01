package com.shopping.checkout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Assumption: the items list is list of Products.
 */
public class Checkout {


    public double checkout(List<Product> items) {
        if(items.isEmpty()){
            return 0;
        }
        items.stream().forEach(item -> {;
            if((item.getProductId()  != 1) && (item.getProductId() != 2)){
                throw new IllegalArgumentException("Invalid product type found: " + item.getName());
            }
        });
        Map<Integer, List<Product>> productTypeMap = countByItemType(items);
        return calculateTotalPrice(productTypeMap);
    }

    /**
     * separates each product type in a Map with key as productId and value is list of products of that type
     * @param items list of items
     * @return Map with key as productId and value is list of products of that type
     */
    private Map<Integer, List<Product>> countByItemType(List<Product> items) {
        Map<Integer, List<Product>> productTypeMap = new HashMap<>();

        for(Product item: items){
            if(! productTypeMap.containsKey(item.getProductId())){
                List<Product> seggregatedItems = new ArrayList<>();
                seggregatedItems.add(item);
                productTypeMap.put(item.getProductId(), seggregatedItems);
            } else {
                productTypeMap.get(item.getProductId()).add(item);
            }
        }
        return productTypeMap;
    }

    private double calculateTotalPrice(Map<Integer, List<Product>> productTypeMap) {
        double totalPrice = 0;
        List<Product> seggregatedItems = new ArrayList<>();
        for(Integer productId: productTypeMap.keySet()){
            seggregatedItems = productTypeMap.get(productId);
            totalPrice += calculateEachProductPrice(seggregatedItems.size(), seggregatedItems.getFirst().getPrice(), seggregatedItems.getFirst().getOffer());
        }
        return totalPrice;
    }

    private double calculateEachProductPrice(int count, double price, Offers offer) {
        double total = 0;
        switch (offer) {
            case BOGOF:
                total = ((count / 2) + (count % 2)) * price;
                break;
            case TFT:
                total = ((count / 3) * 2 + (count % 3)) * price;
                break;
            default:
                total = count * price;
        }
        return total;

    }
}
