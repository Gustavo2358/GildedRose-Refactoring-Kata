package com.gildedrose;

public class Conjured implements ItemCommand{

    private final Item item;

    public Conjured(Item item) {
        this.item = item;
    }

    @Override
    public void execute() {
        item.quality = item.sellIn < 1 ?
            Math.max(item.quality - 4, 0):
            Math.max(item.quality - 2, 0);
        item.sellIn = item.sellIn - 1;
    }
}
