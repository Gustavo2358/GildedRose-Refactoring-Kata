package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if (isAgedBrie(item)) {
                item.quality = item.sellIn < 1 ?
                    Math.min(item.quality + 2, 50):
                    Math.min(item.quality + 1, 50);
                item.sellIn = item.sellIn - 1;
                return;
            } else if (isBackstagePasses(item)) {
                item.quality = Math.min(updateBackstagePassesQuality(item), 50);
                item.sellIn = item.sellIn - 1;
                return;
            } else if (isSulfuras(item)) {

            }
            else {
                item.quality = item.sellIn < 1 ?
                    Math.max(item.quality - 2, 0):
                    Math.max(item.quality - 1, 0);
                item.sellIn = item.sellIn - 1;
            }
        }
    }

    private int updateBackstagePassesQuality(Item item) {
        if (item.sellIn < 1) {
            return 0;
        }

        if (item.sellIn < 6) {
            if (item.quality < 50) {
                return item.quality + 3;
            }
        }

        if (item.sellIn < 11) {
            if (item.quality < 50) {
                return item.quality + 2;
            }
        }

        return item.quality + 1;
    }

    private static boolean isSulfuras(Item item) {
        return item.name.equals("Sulfuras, Hand of Ragnaros");
    }

    private static boolean isAgedBrie(Item item) {
        return item.name.equals("Aged Brie");
    }

    private static boolean isBackstagePasses(Item item) {
        return item.name.equals("Backstage passes to a TAFKAL80ETC concert");
    }
}
