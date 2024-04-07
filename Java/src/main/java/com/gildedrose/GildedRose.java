package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if (isAgedBrie(item)) {
                new AgedBrie(item).updateAgedBrie();
            } else if (isBackstagePasses(item)) {
                new BackstagePasses(item).updatedBackstagePasses();
            } else if (isSulfuras(item)) {
            }
            else {
                updateDefaultItem(item);
            }
        }
    }

    private static void updateDefaultItem(Item item) {
        item.quality = item.sellIn < 1 ?
            Math.max(item.quality - 2, 0):
            Math.max(item.quality - 1, 0);
        item.sellIn = item.sellIn - 1;
    }


    private boolean isSulfuras(Item item) {
        return item.name.equals("Sulfuras, Hand of Ragnaros");
    }

    private boolean isAgedBrie(Item item) {
        return item.name.equals("Aged Brie");
    }

    private boolean isBackstagePasses(Item item) {
        return item.name.equals("Backstage passes to a TAFKAL80ETC concert");
    }
}
