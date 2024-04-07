package com.gildedrose;

public final class AgedBrie implements ItemCommand{

    private final Item item;

    public AgedBrie(Item item) {
        this.item = item;
    }

    public void execute() {
        item.quality = item.sellIn < 1 ?
            Math.min(item.quality + 2, 50):
            Math.min(item.quality + 1, 50);
        item.sellIn = item.sellIn - 1;
    }

}
