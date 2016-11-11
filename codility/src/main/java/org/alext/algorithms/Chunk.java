package org.alext.algorithms;

/**
 * Created by alex on 10/5/2016.
 */
public  class Chunk {
    private final int itemsCount;
    private final String name;

    public Chunk(int itemsCount, String name) {
        this.itemsCount = itemsCount;
        this.name = name;
    }

    public int getItemsCount() {
        return itemsCount;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Chunk{" +
                "itemsCount=" + itemsCount +
                ", name='" + name + '\'' +
                '}';
    }
}
