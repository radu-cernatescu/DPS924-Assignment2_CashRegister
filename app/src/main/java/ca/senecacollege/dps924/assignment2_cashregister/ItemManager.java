package ca.senecacollege.dps924.assignment2_cashregister;

import java.util.ArrayList;

public class ItemManager {
    ArrayList<Item> allItems = new ArrayList<>(0);
    ArrayList<Item> purchasedItems = new ArrayList<>(0);

    public void addPurchasedItem(Item item) {
        purchasedItems.add(item);
    }
}
