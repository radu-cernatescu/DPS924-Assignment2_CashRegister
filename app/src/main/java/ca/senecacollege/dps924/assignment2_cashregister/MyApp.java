package ca.senecacollege.dps924.assignment2_cashregister;

import android.app.Application;

import java.util.ArrayList;

public class MyApp extends Application {

    ItemManager manager = new ItemManager();

    @Override
    public void onCreate() {
        super.onCreate();
        ArrayList<Item> tempList = manager.allItems;
        tempList.add(new Item(3.99, "Berries", 75, null));
        tempList.add(new Item(4.99, "Cherries", 15, null));
        tempList.add(new Item(14.99, "Vodka", 25, null));
        tempList.add(new Item(19.99, "Rum", 5, null));}
}
