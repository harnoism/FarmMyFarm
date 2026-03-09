package Controller;

import java.util.HashMap;
import java.util.Map;

public class Stocks {
    public static Stocks instance = new Stocks();
    public static Map<String, Integer> stocks = new HashMap<>();
    public static Map<String, Integer> seeds = new HashMap<>();

    public void add(String plant, int qty) {
        int current = stocks.getOrDefault(plant, 0);
        stocks.put(plant, current + qty);
    }

    public static void addSeed(String plant, int amount) {
        seeds.put(plant, seeds.getOrDefault(plant, 0) + amount);
    }

    public static boolean removeSeed(String plant) {
        int quantity = seeds.getOrDefault(plant, 0);

        if (quantity > 0) {
            seeds.put(plant, quantity - 1);
            return true;
        }

        return false;
    }
}

