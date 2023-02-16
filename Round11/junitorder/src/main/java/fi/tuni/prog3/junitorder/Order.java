package fi.tuni.prog3.junitorder;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.TreeMap;

public class Order {
    private TreeMap<String, Entry> entries = new TreeMap<>();
    private ArrayList<Entry> Entries = new ArrayList<>();

    static class Entry {
        private final Item item;
        private int count;

        public Entry(Item item, int count) throws IllegalArgumentException{
            if(count < 0) {
                throw new IllegalArgumentException();
            }

            this.item = item;
            this.count = count;
        }

        public String getItemName() {
            return item.getName();
        }

        public double getUnitPrice() {
            return count*item.getPrice();
        }

        public Item getItem() {
        return item;
        }

        public int getCount() {
            return count;
        }

        public String toString() {
            // x is the item unit count and item is the string representation of this entry's item
            return String.format("%d units of %s", count, item.name);
        }
    }

    static class Item {
        private final String name;
        private final double price;
        public Item(String name, double price) throws IllegalArgumentException {
            if(name == null || price < 0) {
                throw new IllegalArgumentException();
            }

            this.name = name;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }

        public boolean equals(Item other) {
            return name.equals(other.getName());
        }
    }

    public Order() {

    }

    public boolean addItems(Item item, int count) throws IllegalArgumentException {
        if(count < 0) {
            throw new IllegalArgumentException();
        }

        if(Entries.contains(item)) {
            if(item.getPrice() != Entries.get(Entries.indexOf(item)).getItem().getPrice()) {
                throw new IllegalStateException();
            }
            entries.get(item.getName()).count += count;
        }
        else {
            Entry newEntry = new Entry(item, count);
            entries.put(item.getName(), newEntry);
        }
        return true;
    }

    public boolean addItems(String name, int count) throws IllegalArgumentException, NoSuchElementException {
        if(count < 0) {
            throw new IllegalArgumentException();
        }
        if(!entries.containsKey(name)) {
            throw new NoSuchElementException();
        }
        entries.get(name).count += count;
        return true;
    }

    public List<Entry> getEntries() {
        return null;
    }
}
