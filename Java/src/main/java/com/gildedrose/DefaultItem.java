package com.gildedrose;

public class DefaultItem {

    private final Item item;

    public DefaultItem(Item item) {
        this.item = item;
    }

    public void updateDefaultItem() {
        item.quality = item.sellIn < 1 ?
            Math.max(item.quality - 2, 0):
            Math.max(item.quality - 1, 0);
        item.sellIn = item.sellIn - 1;
    }



}
